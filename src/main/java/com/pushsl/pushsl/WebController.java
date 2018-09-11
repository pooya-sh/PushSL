package com.pushsl.pushsl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
}
