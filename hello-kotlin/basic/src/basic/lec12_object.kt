package basic

fun main() {
    println(KotlinSingleton.a)
    KotlinSingleton.a +=1
    println(KotlinSingleton.a)

    moveSomething(object : Movable { // 코틀린에서 익명 클래스는 newMovable(){} 이 아닌 object: 타입이름으로 간략하게 표기한다.
        override fun move() {
            println("움직인다")
        }

        override fun fly() {
            println("난다")
        }
    })
}

/**
 * 1. static 함수와 변수 - 클래스가 인스턴스화 될 때 새로운 값이 복제되는게 아니라 정적으로 인스턴스끼리의 값을 공유
 * 2. 싱글톤
 * 3. 익명 클래스
 */

class Person2 private constructor(
    var name: String,
    var age: Int
) {
    // 코틀린은 static 이 존재하지 않고 이렇게 companion object 를 사용한다.
    // companion object : 클래스와 동행하는 유일한 오브젝트
    companion object Factory : Log {
        private const val MIN_AGE = 1 // val 은 런타임 시에 변수가 할당 되지만 const 를 붙이면 컴파일 시에 변수가 할당된다. 따라서 const 는 진짜 상수이며, 기본 타입과 String 에 붙일 수 있다.

        @JvmStatic // 이게 있으면 클래스명.Companion.함수명이 아닌 클래스명.함수명으로 바로 사용가능, 이름이 있다면 Companion 이 아닌 이름으로 접근
        fun newPerson(name: String): Person {
            return Person(name, MIN_AGE)
        }

        override fun print() {
            println("companion object 는 이름을 붙일 수도 있고 안 붙여도 되고 인터페이스를 받을 수도 있다!!")

            // 이렇게 넣어 줄 수는 있지만 보통은 최상단에 하는게 좋음
        }
    }
}

/**
 * 싱글톤
 */
object KotlinSingleton{
    var a:Int = 0
}

/**
 * 익명 클래스
 */
private fun moveSomething(movable: Movable){
    movable.move()
    movable.fly()
}
