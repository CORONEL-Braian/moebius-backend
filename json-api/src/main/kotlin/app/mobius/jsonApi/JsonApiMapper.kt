package app.mobius.jsonApi

import app.mobius.jsonApi.model.JsonApiResource
import app.mobius.jsonApi.model.RelationshipData
import app.mobius.jsonApi.model.ResourceData
import org.objenesis.Objenesis
import org.objenesis.ObjenesisStd
import java.sql.Date
import java.sql.Time
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
object JsonApiMapper {

    /**
     * Map some ModelDto to some JsonApiResource
     *
     * OBS: Used for GET requests
     */
    fun <T> mapDtoToJsonApiResource(
            modelDto: T
    ) : JsonApiResource {
        return JsonApiResource()    //TODO: Impl
    }

    /**
     * Map a JsonApiResource to some ModelDto
     *
     * OBS: Used for POST requests
     * Source:
     *  . set field value: https://www.baeldung.com/java-set-private-field-value
     *  . instantiate classes without default constructors:
     *      . https://stackoverflow.com/q/4133709/5279996
     *      . http://objenesis.org/tutorial.html
     */
    fun <T> mapJsonApiResourceToDto(
            jsonApiResource: JsonApiResource,
            dtoType: Class<T>
    ) : T {

        var dtoInstance: T = try {
            dtoType.newInstance()   //TODO: Deprecated: Use:  dtoType.getDeclaredConstructor().newInstance()
        } catch (e: InstantiationException) {
//            Use Objenesis because the dtoType class has not a default constructor
            val objenesis: Objenesis = ObjenesisStd()
            objenesis.newInstance(dtoType)
        }

        if (jsonApiResource.data.isNotEmpty()) {
            jsonApiResource.data.first().let { data ->
                dtoInstance = mapDataToDtoResource(
                        data = data,
                        dtoType = dtoType as Class<Any>,
                        dtoInstance = dtoInstance as Any
                ) as T
            }
        }

        return dtoInstance
    }

    /**
     * Add fields values from ResourceData to dtoInstance
     */
    private fun mapDataToDtoResource(
            data: ResourceData,
            dtoType: Class<Any>,
            dtoInstance: Any,
    ) : Any {
        var newDtoInstance: Any = dtoInstance
        newDtoInstance = mapAttributesToDtoResource(attributes = data.attributes, dtoType = dtoType, dtoInstance = newDtoInstance)
        newDtoInstance = mapRelationshipsToDtoResource(relationships = data.relationships, dtoType = dtoType, dtoInstance = newDtoInstance)
        return newDtoInstance
    }

    private fun mapRelationshipsToDtoResource(
            relationships: Map<String, RelationshipData>,
            dtoType: Class<Any>,
            dtoInstance: Any,
    ) : Any {

        relationships.map { relationship ->

            dtoType.declaredFields.firstOrNull { dtoRelationship ->
                relationship.key == dtoRelationship.name
            }?.let { dtoRelationship ->
                dtoRelationship.isAccessible = true

                val relationshipDtoInstance = dtoRelationship.type.newInstance()

                /**
                 * https://stackoverflow.com/a/62324236/5279996
                 */
                dtoRelationship.set(
                        dtoInstance,
                        mapDataToDtoResource(    // GENERATES SOME CIRCULAR DEPENDENCY
                                data = relationship.value.data,
                                dtoType = dtoRelationship.type as Class<Any>,
                                dtoInstance = relationshipDtoInstance
                        )
                )
            }
        }
        return dtoInstance
    }

    private fun mapAttributesToDtoResource(
            attributes: Map<String, Any>,
            dtoType: Class<Any>,
            dtoInstance: Any
    ) : Any {
        attributes.map { jsonAttribute ->
            dtoType.declaredFields.firstOrNull() { dtoAttribute ->
                jsonAttribute.key == dtoAttribute.name
            }?.let { dtoAttribute ->
                dtoAttribute.isAccessible = true
                dtoAttribute.set(
                        dtoInstance,
                        setAttributeValue(
                                type = dtoAttribute.type,
                                value = jsonAttribute.value
                        )
                )
            }
        }
        return dtoInstance
    }

    /**
     * Checks the value type for set it as an attribute
     */
    private fun <T> setAttributeValue(
            type: Class<T>,
            value: Any
    ) : T {
        return (try {
            when (type) {
                String::class.java  -> value.toString()
                Date::class.java -> dateValueOf(value.toString())
                Time::class.java -> timeValueOf(value.toString())
                Long::class.java -> value.toString().toLong()
                Double::class.java  -> value.toString().toDouble()
                Boolean::class.java  -> value.toString().toBoolean()
                else -> {
                    if (type.isEnum) enumValueOf(type, value as String)
                    else value
                }
            }

        } catch (e: IllegalArgumentException)  {
            e.printStackTrace()
            value
        }) as T
    }

    /**
     * Map from string date to sql date
     * Precondition: Format of @param date
     *
     * Source: https://stackoverflow.com/a/29168526/5279996
     *
     * @param date: "2021-05-12"
     * @return java.sql.Date
     */
    private fun dateValueOf(date: String) : Date {
        return Date.valueOf(date)
    }

    /**
     * Map from string date to sql date
     * Precondition: Format of @param time
     *
     * @param time: "10:34:00"
     * @return java.sql.Time
     */
    private fun timeValueOf(time: String) : Time {
        return Time.valueOf(time)
    }

    /**
     * Map from string enum to enum
     * Precondition: @param enum exists in T
     *
     * Source: https://stackoverflow.com/a/67514228/5279996
     *
     * @param enum: "M"
     * @return T
     */
    private fun <T> enumValueOf(type: Class<T>, enum: String) : T {
        return type.enumConstants.first { it.toString() == enum }
    }

}