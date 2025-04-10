package me.kzv.websocketstomp.exception

import java.lang.RuntimeException

class StompException(message: String? = "") : RuntimeException(message)