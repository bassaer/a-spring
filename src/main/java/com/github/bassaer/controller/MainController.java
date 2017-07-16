package com.github.bassaer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main controller
 * Created by nakayama on 2017/07/15.
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public String main() {
        return "main";
    }
}
