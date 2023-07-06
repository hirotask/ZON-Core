package com.github.hirotask.core.exc

/**
 * ZONPlayerStatusの有効な値が見つからなかったときのエラー
 *
 * @param msg エラーメッセージ
 */
class ZONPlayerStatusNotFoundException(msg: String? = null) : Exception(msg)
