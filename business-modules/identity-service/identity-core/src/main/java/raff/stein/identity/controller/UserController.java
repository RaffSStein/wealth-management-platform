package raff.stein.identity.controller;

import org.openapitools.api.UsersApi;
import org.openapitools.model.CreateUserRequest;
import org.openapitools.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController implements UsersApi {


    @Override
    public ResponseEntity<User> createUser(CreateUserRequest createUserRequest) {
        return null;
    }

    @Override
    public ResponseEntity<User> getUserById(UUID id) {
        return null;
    }
}
