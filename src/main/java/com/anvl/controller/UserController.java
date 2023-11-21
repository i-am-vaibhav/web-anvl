package com.anvl.controller;

import com.anvl.entities.User;
import com.anvl.service.CartService;
import com.anvl.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final CartService cartService;

    public UserController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }


    @ModelAttribute("user")
    public Principal getUser(Principal principal) {
        return principal;
    }

    @GetMapping("/{page}/page")
    public String getUsersPage(Model model, @PathVariable Optional<Integer> page){
        Page<User> users = userService.getUsers(page);
        model.addAttribute("users", users);
        Authentication authentication = getAuthentication();
        int totalPages = users.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("cartcount", cartService.getCountOfProduct(authentication.getName()));
        return "user/list";
    }

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

}
