package app.mobius.data.util

import kotlin.jvm.Throws
import kotlin.reflect.KProperty1

/**
 * Describes value from property name of Any
 * Source: https://stackoverflow.com/a/35539628/5279996
 */
@Suppress("UNCHECKED_CAST")
@Throws(IllegalAccessException::class, ClassCastException::class)
fun <R> Any.propertyValue(propertyName: String): R {
    val property = this::class.members
            // don't cast here to <Any, R>, it would succeed silently
            .first { it.name == propertyName } as KProperty1<Any, *>
    // force a invalid cast exception if incorrect type here
    return property.get(this) as R
}