package app.mobius.jsonApi.model.request

data class SampleRequestDto(
        val title: String,
        val src: String,
        val photographer: Photographer
) {
    constructor() : this(title = "", src = "", photographer = Photographer())
}

data class Photographer(
        val type: String,
        val id: String
) {
    constructor() : this(type = "", id = "")
}