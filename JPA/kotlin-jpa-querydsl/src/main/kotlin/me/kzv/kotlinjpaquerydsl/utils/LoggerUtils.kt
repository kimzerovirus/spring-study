package me.kzv.kotlinjpaquerydsl.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 모든 Kotlin 클래스에서 SLF4J의 Logger 객체를 얻을 수 있다.
 * https://hippolab.tistory.com/69
 */
inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}