package me.kzv.issue.domain.enums

enum class IssueType {

    BUG, TASK;

    companion object {
        operator fun invoke(type: String) = valueOf(type.uppercase())
    }

//    companion object {
//        fun of(type: String) = valueOf(type.uppercase())
//    }
}
//
//fun main(){
//    val type = IssueType.of("BUG")
//    IssueType.BUG == type
//}

    // 일반 함수와 operator 비교
//fun main(){
//    val type = IssueType("BUG")
//    IssueType.BUG == type
//}