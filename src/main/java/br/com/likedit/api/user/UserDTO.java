package br.com.likedit.api.user;

import br.com.likedit.core.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private long id;
    private String name;

    public UserDTO(final User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public User convert() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}

// TODO: implement lib to make the mapper and remove these converts, construct and method
