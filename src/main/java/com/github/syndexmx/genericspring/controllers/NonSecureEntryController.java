package com.github.syndexmx.genericspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class NonSecureEntryController {

    @GetMapping("/unsecure")
    public String unsecureEnter() {
        return "Unsecured enter";
    }

    @GetMapping("/info")
    public String userInfo(Principal principal) {
        return principal.getName();
    }
}
