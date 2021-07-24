package git.juampa99.rpgfy.utils.string

import org.junit.Test
import org.junit.Assert

import org.junit.Assert.*

class UtilKtTest {

    @Test
    fun toSlug() {
        val normalString = "This is a string"
        assertEquals(normalString.toSlug(), "THIS_IS_A_STRING")
    }

    @Test
    fun capitalizeAll() {
        val lowercaseString = "this is a lowercase string"
        val uppercaseString = "THIS IS AN UPPERCASE STRING"
        assertEquals(lowercaseString.capitalizeAll(), "This Is A Lowercase String")
        assertEquals(uppercaseString.capitalizeAll(), "THIS IS AN UPPERCASE STRING")
    }
}