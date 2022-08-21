package datastructure.linkedList

fun main() {
    val list = Node(value = 1, next = Node(value = 2, next = Node(value = 3, Node(4, Node(5)))))

//    println(list)
//
//    list.printInReverse()

//    pushExample()
//    appendExample()
//    insertExample()
//    popExample()
//    removeLastExample()
    removeAfterExample()
}

fun pushExample() {
    val list = LinkedList<Int>()

//    list.push(3)
//    list.push(8)
//    list.push(10)
//    list.push(16)

    // apply 체이닝
    list.push(3)
        .push(8)
        .push(10)
        .push(16)

    println(list)
}

fun appendExample() {
    val list = LinkedList<Int>()

    list.append(3)
        .append(8)
        .append(10)
        .append(16)

    println(list)
}

fun insertExample(){
    val list = LinkedList<Int>()

    list.push(3).push(8).push(10).push(16)

    println("Before inserting: $list")

    var middleNode = list.nodeAt(1)!!

    for (i in 1 .. 3) {
        middleNode = list.insert(-1 * i, middleNode)
    }

    println("After inserting $list")
}

fun popExample(){
    val list = LinkedList<Int>()

    list.push(3).push(2).push(1)

    println("Before popping: $list")

    val poppedValue = list.pop()

    println("After popping: $list")

    println("Popped value: $poppedValue")

}

fun removeLastExample(){
    val list = LinkedList<Int>()

    list.push(3).push(2).push(1)

    println("Before removing: $list")

    val removedValue = list.removeLast()

    println("After removing: $list")

    println("Removed value: $removedValue")

}

fun removeAfterExample(){
    val list = LinkedList<Int>()

    list.push(3).push(2).push(1)

    println("Before removing: $list")

    val index = 1
    val node = list.nodeAt(index - 1)!!
    val removedValue = list.removeAfter(node)

    println("After removing at index $index: $list")

    println("Removed value: $removedValue")

}