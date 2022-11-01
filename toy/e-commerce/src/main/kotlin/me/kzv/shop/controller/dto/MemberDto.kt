package me.kzv.shop.controller.dto

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class MemberDto(
    val name: @NotBlank(message = "이름은 필수 입력 값입니다.") String,

    @field:NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @field:Email(message = "이메일 형식으로 입력해주세요.")
    val email: String,

    @field:NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @field:Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    val password: String,

    @field:NotEmpty(message = "주소는 필수 입력 값입니다.")
    val address: String,
)