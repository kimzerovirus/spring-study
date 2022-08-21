package datastructure.stack

class StackImpl<T : Any> : Stack<T> {

    private var storage = arrayListOf<T>()

    override val count: Int
        get() = storage.size

    override val isEmpty: Boolean
        get() = super.isEmpty

    override fun pop(): T? = storage.removeLastOrNull()

    override fun push(element: T) {
        storage.add(element)
    }

    override fun peek(): T? = storage.lastOrNull()

}

fun String.isValidParentheses(): Boolean {
    val stack = StackImpl<Char>()

    for (char in this) {
        when (char) {
            '(' -> {
                stack.push(char)
            }
            ')' -> {
                if (stack.isEmpty) {
                    return false
                } else {
                    stack.pop()
                }
            }
        }
    }

    return stack.isEmpty
}