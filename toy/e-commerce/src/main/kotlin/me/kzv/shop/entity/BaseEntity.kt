package me.kzv.shop.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy

abstract class BaseEntity(

    @CreatedBy
    var createdBy: String? = null,

    @LastModifiedBy
    var lastModifiedBy: String? = null

) : BaseTimeEntity()


/**
open : 상속 받을 수 있는 class / function.

상속받는 대상이 부모가 구현한 open fun을 그대로 사용 할 수도 있고, override하여 사용 할 수도 있다.

open class의 경우 상속받는 대상이 없어도 인스턴스화 가능.

open fun의 경우 상속받는 대상이 그대로 사용 가능. override하여 재정의 가능.



abstract: 상속 받아야만 하는 class / function.

하나 이상의 abstract fun, property가 있을 시 해당 class는 abstract로 선언해야 한다.

abstract fun, property의 경우 상속 받는 대상은 해당 abstract fun을 무조건 구현해야 하고, abstract property를 정의해야 한다.

abstract class의 경우 상속 받는 대상이 없으면 인스턴스화 불가능.
 */