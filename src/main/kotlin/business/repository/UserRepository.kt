package business.repository

import business.entities.KhaosUser
import business.entities.UserId
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userId: UserId): Flow<KhaosUser>
}