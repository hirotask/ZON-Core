package com.github.hirotask.core.exc

/**
 * 不正な値が返されたときのエラー
 *
 * @param msg エラーメッセージ
 */
class InvalidNumberException(msg: String? = null) : Exception(msg)
