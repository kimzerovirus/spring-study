package me.kzv.searchingserver.analysis


class HanguelJamoMorphTokenizer private constructor() {
    fun tokenizer(source: String, jamoType: String?): String {
        var jamo = ""
        jamo = when (jamoType) {
            "CHOSUNG" -> chosungTokenizer(source)
            "JUNGSUNG" -> jungsungTokenizer(source)
            "JONGSUNG" -> jongsungTokenizer(source)
            "KORTOENG" -> convertKoreanToEnglish(source)
            else -> jamoTokenizer(source)
        }
        return jamo
    }

    fun jamoTokenizer(source: String): String {
        var jamo = ""
        var criteria: Int
        var sourceChar: Char
        var jamoIdx: Char
        for (element in source) {
            sourceChar = element
            if (sourceChar.toInt() >= 0xAC00) {
                criteria = sourceChar.code - 0xAC00
                jamoIdx = ((criteria - criteria % 28) / 28 / 21).toChar()
                jamo += CHOSUNG[jamoIdx.code]
                jamoIdx = ((criteria - criteria % 28) / 28 % 21).toChar()
                jamo += JUNGSUNG[jamoIdx.code]
                jamoIdx = ((sourceChar.toInt() - 0xAC00) % 28).toChar()
                // NUL 문자에 대한 제거
                if (jamoIdx.code != 0) {
                    jamo += JONGSUNG[jamoIdx.code]
                }
            } else {
                if (isPossibleCharacter(sourceChar)) {
                    jamo += sourceChar
                }
            }
        }
        return jamo
    }

    fun chosungTokenizer(source: String): String {
        var chosung = ""
        var criteria: Int
        var sourceChar: Char
        var choIdx: Char
        for (element in source) {
            sourceChar = element
            if (sourceChar.toInt() >= 0xAC00) {
                criteria = sourceChar.code - 0xAC00
                choIdx = ((criteria - criteria % 28) / 28 / 21).toChar()
                chosung += CHOSUNG[choIdx.toInt()]
            } else {
                if (isPossibleCharacter(sourceChar)) {
                    chosung += sourceChar
                }
            }
        }
        return chosung
    }

    fun doubleChosungTokenizer(source: String): String {
        var chosung = ""
        var criteria: Int
        var sourceChar: Char
        var choIdx: Char
        for (element in source) {
            sourceChar = element
            if (sourceChar.toInt() >= 0xAC00) {
                criteria = sourceChar.toInt() - 0xAC00
                choIdx = ((criteria - criteria % 28) / 28 / 21).toChar()
                chosung += chosungDoubleToSingle(CHOSUNG[choIdx.code].toString())
            } else {
                if (isPossibleCharacter(sourceChar)) {
                    chosung += chosungDoubleToSingle(sourceChar.toString())
                }
            }
        }
        return chosung
    }

    fun chosungDoubleToSingle(chosung: String): String {
        var single = ""
        single = when (chosung) {
            "ㄲ" -> "ㄱㄱ"
            "ㄸ" -> "ㄷㄷ"
            "ㅃ" -> "ㅂㅂ"
            "ㅆ" -> "ㅅㅅ"
            "ㅉ" -> "ㅈㅈ"
            else -> chosung
        }
        return single
    }

    fun jungsungTokenizer(source: String): String {
        var jungsung = ""
        var criteria: Int
        var sourceChar: Char
        var jungIdx: Char
        for (element in source) {
            sourceChar = element
            if (sourceChar.toInt() >= 0xAC00) {
                criteria = sourceChar.code - 0xAC00
                jungIdx = ((criteria - criteria % 28) / 28 % 21).toChar()
                jungsung += JUNGSUNG[jungIdx.code]
            } else {
                if (isPossibleCharacter(sourceChar)) {
                    jungsung += sourceChar
                }
            }
        }
        return jungsung
    }

    fun jongsungTokenizer(source: String): String {
        var jongsung = ""
        var sourceChar: Char
        var jongIdx: Char
        for (element in source) {
            sourceChar = element
            if (sourceChar.toInt() >= 0xAC00) {
                jongIdx = ((sourceChar.toInt() - 0xAC00) % 28).toChar()

                // NUL 문자에 대한 제거
                if (jongIdx.code != 0) {
                    jongsung += JONGSUNG[jongIdx.toInt()]
                }
            } else {
                if (isPossibleCharacter(sourceChar)) {
                    jongsung += sourceChar
                }
            }
        }
        return jongsung
    }

    fun convertKoreanToEnglish(source: String): String {
        var english = ""
        var sourceChar: Char
        var choIdx: Int
        var jungIdx: Int
        var jongIdx: Int
        var criteria: Int
        for (i in 0 until source.length) {
            sourceChar = source[i]
            criteria = sourceChar.toInt() - 0xAC00
            choIdx = criteria / (21 * 28)
            jungIdx = criteria % (21 * 28) / 28
            jongIdx = criteria % (21 * 28) % 28
            if (sourceChar.toInt() >= 0xAC00) {
                english = english + CHOSUNG_EN[choIdx] + JUNGSUNG_EN[jungIdx]
                if (jongIdx != 0x0000) {
                    english = english + JONGSUNG_EN[jongIdx]
                }
            } else {
                if (isPossibleCharacter(sourceChar)) {
                    english = english + sourceChar
                }
            }
        }
        return english
    }

