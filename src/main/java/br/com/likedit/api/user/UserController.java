package br.com.likedit.api.user;

import br.com.likedit.core.user.User;
import br.com.likedit.core.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> all() {
        return ResponseEntity.ok(userService.all().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> details(@PathVariable final Long userId) {
        log.info("Looking user #{}", userId);
        final User userFound = userService.findById(userId);
        return ResponseEntity.ok().body(new UserDTO(userFound));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody final UserDTO dto) {
        log.info("Creating user #{}", dto);
        final User userSaved = userService.save(dto.convert());
        log.info("Created user #{}", userSaved);
        return ResponseEntity.ok(new UserDTO(userSaved));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> update(@PathVariable final Long userId,
                                          @RequestBody final UserDTO dto) {
        log.info("Updating user with id #{}", userId);
        User userUpdated = userService.update(userId, dto.convert());
        log.info("Updated user with id #{}", userId);
        return ResponseEntity.ok(new UserDTO(userUpdated));
    }
}
