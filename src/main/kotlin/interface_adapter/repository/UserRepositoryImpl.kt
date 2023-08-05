package interface_adapter.repository

import business.entities.KhaosUser
import business.entities.UserId
import business.repository.UserRepository
import interface_adapter.database.query.UserQuery
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userQuery: UserQuery) : UserRepository {
    override fun getUser(userId: UserId): Flow<KhaosUser> = userQuery.getUser(userId.value)

}