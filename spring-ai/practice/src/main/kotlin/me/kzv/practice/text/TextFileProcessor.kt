package me.kzv.practice.text

import org.springframework.core.io.ClassPathResource
import java.nio.charset.StandardCharsets

class TextFileProcessor {

    /**
     * 파일을 읽어서 각 행을 리스트에 담고, 10개씩 나눈 2차원 리스트로 반환합니다.
     *
     * @param filePath 클래스패스 기준 파일 경로 (예: "data/sample.txt")
     * @param chunkSize 자를 단위 크기 (기본값 10)
     * @return 10개씩 그룹화된 2차원 리스트
     * @throws IOException 파일 읽기 오류 발생 시
     */
    fun readAndChunkFileLines(filePath: String, chunkSize: Int = 10): List<List<String>> {
        // 파일에서 모든 줄 읽기
        val allLines = readFileLines(filePath)

        // 10개씩 청크로 나누기
        return allLines.chunked(chunkSize)
    }

    /**
     * 파일의 모든 줄을 읽어 리스트로 반환합니다.
     */
    private fun readFileLines(filePath: String): List<String> {
        val resource = ClassPathResource(filePath)
        return resource.inputStream.bufferedReader(StandardCharsets.UTF_8).use { it.readLines() }
    }

    /**
     * 이미 로드된 리스트를 지정된 크기로 청크화하여 2차원 리스트로 반환합니다.
     */
    fun chunkList(list: List<String>, chunkSize: Int = 10): List<List<String>> {
        return list.chunked(chunkSize)
    }

    fun listToNewlineString(list: List<String>): String {
        return list.joinToString(separator = "\n")
    }
}