package basic

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FilePrinter {

    /**
     * 자바와 달리 코틀린은 Checked Exception 과 Unchecked Exception 을 구분하지 않는다.
     * 따라서 모두다 Unchecked Exception 이다.
     */
    fun readFile() {
        val currenFile = File(".")
        val file = File(currenFile.absolutePath + "/a.txt")
        val reader = BufferedReader(FileReader(file))
        println(reader.readLine())
        reader.close()
    }

    fun readFile(path: String) {
        BufferedReader(FileReader(path)).use { reader ->
            println(reader.readLine()) // try with resources 대신 use 라는 inline 확장함수를 사용
        }
    }
}