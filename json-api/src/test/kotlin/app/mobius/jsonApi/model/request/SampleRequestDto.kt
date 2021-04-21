package app.mobius.jsonApi.model.request

data class PhotosSampleRequestDto(
        val title: String,
        val src: String,
        val photographer: Photographer
)

data class Photographer(
        val type: String,
        val id: String
)