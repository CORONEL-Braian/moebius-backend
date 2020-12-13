import java.util.*
data class Content(
        val uuid: UUID,
        val title: String,
        val color: Color,
        val coverPage: String,
        val description: String,
        val tag: String,
        val createOn: Date,
        val attachments: Attachments,
)
typealias Attachments = List<Attached>
data class Attached(
        val uuid: UUID,
        val title: String,
        val createOn: Date,
        val file: String,
)
data class Color(
        val title: String,
        val hexCode: String,
)