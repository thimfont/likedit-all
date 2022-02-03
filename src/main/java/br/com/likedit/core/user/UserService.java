package br.com.likedit.core.user;

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

    public void save(final User user) {
        userRepository.save(user);
    }

    public void update(final User user) {
        this.save(user);
    }

    public List<User> all() {
        return userRepository.findAll();
    }
}
