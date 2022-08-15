package basic

/**
 * 1. Type Alias 와 as import
 * 2. 구조분해와 componentN 함수
 * 3. Jump 와 Label
 * 4. TakeIf 와 TakeUnless 확장함수
 */


/** 1 */
typealias FruitFilter = (Fruit) -> Boolean

fun filterFruits(fruits: List<KotlinFruit>, filter: (KotlinFruit) -> Boolean) {}
fun filterFruits2(fruits: List<KotlinFruit>, filter: FruitFilter) {}

data class UltraSuperDupaDiva(
    val name: String
)

typealias USDDMap = Map<String, UltraSuperDupaDiva> // 자료형을 편하게 줄일 수 있음
// 서로 다른 패키지이지만 같은 이름의 함수가 있다면 import 패키지.함수 as 원하는 이름으로 가져오자


/** 2 */
data class Person123( // data class 는 componentN 함수가 자동으로 만들어진다
    val name: String,
    val age: Int
)

// 데이터 클래스가 아닌데 구조분해를 사용하고 싶다면 직접 componentN 함수를 구현하면 된다.
// componentN 함수는 연산자의 속성을 가지고 있으므로 operator 를 붙여줘야 한다.
class DefaultPerson(
    val dname: String,
    val dage: Int
) {
    operator fun component1(): String = this.dname
    operator fun component2(): Int = this.dage
}

fun samplePerson() {
    val person = Person123("사람", 18)
    val (name, age) = person
//    val name = person.component1()
//    val age = person.component2()

    val dPerson = DefaultPerson("인간", 236)
    val (dname, dage) = dPerson

    println("$name : $age")
    println("$dname : $dage")
}


/** 3
 *  - return : 기본적으로 가장 가까운 enclosing function 또는 익명함수로 값이 반환된다.
 *  - break : 가장 가까운 루프가 제거된다.
 *  - continue : 가장 가까운 루프를 다음 step 으로 보낸다.
 *
 *  일반적으로 forEach 에서는 사용불가
 * */
fun sample3() {
    val numbers = listOf(1, 2, 3)
    numbers.map { number -> number + 1 }
        .forEach { number -> println(number) }

    for (number in numbers) {
        if (number == 2) {
            break
        }
    }

    run {
        numbers.forEach {number ->
            if (number == 2) {
                // break
                return@run
            }else{
                // continue
                return@forEach
            }

        }
    }

    /** 라벨링 */
    abc@ for (i in 1..100) {
        for (j in 1..100) {
            if (j == 2) {
                break@abc // 가장 가까운 for 문 브레이크가 아닌 라벨링 된 for 문을 브레이크 한다.
            }
            println("$i $j")
        }
    }
}

/** 4 */
// method chaining
class Sample4 (val number: Int?){
    fun getNumberOrNull(): Int? {
        return if (number!! <= 0) {
            null
        }else{
            number
        }
    }

    // 주어진 조건을 만족하면 그 값이, 만족하지 않으면 null 반환
    fun getNumberOrNull2(): Int? {
        return number.takeIf { it!! > 0 }
    }

    // 주어진 조건을 만족하지 않으면 그 값이, 만족한다면 null 반환
    fun getNumberOrNull3(): Int? {
        return number.takeUnless { it!! <= 0 }
    }
}

