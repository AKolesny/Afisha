package by.it_academy.users_service.controller;

import by.it_academy.users_service.dto.UserDtoOut;
import by.it_academy.users_service.dto.UserLogIn;
import by.it_academy.users_service.dto.UserSignUp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping("/registration")
    public ResponseEntity<UserDtoOut> signUp(@RequestBody UserSignUp dto) {

        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody UserLogIn dto) {

        return null;
    }

    @GetMapping("/me")
    public UserDtoOut get(){
        return null;
    }
}
