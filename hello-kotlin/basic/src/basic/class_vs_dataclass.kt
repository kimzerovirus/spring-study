package basic

data class DataHuman(val name:String, val age:Int)
class DefaultHuman(val name:String, val age:Int)

fun main (){
    val person1 = DataHuman("안녕",12)
    val person2 = DataHuman("안녕",12)

    println(person1.equals(person2)) // true

    val person3 = DefaultHuman("안녕",12)
    val person4 = DefaultHuman("안녕",12)

    println(person3.equals(person4)) // false

    /*
        data class는 toString이 항상 붙어있는 클래스로
        항상 오버라이딩이 되는 클래스이지만
        일반 클래스는 항상 새로운 객체이기 때문에 false
     */

}