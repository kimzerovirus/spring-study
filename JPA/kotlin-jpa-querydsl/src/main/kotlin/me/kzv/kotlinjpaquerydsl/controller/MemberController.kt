package me.kzv.kotlinjpaquerydsl.controller

import jakarta.validation.Valid
import me.kzv.kotlinjpaquerydsl.controller.dto.MemberForm
import me.kzv.kotlinjpaquerydsl.entity.Address
import me.kzv.kotlinjpaquerydsl.entity.Member
import me.kzv.kotlinjpaquerydsl.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController(
    private val memberService: MemberService,
){
    @GetMapping("/members/new")
    fun createForm(model: Model): String {
        model.addAttribute("memberForm", MemberForm("","","",""))

        return "members/createMemberForm"
    }

    @PostMapping("/members/new")
    fun create(@Valid form: MemberForm, result: BindingResult): String {
        if (result.hasErrors()) {
            return "members/createMemberForm"
        }

        val address = Address(form.city, form.street, form.zipcode)
        val member = Member(name = form.name, address = address)
        memberService.join(member)

        return "redirect:/"
    }

    @GetMapping("/members")
    fun list(model: Model): String {
        val members = memberService.findMembers()
        model.addAttribute("members", members)

        return "members/memberList"
    }
}