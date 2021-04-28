package app.mobius.jsonApi.model.request

import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
object JsonApiMapper {

    /**
     * Map a generic of JsonApiRequest to some model DTO Request
     * PRE: @param valueType has default values
     *
     * Source:
     *  . set field value: https://www.baeldung.com/java-set-private-field-value
     */
    fun <T> mapJsonApiToDtoRequest(
            jsonApiRequest: JsonApiRequest,
            dtoType: Class<T>
    ) : T {
        /**
         * If dtoType has not default values, do not use #newInstance()
         */
        val dtoInstance: T = dtoType.newInstance()

        if (jsonApiRequest.data.isNotEmpty()) {
//              Add fields value of JsonApiRequest to dtoInstance
            jsonApiRequest.data.first().let { data ->
                mapAttributesToDtoRequest(dtoType = dtoType, dtoInstance = dtoInstance, attributes = data.attributes)
                mapRelationshipsToDtoRequest(dtoType = dtoType, dtoInstance = dtoInstance, relationships = data.relationships)
            }

        }

        return dtoInstance
    }

    private fun <T> mapRelationshipsToDtoRequest(
            dtoType: Class<T>,
            dtoInstance: T,
            relationships: Map<String, RelationshipData>,
    ) {
//        TODO: Check circular dependency
        relationships.map { jsonRelationship ->

//            jsonRelationship.value.data.attributes

            dtoType.declaredFields.first { dtoAttribute ->
                jsonRelationship.key == dtoAttribute.name
            }.let { dtoAttribute ->
                dtoAttribute.isAccessible = true
//                dtoAttribute.set(dtoInstance, jsonRelationship.value) //TODO: exception by "Photographer(tpye=,id=)"
            }
        }
    }

    private fun <T> mapAttributesToDtoRequest(
            dtoType: Class<T>,
            dtoInstance: T,
            attributes: Map<String, Any>
    ) {
        attributes.map { jsonAttribute ->
            dtoType.declaredFields.first { dtoAttribute ->
                jsonAttribute.key == dtoAttribute.name
            }.let { dtoAttribute ->
                dtoAttribute.isAccessible = true
                dtoAttribute.set(dtoInstance, jsonAttribute.value)
            }
        }
    }

    fun mapperModelDtoRequestToEntity() {

    }

}