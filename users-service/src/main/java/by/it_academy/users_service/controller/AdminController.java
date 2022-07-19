package by.it_academy.users_service.controller;

import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.IMyPage;
import by.it_academy.users_service.dto.UserDto;
import by.it_academy.users_service.dto.UserDtoOut;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class AdminController {

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDto dto) {
        return null;
    }

    @GetMapping
    public ResponseEntity<IMyPage<UserDtoOut>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "25") Integer size) {
        PageRequest request = PageRequest.of(page, size);
        return null;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDtoOut> get(@PathVariable UUID uuid) {
        return null;
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void update(@PathVariable(name = "uuid") UUID uuid,
                       @PathVariable(name = "dt_update") Long dtUpdate,
                       @RequestBody UserDto dto) {

    }
}
