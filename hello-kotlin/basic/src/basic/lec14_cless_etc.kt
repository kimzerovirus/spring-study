package basic

fun main(){
    val dto1 = PersonDto("사람", 20)
    val dto2 = PersonDto(name = "사람", age = 20)

    println(dto1 == dto2) //true
}

/**
 * data class
 */

// equals 와 toString 을 자동으로 만들어주는 data class
// named argument 를 활용하면 builder pattern 을 사용하는 것과 같은 효과를 낼 수 있다.
data class PersonDto(
    val name: String,
    val age: Int,
)

/**
 * enum class
 */

enum class Country(
    private val code: String
){
    KOREA("KO"),
    AMERICA("US");

}

private fun handleCountry(country: Country) {
    when (country) { // 컴파일러가 country 의 모든 타입을 알고 있어 다른 타입에 대한 로직(else 처리)을 작성하지 않아도 된다.
        Country.KOREA -> TODO()
        Country.AMERICA -> TODO()
    }
}

/**
 * Sealed class, Sealed interface
 * - 컴파일 타임 때 하위 클래스의 타입을 모두 기억한다.
 * - 따라서 런타임때 클래스 타입이 추가될 수 없다. // abstract class 와 비슷하지만 이 부분이 다르다
 * - 하위클래스는 같은 패키지에 있어야 한다.
 *
 * Enum 과 다른점
 * - 클래스를 상속받을 수 있다.
 * - 하위 클래스는 멀티 인스턴스가 가능하다.
 */
sealed class HyundaiCar(
    val name: String,
    val price: Long
)

class Avante :HyundaiCar("아반떼", 1_000L)
class Sonata :HyundaiCar("소나타", 2_000L)
class Grandeur :HyundaiCar("그랜져", 3_000L)

private fun handleCar(car: HyundaiCar) { // 추상화가 필요한 Entity or DTO 에 sealed class 를 활용
    when (car) {
        is Avante -> TODO()
        is Sonata -> TODO()
        is Grandeur -> TODO()
    }
}