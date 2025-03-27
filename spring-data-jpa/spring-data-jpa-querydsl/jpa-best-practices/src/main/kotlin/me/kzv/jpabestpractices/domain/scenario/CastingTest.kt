package me.kzv.jpabestpractices.domain.scenario

open class Parent {
    val name: String = "parent"
    val age: Int = 60
}

class Child: Parent() {
    val childAge: Int = 30
}

fun main() {
    val child = Child()
    val parent = Parent()

    val newParent = child as Parent // 업캐스팅

    val newChild1 = newParent as Child // 다운캐스팅
    println(newChild1.childAge) // 30

    val newChild2 = parent as Child
    println(newChild2.childAge) // ClassCastException - 다운캐스팅이란 업캐스팅으로 타입 변환을 한 객체에 대해 원본 타입으로 변환하는 과정
}