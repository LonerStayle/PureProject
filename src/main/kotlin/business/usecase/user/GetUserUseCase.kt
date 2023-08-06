package business.usecase.user

import business.entities.KhaosUser
import business.entities.UserId
import business.repository.UserRepository
import business.usecase.base.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext

class GetUserUseCase(private val userRepository: UserRepository) : UseCase<UserId, Flow<KhaosUser>>() {

    override suspend fun invoke(request: UserId): Flow<KhaosUser> = withContext(Dispatchers.IO) {
        return@withContext userRepository.getUser(request).catch { println(it.message) }
    }
}