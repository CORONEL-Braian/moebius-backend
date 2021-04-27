package app.mobius.jsonApi.model.request

import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
object JsonApiMapper {

    /**
     * Map a generic of JsonApiRequest to some model DTO Request
     * PRE: @param valueType has default values
     *
     * Source:
     *  . set field: https://www.baeldung.com/java-set-private-field-value
     */
    fun <T> mapGenericToDtoRequest(
            jsonApiRequest: JsonApiRequest,
            valueType: Class<T>
    ) : T {
        /**
         * If valueType has not default values, do not use #newInstance()
         */
        val instance: T = valueType.newInstance()

//        TODO: Add fields to instance

        return instance
    }

    fun mapperModelDtoRequestToEntity() {

    }

}