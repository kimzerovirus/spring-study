package datastructure.stack

interface Stack<T:Any> {

    val count: Int
    val isEmpty: Boolean
        get() = count == 0

    fun pop(): T?

    fun push(element: T)

    fun peek(): T?
}