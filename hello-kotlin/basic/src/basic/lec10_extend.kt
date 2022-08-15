package basic

abstract class Animal(
    protected val species: String,
    protected open val legCount: Int, // 프로퍼티를 오버라이드 할 때 추상프로퍼티가 아니라면 무조건 open 을 붙여줘야 한다.
) {
    abstract fun move()
}

class Cat(
    species: String
) : Animal(species, 4) { // extends 와 implements 는 구별없이 한칸 띄고 : 을 사용하여 상속한다.
    // 코틀린에서는 어떤 클래스를 상속받을때 무조건 상위 클래스 생성자를 호출해야한다.
    override fun move() {
        println("사뿐사뿐 걸어가~")
    }
}

class Penguin(
    species: String
) : Animal(species, 2), Swimable, Flyable {

    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 뒤뚱뒤뚱~")
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount

    override fun act() {
        super<Swimable>.act()
        super<Flyable>.act()
    }

    override fun fly() {
        println("~~~~~~~~~~~~~~~~~~~")
    }

    override val swimAbility: Int
        get() = 3
}

interface Swimable {
    val swimAbility: Int
        get() = 0 // default value 추상이라 backing field 없이 작성 가능

    fun act() {
        println("어푸 어푸~")
    }

    fun fly() // 추상 메서드
}

interface Flyable {
    fun act() {
        println("파닥 파닥~")
    }
}


/**
 * -----------------------------------------------------------------------
 */


fun main() {
    Derived(300) // 상위 클래스에서 하위클래스가 override 하고 있는 프로퍼티를 생성자 블록이나 init 블록 사용하면 이상한 값 나옴 따라서 상위클래스를 설계할 때 생성자 또는 초기화 블록에 사용되는 프로퍼티에는 open 을 피해야 한다.
}

open class Base(
    open val number: Int = 100
) {
    init {
        println("Base Class")
        println(number)
    }
}

class Derived(
    override val number: Int
) : Base(number) {
    init {
        println("Derived Class")
    }
}

