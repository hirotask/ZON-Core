package com.github.hirotask.core.domain

/**
 * ZONPlayerStatusドメイン
 *
 * @property hp HP
 * @property hpRegen HP再生速度
 * @property mp MP
 * @property mpRegen MP再生速度
 * @property strength 攻撃力
 */
data class ZONPlayerStatus(var hp: Int, var hpRegen: Int, var mp: Int, var mpRegen: Int, var strength: Int)
