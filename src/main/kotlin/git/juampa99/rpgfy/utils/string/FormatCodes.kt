package git.juampa99.rpgfy.utils.string

/**
 * Usable for name tags
 * */
object FormatCodes {

    const val OBFUSCATED = "§k"
    const val BOLD = "§l"
    const val STRIKETHROUGH = "§m"
    const val UNDERLINE = "§n"
    const val ITALIC = "§o"
    const val RESET = "§r"

    fun formatAndReset(str: String, format: String): String {
        return "${format}str$RESET"
    }

}