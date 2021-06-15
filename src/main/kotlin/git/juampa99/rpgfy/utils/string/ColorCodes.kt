package git.juampa99.rpgfy.utils.string

/**
 * Usable for name tags
 * */
object ColorCodes {

    const val BLACK: Color = "§0"
    const val DARK_BLUE: Color = "§1"
    const val DARK_GREEN: Color = "§2"
    const val DARK_AQUA: Color = "§3"
    const val DARK_RED: Color = "§4"
    const val DARK_PURPLE: Color = "§5"
    const val GOLD: Color = "§6"
    const val GRAY: Color = "§7"
    const val DARK_GRAY: Color = "§8"
    const val BLUE: Color = "§9"
    const val GREEN: Color = "§a"
    const val AQUA: Color = "§b"
    const val RED: Color = "§c"
    const val LIGHT_PURPLE: Color = "§d"
    const val YELLOW: Color = "§e"
    const val WHITE: Color = "§f"

    fun getColorConstant(color: String): String {
        return when(color.lowercase()) {
            "red" -> RED
            "yellow" -> YELLOW
            "light_purple"-> LIGHT_PURPLE
            "green" -> GREEN
            "aqua" -> AQUA
            "blue" -> BLUE
            "dark_gray" -> DARK_GRAY
            "gray" -> GRAY
            "gold" -> GOLD
            "dark_purple" -> DARK_PURPLE
            "dark_red" -> DARK_RED
            "dark_aqua" -> DARK_AQUA
            "dark_green" -> DARK_GREEN
            "dark_blue" -> DARK_BLUE
            "black" -> BLACK
            else -> WHITE
        }
    }

}

typealias Color = String
