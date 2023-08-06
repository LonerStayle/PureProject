import app.dummy.dummyUsers
import app.service_locator.ServiceLocator
import business.entities.KhaosUser
import business.entities.UserId
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val userController = ServiceLocator.provideUserController()
    val allViewUserData = (dummyUsers.indices).asFlow().map {
        UserId(it.toLong())
    }.debounce(500).flatMapMerge {
        userController.getUser(it)
    }.onStart {  }
}