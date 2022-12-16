package me.kzv.kotlinjpaquerydsl.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

/**
 * https://www.inflearn.com/questions/78106/embeddable-%EC%82%AC%EC%9A%A9%EC%8B%9C-%EC%A7%88%EB%AC%B8-%EC%9E%85%EB%8B%88%EB%8B%A4
 *
 * JPA가 FIELD, 프로퍼티(getter,setter) 엑세스 두가지 방법이 있는데요.
 * 아마 getter 같은게 보여서 정확히 판단이 어려워서 그렇게 나오는 것 같아요.
 * 참고로 프로퍼티 접근은 최근에는 권장하지 않습니다.
 * 해결방법은 2가지가 있습니다.
 * 1. @Access로 필드 접근을 명시한다.
 * 2. 첫 컬럼에 @Column으로 필드 접근 방식을 사용하고 있다는 것을 명시한다.
 *
 */

@Embeddable // 내장 타입
class Address(
    @Column val city: String,
    @Column val street: String,
    @Column val zipcode: String,
)
