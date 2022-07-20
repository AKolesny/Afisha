package by.it_academy.users_service.controller;

import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.IMyPage;
import by.it_academy.users_service.dto.UserDto;
import by.it_academy.users_service.dto.UserDtoOut;
import by.it_academy.users_service.service.api.IAdminService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class AdminController {

    private final IAdminService service;

    public AdminController(IAdminService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDto dto) {
        User user = this.service.save(dto);

        return ResponseEntity.ok(user);
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
