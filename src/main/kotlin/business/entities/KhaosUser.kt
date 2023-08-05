package business.entities

data class KhaosUser(
    val id: UserId,
    val name: String,
    val email: String,
    val thumbUrl: String,
    val myImages: List<KhaosImage> = emptyList(),
    val myVideos: List<KhaosVideo> = emptyList(),
    val myFiles: List<KhaosFile> = emptyList()
) {

    companion object {
        val INIT_TEMP = KhaosUser(
            id = UserId(-1),
            name = "",
            email = "",
            thumbUrl = "",
        )
    }

    override fun toString(): String {
        return """
            ${this::class.simpleName}(
            ${this::id.name} = ${id.value},
            ${this::name.name} = ${name},
            ${this::email.name} = ${email},
            ${this::thumbUrl.name} = ${thumbUrl},
            ${this::myImages.name} = ${myImages},
            ${this::myVideos.name} = ${myVideos},
            ${this::myFiles.name} = ${myFiles},
            )
        """.trimIndent()

    }
}
