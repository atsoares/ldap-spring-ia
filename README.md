# ldap-spring-ia
TECHINTERVIEW LDAP REST API IA

# Ldap Config
 ```
 docker run --env LDAP_ORGANISATION="My Company" --env LDAP_DOMAIN="techinterview.com" --env LDAP_ADMIN_PASSWORD="123456" -p 389:389 --detach osixia/openldap:1.4.0
 ```
 - Create the OU user under domain:
 - Create the file create_ou_users.ldif with the following content:
 ```
 dn: ou=Users,dc=techinterview,dc=com
 changetype: add
 objectClass: organizationalUnit
 objectClass: top
 ou: Users
 <blank line at the end of the file>
 ```

 - The following command will create a OU(organizationalUnit) to hold your users:
 ```
 ldapmodify -h localhost -p 389 -w '123456' -D 'cn=admin,dc=techinterview,dc=com'  <  create_ou_users.ldif  
 ```

# How to test locally
- Java 8 / Maven / Your favourite IDE / Git
- Ports 8080, 8081 and 8082 must be available
- Clone the repo @ https://github.com/atsoares/ldap-spring-ia.git
- 'docker-compose up' in the root directory to start the LDAP server
- Start the API(8080) application on your IDE ('ldapApiApplication.java')
- Access the API methods with either swagger or postman

# Swagger
http://localhost:8080/swagger-ui.html

# PHP LDAP ADMIN
- http://localhost/
- web-based LDAP administration tool for managing your LDAP server

# Tests
UserControllerTest.java
