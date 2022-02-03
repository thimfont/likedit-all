package br.com.likedit.core.user;

import br.com.likedit.core.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(final User user) {
        return userRepository.save(user);
    }

    public User update(final long userId, final User user) {
        user.setId(userId);
        return this.save(user);
    }

    public User findById(final long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> all() {
        return userRepository.findAll();
    }
}
