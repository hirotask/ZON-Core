package com.github.hirotask.core.exc

/**
 * ZONPlayerが見つからなかったときに発生するエラー
 */
class ZONPlayerNotFoundException(msg: String? = null) : Exception(msg)
