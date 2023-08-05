package interface_adapter.database.query

import app.dummy.dummyUsers
import business.entities.KhaosUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserQuery {
    fun getUser(id: Long): Flow<KhaosUser> = flow {
        emit(
            dummyUsers.find { it.id.value == id } ?: throw NotFoundUserQueryException()
        )
    }
}

class NotFoundUserQueryException() : Exception() {
    override val message: String
        get() = "해당 유저를 찾지 못했습니다."
}