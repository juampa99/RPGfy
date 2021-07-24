package git.juampa99.rpgfy.utils.string

// Extension functions

/**
 * Converts strings of the form "This is a string" to "THIS_IS_A_STRING"
 * */
fun String.toSlug(): String = this.uppercase().replace(" ", "_")

/**
 * Converts strings of the form "THIS_IS_A_STRING" to "This is a string"
 * */
fun String.unslug(): String = this.lowercase().replace("_", " ").capitalizeFirst()

/**
 * Capitalizes first char in string
 */
fun String.capitalizeFirst(): String = this.replaceFirstChar{ c -> c.uppercase()}

/**
 * Capitalize words in string, e.g. "this is a string" to "This Is A String"
 * */
fun String.capitalizeAll(): String = this.split(" ")
                                        .map { s -> s.capitalizeFirst() }.joinToString(" ")