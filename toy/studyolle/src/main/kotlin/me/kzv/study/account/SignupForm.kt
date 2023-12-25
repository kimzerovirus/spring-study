package me.kzv.study.account

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class SignupForm(
    @field:NotBlank
    @field:Length(min = 3, max = 20)
    @field:Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-](3,20)$")
    val nickname: String,

    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank
    @field:Length(min = 8, max = 50)
    val password: String,
)
