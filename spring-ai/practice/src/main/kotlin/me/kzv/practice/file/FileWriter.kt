package me.kzv.practice.file

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class FileWriter {
    fun writeStringToFile(content: String, filePath: String, append: Boolean = false) {
        val path = Paths.get(filePath)

        // 상위 디렉토리가 존재하지 않으면 생성
        path.parent?.let {
            if (!Files.exists(it)) {
                Files.createDirectories(it)
            }
        }

        // 옵션 설정 (덮어쓰기 또는 추가)
        val options = if (append) {
            arrayOf(StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        } else {
            arrayOf(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
        }

        // 파일에 쓰기
        Files.write(path, content.toByteArray(StandardCharsets.UTF_8), *options)
    }
}