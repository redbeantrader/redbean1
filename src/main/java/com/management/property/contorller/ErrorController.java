package com.management.property.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorController {
    @RequestMapping("403")
    public String unauth() {
        return "error/403";
    }
}
