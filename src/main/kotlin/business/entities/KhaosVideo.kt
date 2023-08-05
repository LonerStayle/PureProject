package business.entities

data class KhaosVideo(
    val id: VideoId,
    val uploadUserId: UserId,
    val videoUrl: String
)
