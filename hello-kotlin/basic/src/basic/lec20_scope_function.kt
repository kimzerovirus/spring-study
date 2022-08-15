package basic

// TODO 솔직히 그냥 안쓰고 작성하는게 더 가독성이 나은듯

fun main() {
    val person = Person("닝겐", 999)

    val value1 = person.let { it.age } // let { p -> p.age } // 일반 함수를 받으므로 파라미터명을 직접 설정할 수 있다.
    val value2 = person.run { this.age } // run { age } // 확장함수에서는 본인 자신을 this 로 호출하고 생략가능하다.
    val value3 = person.also { it.age }
    val value4 = person.apply { this.age }

    println("let = $value1 ") //age
    println("run = $value2 ") // age
    println("also = $value3 ") // Person
    println("apply = $value4") // Person

    with(person) {
        println(name)
        println(this.age)
    }
}

/**
 * scope function 이란?
 *
 * 람다를 사용해 일시적인 영역을 만들고
 * 코드를 더 간결하게 만들거나, method chaining 에 활용하는 함수를 말한다.
 *
 * 확장함수(: 멤버함수인것처럼 사용가능하다는 특징이 있음)
 * - let, run 람다의 결과
 * - also, apply 객체 그 자체
 *
 * 확장x
 * - with
 *
 * this : 생략이 가능한 대신, 다른 이름을 붙일 수 없다.
 * it : 생략이 불가능한 대신, 다른 이름을 붙일 수 있다.
 */

fun printPerson(person: Person?) {
    person?.let { // Person 이 null 일 수도 있으므로 safe call
        println(it.name)
        println(it.age)
    }

    if (person != null) {
        println(person.name)
        println(person.age)
    }
}

/** let : 하나 이상의 함수를 call chain 결과로 호출 할 때 */
fun func1(){
    val strings = listOf("APPLE", "CAR")
    strings.map{it.length}
        .filter { it >3 }
        .let(::println)
        .let {lengths -> println(lengths)}
}

/** let : non-null 값에 대해서만 code block 을 실행시킬 때 */
fun func2(str: String?){
    val length = str?.let{
        println(it.uppercase())
        it.length
    }

    println(length)
}

/** let : 일회성으로 제한된 영역에 지역 변수를 만들 때 */
fun func3(){
    val numbers = listOf("one", "two","three")
    val modifiedFirstItem = numbers.first()
        .let { firstItem ->
            if(firstItem.length>=5) firstItem else "!$firstItem!"
        }.uppercase()

    println(modifiedFirstItem)
}

/** run : 객체를 만들어 DB에 바로 저장하고, 그 인스턴스를 활용할 때 */
fun func4(){
//    val person = Person("닝겐", 999).run(personRepository::save)
//    근데 굳이?
//    val person = personRepository.save(Person("닝겐", 999) 가 낫지
}

/** with : 특정 객체를 달느 객체로 변환해야 하는데, 모듈 간의 의존성에 의해 정적 팩토리 혹은 toClass 함수를 만들기 어려울 때 */
