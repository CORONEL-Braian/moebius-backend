package app.mobius.data.util

import java.util.*

fun getUUID(msg: String) = UUID.randomUUID().also { print("New UUID for $msg: $it") }
