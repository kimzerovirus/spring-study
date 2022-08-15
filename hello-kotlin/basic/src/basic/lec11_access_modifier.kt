package basic

fun main() {

}

/**
 * 자바의 접근제어
 * - public : 모든 곳에서 접근 가능
 * - protected : 같은 패키지 또는 하위 클래스에서만 접근 가능
 * - default : 같은 패키지에서만 접근 가능
 * - private : 선언된 클래스 내에서만 접근 가능
 *
 * 코틀린의 접근제어
 * - public : 모든 곳에서 접근 가능
 * - protected : 선언된 클래스 또는 하위 클래스에서만 접근 가능, 파일 최상단에서 사용 불가능 (같은 패키지 X) -> 코틀린에서는 패키지를 namespace 를 관리하기 위한 용도로만 사용되지 가시성 제어에는 사용되지 않는다.
 * - internal : 같은 모듈에서만 접근 가능 (한 번에 컴파일 되는 코틑린 코드 IDEA MODULE, MAVEN, GRADLE ...)
 * - private : 선언된 파일 내에서만 접근 가능
 *
 * 자바의 기본 접근 지시어는 default 이지만 코틀린은 public 이다.
 * internal 은 바이트 코드 상 public 이 된다. 따라서 자바 코드에서는 internal 을 마음대로 접근할 수 있게 된다.
 */


// 생성자 앞에 접근 지시어 붙이면 됨 기본은 public 생략됨
class AccessModifiedConstructor private constructor(
    internal val name: String,
    private var owner: String,
    _price: Int
){
    var price = _price
        private set
}
