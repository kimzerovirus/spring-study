package basic

/**
 * 코틀린에서는 함수가 1급 시민이다. (자바는 2급 시민)
 *
 * 코틀린 람다
 * - 코틀린에서 람다는 두 가지 방법으로 만들 수 있고, {} 방법이 더 선호된다.
 * - 함수를 호출하며, 마지막 파라미터인 람다를 쓸 때는 소괄호 밖으로 람다를 뺄 수 있다.
 * - 람다의 마지막 expression 결과는 람다의 반환 값이다.
 * - 코틀린에서는 Closure 를 사용하여 non-final(가변) 변수도 람다에서 사용할 수 있다.
 */
fun main() {
    val fruits = listOf(
        Fruit("사과", 1_000),
        Fruit("사과", 1_100),
        Fruit("사과", 1_200),
        Fruit("바나나", 2_000),
        Fruit("포도", 2_100),
        Fruit("수박", 2_200),
        Fruit("자두", 2_900),
        Fruit("복숭아", 3_100),
        Fruit("멜론", 3_200),
    )

    // 람다 사용법 1
    val isApple: (Fruit) -> Boolean = fun(fruit: Fruit): Boolean { // lamda 익명함수
        return fruit.name == "사과"
    }

    // 람다 사용법 2
    val isApple2: (Fruit) -> Boolean = { fruit: Fruit -> fruit.name == "사과" }

    // 람다함수 호출하기 1
    isApple(fruits[0])

    // 람다 함수 호출하기 2 (invoke 를 명시적으로 사용하여 호출하기)
    isApple.invoke(fruits[0])

    filterFruits(fruits, isApple)
    filterFruits(fruits) { it.name == "사과" } // 소괄호 밖의 중괄호는 마지막 파리머터의 값으로 들어간다. {fruit -> fruit.name == "사과"} 의 경우 fruit 이라는 변수 하나므로 it 이라는 변경 가능
}

private fun filterFruits(
    fruits: List<Fruit>,
    filter: (Fruit) -> Boolean
): List<Fruit> {
    val results = mutableListOf<Fruit>()
    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }

    return results
}