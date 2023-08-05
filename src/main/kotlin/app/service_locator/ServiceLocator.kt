package app.service_locator

import business.usecase.user.GetUserUseCase
import interface_adapter.controller.UserController
import interface_adapter.database.query.UserQuery
import interface_adapter.repository.UserRepositoryImpl

object ServiceLocator {
    private var userController: UserController? = null
    fun provideUserController(): UserController = if (userController != null) {
        userController!!
    } else {
        val getUserUseCase = GetUserUseCase(UserRepositoryImpl(UserQuery()))
        UserController(getUserUseCase)
    }
}


