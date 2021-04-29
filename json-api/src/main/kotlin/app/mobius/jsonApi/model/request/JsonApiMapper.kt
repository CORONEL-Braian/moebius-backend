package app.mobius.jsonApi.model.request

import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
object JsonApiMapper {

    /**
     * Map a generic of JsonApiRequest to some model DTO Request
     * PRE: @param valueType has default values //TODO: Delete
     *
     * Source:
     *  . set field value: https://www.baeldung.com/java-set-private-field-value
     */
    fun <T> mapJsonApiToDtoRequest(
            jsonApiRequest: JsonApiRequest,
            dtoType: Class<T>
    ) : T {
        /**
         * If dtoType has not default values, do not use #newInstance() //TODO: Delete
         */
        var dtoInstance: T = dtoType.newInstance()

        if (jsonApiRequest.data.isNotEmpty()) {
            jsonApiRequest.data.first().let { data ->
                dtoInstance = mapDataToDtoRequest(
                        data = data,
                        dtoType = dtoType,
                        dtoInstance = dtoInstance
                )
            }
        }

        return dtoInstance
    }

    /**
     * Add fields values from RequestData to dtoInstance
     */
    private fun <T> mapDataToDtoRequest(
            data: RequestData,
            dtoType: Class<T>,
            dtoInstance: T,
    ) : T {
        var newDtoInstance = dtoInstance
        newDtoInstance = mapAttributesToDtoRequest(attributes = data.attributes, dtoType = dtoType, dtoInstance = newDtoInstance)
        newDtoInstance = mapRelationshipsToDtoRequest(relationships = data.relationships, dtoType = dtoType, dtoInstance = newDtoInstance)
        return newDtoInstance
    }

    private fun <T> mapRelationshipsToDtoRequest(
            relationships: Map<String, RelationshipData>,
            dtoType: Class<T>,
            dtoInstance: T,
    ) : T {

        val newDtoInstance = dtoInstance

//        TODO: Check circular dependency
        relationships.map { relationship ->

//            jsonRelationship.value.data.attributes

            dtoType.declaredFields.first { dtoRelationship ->
                relationship.key == dtoRelationship.name
            }.let { dtoRelationship ->
                dtoRelationship.isAccessible = true

                val relationshipDtoInstance = dtoRelationship.type.newInstance()

                dtoRelationship.set(
                        newDtoInstance,
                        mapDataToDtoRequest(    // GENERATES SOME CIRCULAR DEPENDENCY
                                data = relationship.value.data,
                                dtoType = dtoRelationship.type,
                                dtoInstance = relationshipDtoInstance
                        )
                )
            }
        }
        return newDtoInstance
    }

    private fun <T> mapAttributesToDtoRequest(
            attributes: Map<String, Any>,
            dtoType: Class<T>,
            dtoInstance: T
    ) : T {
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