package br.com.likedit.ui;

import br.com.likedit.core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String home(final Model model) {
        model.addAttribute("users", userService.all());
        return "users/users";
    }
}
