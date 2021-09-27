package com.sp.fc.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/greeting")
    public String greeting() {
        return "hello";
    }
}
