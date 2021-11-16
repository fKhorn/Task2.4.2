package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String getLogin() {
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String getLogout() {
        return "redirect:/login";
    }
}
