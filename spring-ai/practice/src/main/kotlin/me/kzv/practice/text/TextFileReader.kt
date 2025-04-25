package me.kzv.practice.text

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import java.io.*
import java.nio.charset.StandardCharsets

class TextFileReader {
    /**
     * 클래스패스 내의 텍스트 파일을 읽어서 각 행을 리스트에 담아 반환합니다.
     *
     * @param filePath 클래스패스 기준 파일 경로 (예: "data/sample.txt")
     * @return 파일의 각 행이 담긴 리스트
     * @throws IOException 파일 읽기 오류 발생 시
     */
    fun readLinesFromFile(filePath: String): List<String> {
        // 클래스패스 리소스로 파일 접근
        val resource: Resource = ClassPathResource(filePath)
        val lines = ArrayList<String>()

        // use 함수를 사용하여 자원 자동 해제 (코틀린의 try-with-resources)
        BufferedReader(
            InputStreamReader(resource.inputStream, StandardCharsets.UTF_8)
        ).use { reader ->
            // 코틀린 스타일로 파일 읽기
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                line?.let { lines.add(it) }
            }

            // 또는 더 간결한 방식 (대안)
            // return reader.lineSequence().toList()
        }

        return lines
    }

    /**
     * 외부 경로의 텍스트 파일을 읽어서 각 행을 리스트에 담아 반환합니다.
     *
     * @param absolutePath 파일의 절대 경로
     * @return 파일의 각 행이 담긴 리스트
     * @throws IOException 파일 읽기 오류 발생 시
     */
    fun readLinesFromAbsolutePath(absolutePath: String): List<String> {
        val lines = ArrayList<String>()

        BufferedReader(
            InputStreamReader(
                java.io.FileInputStream(absolutePath), StandardCharsets.UTF_8
            )
        ).use { reader ->
            // 코틀린의 파일 읽기 유틸리티를 활용한 방식
            return reader.lineSequence().toList()
        }
    }

    /**
     * 코틀린 확장 함수를 사용한 더 간결한 방식
     */
    fun readFileLines(filePath: String): List<String> {
        val resource = ClassPathResource(filePath)
        return resource.inputStream.bufferedReader(StandardCharsets.UTF_8).use { it.readLines() }
    }
}