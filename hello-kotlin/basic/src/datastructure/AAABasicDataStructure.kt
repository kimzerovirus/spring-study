package datastructure

import java.util.*
import kotlin.collections.ArrayList

fun dataStructure() {

    /** 1. Array */
    val array: Array<Int> = arrayOf(1, 2, 3)



    /** 2. List */
    val lists: List<Int> = listOf(1, 2, 3)

    /** 2.1 Immutable List */
    val immutableList = listOf(1, 2, 3)

    /** 2.2 Mutable List */
    val mutableList = mutableListOf(1, 2, 3)
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/

    /** 2.3 ArrayList */
    val arrayList: ArrayList<Int> = arrayListOf(1, 2, 3) // ArrayList 는 MutableList 인터페이스를 상속받은 구현체이다.
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/



    /** 3. Stack */
    val stack: Stack<Int> = Stack()



    /** 4. Queue */
    // Queue 는 클래스가 아닌 인터페이스로 이를 구현하는 다양한 클래스가 존재함 (AbstractQueue, LinkedList...)
    val queue: Queue<Int> = LinkedList()



    /** 5. LinkedList */
    val linkedList: LinkedList<Int> = LinkedList()



    /** 6. Set */
    val sets: Set<Int> = setOf(1, 2, 3)

    /** 6.1 Mutable Set */
    val mutableSet: Set<Int> = mutableSetOf(1,2,3) // 기본 구현체는 LinkedHashSet 이다.



    /** 7. Map */
    val map = mapOf(1 to "One", 2 to "two") // to 중위 호출

    /** 7.1 MutableMap */
    val mutableMap = mutableMapOf(1 to "One", 2 to "two")

    val oldMap = mutableMapOf<Int,String>()
    oldMap[1] = "one"
    oldMap[2] = "two"

}