package dev.kiyari.posts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hb")
public class HeartbeatController {

    @GetMapping
    public String beat() {
        return "Heart beat";
    }
}
