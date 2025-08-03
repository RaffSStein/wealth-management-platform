package raff.stein.user.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.UserApi;
import org.openapitools.model.CreateUserRequest;
import org.openapitools.model.UpdateUserRequest;
import org.openapitools.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    //TODO: Implement the methods of UserApi interface

    @Override
    public ResponseEntity<User> createUser(CreateUserRequest createUserRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> disableUser(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> enableUser(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<User> getCurrentUser() {
        // This method should return the currently authenticated user.
        return null;
    }

    @Override
    public ResponseEntity<User> getUserById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<User> updateUserById(UUID id, UpdateUserRequest updateUserRequest) {
        return null;
    }
}
