package datastructure.queue

class ArrayListQueue<T> : Queue<T> {
    private val storage = arrayListOf<T>()

    override val count: Int
        get() = storage.size

    override val isEmpty: Boolean
        get() = super.isEmpty

    override fun enqueue(element: T): Boolean = storage.add(element)

    override fun dequeue(): T? = if(isEmpty) null else storage.removeAt(0)

    override fun peek(): T? = storage.getOrNull(0)
}