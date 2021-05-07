package app.mobius.jsonApi.model

import org.objenesis.Objenesis
import org.objenesis.ObjenesisStd
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
object JsonApiMapper {

    /**
     * Map a generic of JsonApiRequest to some model DTO Request
     *
     * Source:
     *  . set field value: https://www.baeldung.com/java-set-private-field-value
     *  . instantiate classes without default constructors:
     *      . https://stackoverflow.com/q/4133709/5279996
     *      . http://objenesis.org/tutorial.html
     */
    fun <T> mapJsonApiToDtoRequest(
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
                dtoInstance = mapDataToDtoRequest(
                        data = data,
                        dtoType = dtoType as Class<Any>,
                        dtoInstance = dtoInstance as Any
                ) as T
            }
        }

        return dtoInstance
    }

    /**
     * Add fields values from RequestData to dtoInstance
     */
    private fun mapDataToDtoRequest(
            data: ResourceData,
            dtoType: Class<Any>,
            dtoInstance: Any,
    ) : Any {
        var newDtoInstance: Any = dtoInstance
        newDtoInstance = mapAttributesToDtoRequest(attributes = data.attributes, dtoType = dtoType, dtoInstance = newDtoInstance) as Any
        newDtoInstance = mapRelationshipsToDtoRequest(relationships = data.relationships, dtoType = dtoType, dtoInstance = newDtoInstance)
        return newDtoInstance
    }

    private fun mapRelationshipsToDtoRequest(
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
                        mapDataToDtoRequest(    // GENERATES SOME CIRCULAR DEPENDENCY
                                data = relationship.value.data,
                                dtoType = dtoRelationship.type as Class<Any>,
                                dtoInstance = relationshipDtoInstance
                        )
                )
            }
        }
        return dtoInstance
    }

    private fun mapAttributesToDtoRequest(
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

    fun mapperModelDtoRequestToEntity() {

    }

}