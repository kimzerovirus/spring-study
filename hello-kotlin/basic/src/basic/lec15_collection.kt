package basic

/**
 * 가변 컬렉션 : 컬렉션에 element 를 추가, 삭제할 수 없다.
 * 불변 컬렉션 : 컬렉션에 element 를 추가, 삭제할 수 없다.
 * 다만, 불변 컬렉션이어도 Reference Type 인 Element 의 필드는 바꿀 수 있다(추가는 안되도 수정은 가능이란 소리).
 */
fun main(){
    val numbersList = listOf(100, 200)
    val emptyList = emptyList<Int>()

    printNumbers(emptyList()) // 추론 가능하므로 타입 생략

    for ((idx, value) in numbersList.withIndex()) {
        println("$idx, $value")
    }

    val numbersSet = setOf(100, 200)
    for (number in numbersSet) {
        println(number)
    }

    val mutableSet = mutableSetOf(100, 200) // 기본 구현체는 LinkedHashSet 이다.

    val oldMap = mutableMapOf<Int,String>()
    oldMap[1] = "MONDAY"
    oldMap[2] = "TUESDAY"

    mapOf(1 to "MONDAY", 2 to "TUESDAY") // to 중위 호출

    for (key in oldMap.keys) {
        println(key)
        println(oldMap[key])
    }

    for ((key, value) in oldMap.entries) {
        println(key)
        println(value)
    }
}

private fun printNumbers(numbers: List<Int>) {
    println(numbers[0]) //get() 함수 안씀
}

/**
 * 컬렉션의 null 가능성
 * - List<Int?> : 리스트에 null 이 들어갈 수 있지만, 리스트는 절대 null 이 아님
 * - List<Int>? : 리스트에는 null 이 들어갈 없지만, 리스트는 null 일 수 있음
 * - List<Int?>? : 리스트에 null 이 들어갈 수 있고 리스트도 null 일 수 있음
 *
 * 자바와 같이 사용할 경우 자바는 nullable 과 non-nullable 타입을 구분하지 않으므로 주의
 * */