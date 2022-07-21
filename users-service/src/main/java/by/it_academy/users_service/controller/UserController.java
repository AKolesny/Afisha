package by.it_academy.users_service.controller;

import by.it_academy.users_service.dto.UserDtoOut;
import by.it_academy.users_service.dto.UserLogIn;
import by.it_academy.users_service.dto.UserSignUp;
import by.it_academy.users_service.service.api.IUserMapper;
import by.it_academy.users_service.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService service;
    private final IUserMapper mapper;

    public UserController(IUserService service, IUserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDtoOut> signUp(@RequestBody UserSignUp dto) {
        UserDtoOut dtoOut = this.mapper.mapDtoOut(this.service.signUp(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoOut);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody UserLogIn dto) {

        return ResponseEntity.ok(this.service.logIn(dto));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDtoOut> get(){

        return ResponseEntity.ok(this.mapper.mapDtoOut(this.service.get()));
    }
}
