package me.kzv.kotlinjpaquerydsl.controller

import me.kzv.kotlinjpaquerydsl.utils.logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun home(): String{
        logger().info("home controller")

        return "home"
    }

}