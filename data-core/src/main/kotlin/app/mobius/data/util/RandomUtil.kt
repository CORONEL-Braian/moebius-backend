package app.mobius.data.util

import kotlin.random.Random

fun randomString(): String = Random(1).nextInt().toString()