package me.kzv.kotestcore

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

// TODO - BDD 스타일 MockK, Spring에서의 kotest도 사용해보기

class SampleKoTest : FunSpec({
    test("1 더하기 1은 2이다.") {
        val actual = 1 + 1

        actual shouldBe 2
    }

    test("1 더하기 1은 3이 아니다.") {
        val actual = 1 + 1

        actual shouldNotBe 3
    }
})