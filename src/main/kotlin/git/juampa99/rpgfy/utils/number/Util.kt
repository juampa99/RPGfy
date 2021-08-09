package git.juampa99.rpgfy.utils.number

import git.juampa99.rpgfy.Rpgfy

/**
 * If integer is bigger than cap, returns cap, else return the integer
 * */
fun Int.cap(cap: Int): Int = this.coerceAtMost(cap)

fun Int.effectCap(): Int = this.cap(Rpgfy.plugin?.config?.getInt("max-level") ?: 5)

fun Double.cap(cap: Double): Double = this.coerceAtMost(cap)