package app.mobius.data.util

import java.util.*

fun getUUID(msg: String) = UUID.randomUUID().also { println("New UUID for $msg: $it") }
