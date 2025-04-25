package me.kzv.practice

import me.kzv.practice.file.FileWriter
import me.kzv.practice.text.TextFileProcessor
import me.kzv.practice.text.TextFileReader
import org.junit.jupiter.api.Test
import org.springframework.ai.chat.client.ChatClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource

@SpringBootTest
class GptTest {

    @Autowired
    lateinit var chatClient: ChatClient

    val question = """
변호사에게 상담 받는다 생각하고 너가 처한 사건을 해결하기 위한 법률 자문 질문을 만들어줘. 답변은 아래와 같은 json 형태로 할 것
{"title": "제62조 (이사의 대리인 선임)", "question": "이사가 외부인에게 우리 법인의 계약 권한을 넘겼어요. 이 계약이 법적으로 문제가 없나요?", "description": "정관이나 총회 결의로 금지되지 않은 한 이사는 특정 행위를 타인에게 대리시킬 수 있음", "agentType": "GPT"} 다음은 법 조항 목록이야. 법 조항 하나당 하나의 질문 데이터를 만들어줘
    """.trimIndent()

    @Test
    fun `법률 질문 생성하기`() {
        val lines = TextFileReader().readFileLines("sample.txt")
        val chunkList = TextFileProcessor().chunkList(lines)
        for (chunk in chunkList) {
            val content = question + TextFileProcessor().listToNewlineString(chunk)

            val chatResponse = chatClient.prompt().user(content).call().chatResponse()
            println(chatResponse)
            FileWriter().writeStringToFile(chatResponse.toString(), ClassPathResource("sample.json").path, true)
        }

    }
}