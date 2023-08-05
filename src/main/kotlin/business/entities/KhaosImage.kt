package business.entities

data class KhaosImage(
    val id: ImageId,
    val uploadUserId: UserId,
    val imageUrl: String
)