package git.juampa99.rpgfy.utils.string

// Extension functions

// Converts strings of the form "This is a string" to "THIS_IS_A_STRING"
fun String.toSlug(): String = this.uppercase().replace(" ", "_")