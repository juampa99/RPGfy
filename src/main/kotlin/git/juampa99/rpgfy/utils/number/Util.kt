package git.juampa99.rpgfy.utils.number

import java.lang.Math.min

/**
 * If integer is bigger than cap, returns cap, else return the integer
 * */
fun Int.cap(cap: Int): Int = this.coerceAtMost(cap)