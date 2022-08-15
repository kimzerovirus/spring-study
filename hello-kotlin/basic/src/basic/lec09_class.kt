package basic

import java.lang.IllegalArgumentException

class KotlinPerson constructor (name: String, age: Int){ // constructor 지시어는 생략 가능하다, 생성자 안에서 필드 생성가능

    var name: String = name
        set(value) {
            field = value
        }

    var age: Int = age

    /**
     * 코틀린에서는 필드만 만들면 getter, setter 를 자동으로 만들어준다.
     */
}

// 위의 코드를 최대한 요약하면
class KotlinPerson2( // 주 생성자
    val name: String,
    var age: Int,
    customName: String = "custom name"
){
    val customName = customName
        get() = field.uppercase() // 자기 자신을 가리키는 예약어 field(backing field, 여기서는 name) field 대신에 this.name.uppercase() 로 호출해도 됨

    fun getUppercaseCustomName(): String = this.customName.uppercase() // 굳이 위와 같이 안써도 충분히 표현 가능

    init { // 생성자가 호출되는 시점에 호출되므로 검증로직등을 넣어주면 좋다
        if (age <= 0) {
            throw IllegalArgumentException("나이는 1살 이상이어야 됩니다.")
        }
        println("초기화 블록")
    }

    constructor(name: String) : this(name, 1){ // 부 생성자
        println("부 생성자1")
    }
    constructor():this("헤헷"){
        println("부 생성자2")
        println("헤헷")
    }

/*
    fun isAdult(): Boolean{
        return this.age >=20
    }
*/
    /**
     * custom getter, setter
     * 객체의 속성이라면, custom getter 를 사용하고
     * 아니라면 함수 형태로 작성하는 방법도 괜찮을듯
     */
    val isAdult: Boolean get() = this.age >= 20 // get(){ return this.age>=20 }
    fun isAdultFunc(): Boolean = this.age>= 20

}

fun main(){
    val person = KotlinPerson("사람1", 20)
    println(person.name)
    person.age = 10
    println(person.age)

    val baby = KotlinPerson2("아기1")
    println(baby.name)
    println(baby.age)

    KotlinPerson2()
}

/**
 * 코틀린은 부생성자를 사용하기 보다는 default parameter 를 사용하기를 권장한다.
 */