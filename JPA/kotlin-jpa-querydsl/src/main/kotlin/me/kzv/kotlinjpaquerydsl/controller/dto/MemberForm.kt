package me.kzv.kotlinjpaquerydsl.controller.dto

import jakarta.validation.constraints.NotEmpty

/**
 * kotlin 에서 spring validation 사용시 문제점 - 자바 코드로 변환시 필드에 안붙고 생성자에만 붙으므로 필드라고 명시해줘야 한다!
 * https://unluckyjung.github.io/kotlin/spring/2022/06/06/kotlin-validation-annotation/
 */
data class MemberForm(

    @field:NotEmpty(message = "회원 이름은 필수입니다.")
    val name: String,

    val city: String,
    val street: String,
    val zipcode: String,
)
