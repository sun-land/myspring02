package com.sparta.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PageController {
    @GetMapping("/")
    public String getAllPostPage() {
        return "index";
    }

    @GetMapping("/user/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/posts/{postId}/detail")
    public String getDetailPage(Model model, @PathVariable Long postId) {
        model.addAttribute("postId",postId);
        return "detail";
    }

    @GetMapping("/write")
    public String writePage() {
        return "write";
    }
}
