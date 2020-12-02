package openldap.api.service;

import openldap.api.entities.User;

import java.util.List;
public interface UserService {

    void createUser(User user);

    List<User> getAll();

    User getUserByUid(String uid);

    void deleteUserByUid(String uid);

}
