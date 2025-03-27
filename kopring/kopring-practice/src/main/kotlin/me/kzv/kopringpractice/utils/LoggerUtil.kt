package me.kzv.kopringpractice.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)

/**
 * reified type parameter 와 함께 inline 함수를 만들면 추가적으로 Class<T>를 파라미터로 넘겨줄 필요 없이 런타임에 타입 T에 접근할 수 있다
 * 추가적으로 T 클래스를 인자로 받지 않고 세팅 해주는듯
 */