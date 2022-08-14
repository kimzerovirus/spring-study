package datastructure

fun main(){
    val colors = arrayOfNulls<String>(5)
    colors[0] = "purple"
    colors[1] = "blue"

    println(colors.contentToString()) // <in java> Arrays.toString(colors);

    val numbers = intArrayOf(100, 200)

    for(i: Int in 1..numbers.size)
        println("${numbers[i - 1]}, ")

    for ((index, item )in numbers.withIndex())
        println("numbers[$index] = $item,")

    colors.forEach { item -> println(item) } // <in java> Arrays.stream(colors).forEach(System.out::println);

}
