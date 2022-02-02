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

        // userdetails가 null이 아니면(로그인된 사람이면) username 가져옴
        if (userDetails != null) {
            model.addAttribute("loginUsername",userDetails.getUsername());
        }
        return "index";
    }

    @GetMapping("/user/login")
    public String loginPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("loginUsername",userDetails.getUsername());
        }
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("loginUsername",userDetails.getUsername());
        }
        return "signup";
    }

    @GetMapping("/posts/{postId}/detail")
    public String getDetailPage(Model model, @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("postId",postId);
        // userdetails가 null이 아니면(로그인된 사람이면) username 가져옴
        if (userDetails != null) {
            model.addAttribute("loginUsername",userDetails.getUsername());
        }
        return "detail";
    }

    @GetMapping("/write")
    public String writePage(Model model,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("loginUsername",userDetails.getUsername());
        return "write";
    }
}
