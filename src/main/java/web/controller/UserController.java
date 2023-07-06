package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers (ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "showUsers";
    }

    @GetMapping("/info")
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "userInfo";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "userId", defaultValue = "0") Integer userId) {
        if (userId == 0) {
            userService.addUser(user);
        } else {
            user.setId(userId);
            userService.updateUser(user);
        }
        return "redirect:/user/";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateUser(@RequestParam(value = "userId") Integer id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "userInfo";
    }

    @RequestMapping(value ="/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteUser(@RequestParam(value = "userId") Integer id) {
        userService.deleteUser(id);
        return "redirect:/user/";
    }

}
