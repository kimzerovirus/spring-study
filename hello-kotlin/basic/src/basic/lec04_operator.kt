package basic

fun main(){
    val money1 = JavaMoney(2_000L)
    val money2 = JavaMoney(1_000L)
    val money3 = money1
    val money4 = JavaMoney(2_000L)

    if (money1 > money2) { // 비교연산자를 호출하면 compareTo를 자동으로 호출한다.
        println("Money1이 Money2보다 금액이 큽니다.")
    }

    println(money1 == money2)
    println(money1 == money3)
    println(money1 === money3)
    println(money1 == money4)

// 동일성 : 완전히 동일한 객체인가, 주소값이 같은 객체인가

// 동등성 : 두 객체의 값이 같은가


/*
    Java에서는
     - 동일성 ==
     - 동등성 equals

    kotlin에서는
     - 동일성 ===
     - 동등성 ==
 */


    if (fun1() || fun2()) {
        println("본문")
    }

    if (fun1() && fun2()) {
        println("본문")
    }


    // in !in
    // 컬렉션이나 범위에 포함되어 있다, 포함되어 있지 않다

    // a..b
    // a부터 b까지의 범위 객체를 생성한다.


    // 연산자 오버로딩
    val m1 = Money(1_000L)
    val m2 = Money(1_000L)
    println(m1 + m2)
}


fun fun1(): Boolean {
    println("fun 1")
    return true
}

fun fun2(): Boolean {
    println("fun 1")
    return false
}

