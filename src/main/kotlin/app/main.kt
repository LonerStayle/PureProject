import app.service_locator.ServiceLocator
import business.entities.UserId
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val userController = ServiceLocator.provideUserController()
    userController.getUser(UserId(0)).collectLatest {
        println(it)
    }
}