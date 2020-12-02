package openldap.api.service.impl;

import openldap.api.entities.User;
import openldap.api.mappers.UserAttributeMapper;
import openldap.api.service.UserService;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public void createUser(User user){
        ldapTemplate.bind(buildDn(user.getUid()), null, buildAttributes(user));
    }

    @Override
    public List<User> getAll() {
        return ldapTemplate.search(query().searchScope(SearchScope.SUBTREE).where("objectclass").is("person"),
                new UserAttributeMapper());
    }

    @Override
    public User getUserByUid(String uid){

        User user = new User();

        List<User> users = ldapTemplate.search(query().searchScope(SearchScope.SUBTREE).where("objectclass").is("person").and("uid").like(uid),
                new UserAttributeMapper());

        if(!users.isEmpty())
            user = users.get(0);

        return user;
    }

    @Override
    public void deleteUserByUid(String uid){
        ldapTemplate.unbind(buildDn(uid));
    }

    public Name buildDn(String userId) {
        return LdapNameBuilder.newInstance()
                .add("uid", userId).build();
    }

    private Attributes buildAttributes(User user) {
        BasicAttribute ocattr = new BasicAttribute("objectclass");
        ocattr.add("top");
        ocattr.add("person");
        ocattr.add("organizationalPerson");
        ocattr.add("inetOrgPerson");

        Attributes attrs = new BasicAttributes();
        attrs.put(ocattr);
        attrs.put("cn", user.getCn());
        attrs.put("sn", user.getSn());
        attrs.put("uid", user.getUid());
        return attrs;
    }


}
