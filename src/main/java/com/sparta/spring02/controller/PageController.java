package com.sparta.spring02.controller;

import com.sparta.spring02.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class PageController {
    @GetMapping("/")
    public String getAllPostPage(Model model,@AuthenticationPrincipal UserDetailsImpl userDetails) {

        // userdetails에서 username 가져옴
        model.addAttribute("username",userDetails.getUsername());
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
