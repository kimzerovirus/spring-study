package me.kzv.shop

import org.slf4j.Logger
import org.slf4j.LoggerFactory

// 모든 클래스에서 로그 객체를 얻을 수 있음
inline fun <reified T> T.logger(): Logger{
    return LoggerFactory.getLogger(T::class.java)
}