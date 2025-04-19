package me.kzv.practice.pdf

interface TextParser {
    fun parse(text: String): List<String>
}

class DefaultTextParser : TextParser {
    override fun parse(text: String): List<String> {
        // 1. 괄호 안 내용 제거: [ ... ] 와 < ... >
        val cleanedText = text
            .replace(Regex("""\[[\s\S]*?]"""), "")  // [ ... ] 전역 제거 (줄바꿈 포함)
            .replace(Regex("""<.*?>"""), "")        // < ... > 제거

        // 2. 조문 추출
        val clauseRegex = Regex("""^(제\d+조\(.*?\))\s*(.*)""")  // 조문명 포함
        val lines = cleanedText.lines()
        val clauses = mutableListOf<String>()
        var currentContent = StringBuilder()

        for (line in lines.map { it.trim() }) {
            if (line.isBlank()) continue

            val match = clauseRegex.find(line)
            if (match != null) {
                // 새 조문 시작 → 이전 것 저장
                if (currentContent.isNotEmpty()) {
                    clauses.add(currentContent.toString().trim())
                    currentContent = StringBuilder()
                }

                val title = match.groupValues[1] // 제n조(조문명)
                val content = match.groupValues[2]

                currentContent.append(title)
                if (content.isNotBlank()) {
                    currentContent.append(content.trim())
                }
            } else {
                // 조문 본문 이어붙이기
                currentContent.append(line)
            }
        }

        // 마지막 조문 저장
        if (currentContent.isNotEmpty()) {
            clauses.add(currentContent.toString().trim())
        }

        return clauses
    }
}