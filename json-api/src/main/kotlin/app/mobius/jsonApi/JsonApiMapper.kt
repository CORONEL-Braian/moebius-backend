package app.mobius.jsonApi

import app.mobius.jsonApi.model.JsonApiResource
import app.mobius.jsonApi.model.RelationshipData
import app.mobius.jsonApi.model.ResourceData
import org.objenesis.Objenesis
import org.objenesis.ObjenesisStd
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
            dtoType.newInstance()
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
        newDtoInstance = mapAttributesToDtoResource(attributes = data.attributes, dtoType = dtoType, dtoInstance = newDtoInstance) as Any
        newDtoInstance = mapRelationshipsToDtoResource(relationships = data.relationships, dtoType = dtoType, dtoInstance = newDtoInstance)
        return newDtoInstance
    }

    private fun mapRelationshipsToDtoResource(
            relationships: Map<String, RelationshipData>,
            dtoType: Class<Any>,
            dtoInstance: Any,
    ) : Any {

        relationships.map { relationship ->

            dtoType.declaredFields.first { dtoRelationship ->
                relationship.key == dtoRelationship.name
            }.let { dtoRelationship ->
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
            dtoType.declaredFields.first { dtoAttribute ->
                jsonAttribute.key == dtoAttribute.name
            }.let { dtoAttribute ->
                dtoAttribute.isAccessible = true
                dtoAttribute.set(dtoInstance, jsonAttribute.value)
            }
        }
        return dtoInstance
    }

}