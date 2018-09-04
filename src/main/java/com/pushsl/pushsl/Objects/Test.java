package com.pushsl.pushsl.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test {

    @GetMapping("/test")
    public ModelAndView test(){
        int[] array = new int [] {1,2,3,4};
        return  new ModelAndView("index")
                .addObject("array", array);
    }
}
