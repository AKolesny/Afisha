package by.it_academy.users_service.controller;

import by.it_academy.users_service.dao.entity.User;
import by.it_academy.users_service.dto.IMyPage;
import by.it_academy.users_service.dto.UserDto;
import by.it_academy.users_service.dto.UserDtoOut;
import by.it_academy.users_service.service.api.IAdminMapper;
import by.it_academy.users_service.service.api.IAdminService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class AdminController {

    private final IAdminService service;
    private final IAdminMapper mapper;

    public AdminController(IAdminService service, IAdminMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<UserDtoOut> save(@RequestBody UserDto dto) {
        UserDtoOut user = this.mapper.mapDtoOut(this.service.save(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<IMyPage<UserDtoOut>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "25") Integer size) {
        PageRequest request = PageRequest.of(page, size);

        IMyPage<UserDtoOut> myPage = this.mapper.mapPage(this.service.getAll(request));
        return ResponseEntity.ok(myPage);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDtoOut> get(@PathVariable UUID uuid) {
        return ResponseEntity.ok(this.mapper.mapDtoOut(this.service.get(uuid)));
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<UserDtoOut> update(@PathVariable(name = "uuid") UUID uuid,
                       @PathVariable(name = "dt_update") Long dtUpdate,
                       @RequestBody UserDto dto) {
        LocalDateTime update = LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneId.systemDefault());

        return ResponseEntity.ok(this.mapper.mapDtoOut(this.service.update(dto, uuid, update)));
    }
}
