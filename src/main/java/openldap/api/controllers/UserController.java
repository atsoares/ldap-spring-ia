package openldap.api.controllers;

import static openldap.api.constants.Constants.*;

import javax.validation.Valid;

import openldap.api.entities.User;
import openldap.api.exception.UserAlreadyExistsException;
import openldap.api.exception.UserDoesNotExistException;
import openldap.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;

@RestController
@RequestMapping("/Users")
@Api(value = "OpenLdap Api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = STATUS_201_CREATED),
                    @ApiResponse(code = 400, message = STATUS_400_BAD_REQUEST)
            })
    @PostMapping
    @ApiOperation(value = "Create user")
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }


    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = STATUS_200_GET_OK),
                    @ApiResponse(code = 404, message = STATUS_404_NOT_FOUND)
            })
    @GetMapping
    @ApiOperation(value = "Get all users")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = STATUS_200_GET_OK),
                    @ApiResponse(code = 404, message = STATUS_404_NOT_FOUND)
            })
    @GetMapping("/{uid}")
    @ApiOperation(value = "Get user by uid")
    public ResponseEntity<User> getUserByUid(@PathVariable String uid) throws UserDoesNotExistException {
        return new ResponseEntity<User>(userService.getUserByUid(uid), HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = STATUS_200_GET_OK),
                    @ApiResponse(code = 404, message = STATUS_404_NOT_FOUND)
            })
    @DeleteMapping("/{uid}")
    @ApiOperation(value = "Delete user by uid")
    public ResponseEntity<Void> deleteUserById(@PathVariable String uid) throws UserDoesNotExistException {
        userService.deleteUserByUid(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}