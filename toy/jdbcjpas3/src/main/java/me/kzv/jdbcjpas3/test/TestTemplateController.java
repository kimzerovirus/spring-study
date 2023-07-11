package me.kzv.jdbcjpas3.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class TestTemplateController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {
        TestMember testMember = new TestMember(1L, "testName");

        model.addAttribute("test", testMember);
        model.addAttribute("today", LocalDate.now());

        return "example";
    }
}
