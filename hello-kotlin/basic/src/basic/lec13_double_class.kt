package basic

/**
 * 중첩 클래스
 *
 * 1. static 을 사용하는 중첩 클래스
 * 2. static 을 사용하지 않는 중첩 클래스
 *  - 내부 클래스
 *      - 내부클래스는 숨겨진 외부 클래스 정보를 가지고 있어, 참조를 해지하지 못하는 경우 메모리 누수가 생길 수 있고, 이를 디버깅 하기 어렵다.
 *      - 따라서 클래스안에 내부클래스를 만들 때는 static 을 사용하기를 권장함
 *  - 지역 클래스
 *  - 익명 클래스
 */

fun main() {

}


/**
 * 권장되는 중첩 클래스 만들어보기
 */
class KotlinHouse(
    private val address: String,
    private val livingRoom: LivingRoom
) {
    class LivingRoom(
        private val area: Double
    )
}

/**
 * 권장되지 않는 중첩 클래스 만들어보기 - Java House 와 비교
 * - 코틀린은 기본적으로 바깥 클래스 참조를 하지 않으나
 *   바깥클래스를 참조하고 싶다면 inner 키워드를 사용 후 this@바깥클래스 를 통해 참조한다.
 */
class KotlinHouse2(
    private val address: String,
    private val livingRoom: LivingRoom
) {
    inner class LivingRoom(
        private val area: Double
    ){
        val address: String
        get() = this@KotlinHouse2.address
    }
}