    companion object {
        // {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'}
        private val CHOSUNG = charArrayOf(
            0x3131.toChar(),
            0x3132.toChar(),
            0x3134.toChar(),
            0x3137.toChar(),
            0x3138.toChar(),
            0x3139.toChar(),
            0x3141.toChar(),
            0x3142.toChar(),
            0x3143.toChar(),
            0x3145.toChar(),
            0x3146.toChar(),
            0x3147.toChar(),
            0x3148.toChar(),
            0x3149.toChar(),
            0x314a.toChar(),
            0x314b.toChar(),
            0x314c.toChar(),
            0x314d.toChar(),
            0x314e.toChar()
        )

        // {'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'}
        private val JUNGSUNG = charArrayOf(
            0x314f.toChar(),
            0x3150.toChar(),
            0x3151.toChar(),
            0x3152.toChar(),
            0x3153.toChar(),
            0x3154.toChar(),
            0x3155.toChar(),
            0x3156.toChar(),
            0x3157.toChar(),
            0x3158.toChar(),
            0x3159.toChar(),
            0x315a.toChar(),
            0x315b.toChar(),
            0x315c.toChar(),
            0x315d.toChar(),
            0x315e.toChar(),
            0x315f.toChar(),
            0x3160.toChar(),
            0x3161.toChar(),
            0x3162.toChar(),
            0x3163.toChar()
        )

        // {' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'}
        private val JONGSUNG = charArrayOf(
            0x3131.toChar(), 0x0000.toChar(), 0x3132.toChar(), 0x3133.toChar(), 0x3134.toChar(), 0x3135.toChar(), 0x3136.toChar(), 0x3137.toChar(), 0x3139.toChar(),
            0x313a.toChar(), 0x313b.toChar(), 0x313c.toChar(), 0x313d.toChar(), 0x313e.toChar(), 0x313f.toChar(), 0x3140.toChar(), 0x3141.toChar(), 0x3142.toChar(),
            0x3144.toChar(), 0x3145.toChar(), 0x3146.toChar(), 0x3147.toChar(), 0x3148.toChar(), 0x314a.toChar(), 0x314b.toChar(), 0x314c.toChar(), 0x314d.toChar(),
            0x314e.toChar()
        )
        private val CHOSUNG_EN = arrayOf(
            "r", "R", "s", "e", "E", "f", "a", "q", "Q", "t", "T",
            "d", "w", "W", "c", "z", "x", "v", "g"
        )
        private val JUNGSUNG_EN = arrayOf(
            "k", "o", "i", "O", "j", "p", "u", "P", "h", "hk",
            "ho", "hl", "y", "n", "nj", "np", "nl", "b", "m", "ml", "l"
        )
        private const val CHOSUNG_BEGIN_UNICODE = 12593.toChar()
        private const val CHOSUNG_END_UNICODE = 12622.toChar()
        private const val HANGUEL_BEGIN_UNICODE = 44032.toChar()
        private const val HANGUEL_END_UNICODE = 55203.toChar()
        private const val NUMBER_BEGIN_UNICODE = 48.toChar()
        private const val NUMBER_END_UNICODE = 57.toChar()
        private const val ENGLISH_LOWER_BEGIN_UNICODE = 65.toChar()
        private const val ENGLISH_LOWER_END_UNICODE = 90.toChar()
        private const val ENGLISH_UPPER_BEGIN_UNICODE = 97.toChar()
        private const val ENGLISH_UPPER_END_UNICODE = 122.toChar()

        @Volatile
        private var hanguelJamoMorphTokenizer: HanguelJamoMorphTokenizer? = null
        private val JONGSUNG_EN = arrayOf(
            "", "r", "R", "rt", "s", "sw", "sg", "e", "f", "fr", "fa",
            "fq", "ft", "fx", "fv", "fg", "a", "q", "qt", "t", "T", "d", "w", "c", "z", "x", "v", "g"
        )
        private val LETTER_EN = arrayOf(
            "r", "R", "rt", "s", "sw", "sg", "e", "E", "f", "fr", "fa",
            "fq", "ft", "fx", "fv", "fg", "a", "q", "Q", "qt", "t", "T", "d", "w", "W", "c", "z", "x",
            "v", "g"
        )
        val instance: HanguelJamoMorphTokenizer?
            get() {
                if (hanguelJamoMorphTokenizer == null) {
                    synchronized(HanguelJamoMorphTokenizer::class.java) {
                        if (hanguelJamoMorphTokenizer == null) {
                            hanguelJamoMorphTokenizer = HanguelJamoMorphTokenizer()
                        }
                    }
                }
                return hanguelJamoMorphTokenizer
            }

        private fun isPossibleCharacter(c: Char): Boolean {
            return (c in NUMBER_BEGIN_UNICODE..NUMBER_END_UNICODE
                    || c in ENGLISH_UPPER_BEGIN_UNICODE..ENGLISH_UPPER_END_UNICODE
                    || c in ENGLISH_LOWER_BEGIN_UNICODE..ENGLISH_LOWER_END_UNICODE
                    || c in HANGUEL_BEGIN_UNICODE..HANGUEL_END_UNICODE
                    || c in CHOSUNG_BEGIN_UNICODE..CHOSUNG_END_UNICODE)
        }
    }
}
