package app.mobius.data.util

import java.util.*

fun randomString(begin: String = "", endIndex: Int = 5): String {
    val randomUUID = UUID.randomUUID().toString().substring(0, endIndex)
    return "$begin $randomUUID".also { println("Random string: $it") }
}