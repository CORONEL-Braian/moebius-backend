package app.mobius.jsonApi.model.request

import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
object JsonApiMapper {

    /**
     * Map a generic of JsonApiRequest to some model DTO Request
     * PRE: @param valueType has default values
     */
    fun <T> mapperGenericToModelDtoRequest(
            jsonApiRequest: JsonApiRequest,
            valueType: Class<T>
    ) : T {
        /**
         * If valueType has not default values, do not use #newInstance()
         */
        val instance: T = valueType.newInstance()



        return instance
    }

    fun mapperModelDtoRequestToEntity() {

    }

}