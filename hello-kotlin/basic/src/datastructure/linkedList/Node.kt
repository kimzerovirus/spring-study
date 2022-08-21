package datastructure.linkedList

data class Node<T : Any>(
    var value: T,
    var next: Node<T>? = null
){
    override fun toString(): String {
        return if(next != null) "$value -> ${next.toString()}" else "${value}"
    }

    fun printInReverse(){
        println("=======printInReverse=======")
        next?.printInReverse()

        if (next != null) {
            print(" -> ")
        }

        print(value.toString())
    }
}
