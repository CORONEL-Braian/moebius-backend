package app.mobius.data.util

import java.util.*

fun randomString(begin: String = ""): String {
    val randomUUID = UUID.randomUUID().toString().substring(0, 5)
    return "$begin $randomUUID".also { println("Random string: $it") }
}