package openldap.api.mappers;

import openldap.api.entities.User;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

public class UserAttributeMapper implements AttributesMapper<User> {

    @Override
    public User mapFromAttributes(Attributes attributes) throws NamingException {
        User user = new User();
        user.setUid(null != attributes.get("uid") ? attributes.get("uid").get().toString() : null);
        user.setCn(null != attributes.get("cn") ? attributes.get("cn").get().toString() : null);
        user.setSn(null != attributes.get("sn") ? attributes.get("sn").get().toString() : null);

        return user;
    }

}
