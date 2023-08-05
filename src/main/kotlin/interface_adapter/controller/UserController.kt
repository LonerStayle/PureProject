package interface_adapter.controller

import business.entities.KhaosUser
import business.entities.UserId
import business.usecase.user.GetUserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UserController(private val getUserUseCase: GetUserUseCase) {
    suspend fun getUser(userId: UserId): StateFlow<KhaosUser> =
        getUserUseCase(userId).stateIn(CoroutineScope(Dispatchers.IO))


}