package datastructure.queue

interface Queue<T> {
    val count: Int
    val isEmpty: Boolean
        get() = count == 0

    fun peek(): T?

    fun enqueue(element: T): Boolean

    fun dequeue(): T?

}