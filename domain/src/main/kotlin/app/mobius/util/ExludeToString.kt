package app.mobius.util

import java.lang.reflect.Field
import java.util.*

/**
 * Source: https://gist.github.com/Coronel-B/88ac762bee486eb83ded694a055da6d7
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExcludeToString

data class Test(
        var a: String = "Test A",
        @ExcludeToString var b: String = "Test B",
) {
    override fun toString(): String {
        return ExcludeToStringUtils.getToString(this)
    }
}

object ExcludeToStringUtils {

    @JvmStatic
    fun main(args: Array<String>) {
        print(Test().toString())
    }

    /**
     * Formats the object with their respective fields
     */
    fun getToString(obj: Any): String {
        val toString = LinkedList<String>()
        getFieldsNotExludeToString(obj).forEach { prop ->
            prop.isAccessible = true
            toString += "${prop.name}=" + prop.get(obj)?.toString()?.trim()
        }
        return "${obj.javaClass.simpleName}=[${toString.joinToString(", ")}]"
    }

    /**
     * Filter the fields that do not have annotation @ExcludeToString
     */
    private fun getFieldsNotExludeToString(obj: Any): List<Field> {
        val declaredFields = obj::class.java.declaredFields
        return declaredFields.filterNot { field ->
            isFieldWithExludeToString(field)
        }
    }

    /**
     * Determine if a field has annotation @ExcludeToString
     */
    private fun isFieldWithExludeToString(field: Field): Boolean {
        field.annotations.forEach {
            if (it.annotationClass == ExcludeToString::class) {
                return true
            }
        }
        return false
    }

}