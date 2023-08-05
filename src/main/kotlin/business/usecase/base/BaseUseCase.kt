package business.usecase.base


abstract class UseCase<P, R> {
    abstract suspend operator fun invoke(request: P): R
}

abstract class WrapperResultUseCase<P, R> {
    abstract suspend operator fun invoke(request: P): Result<R>
}

abstract class NonRequestUseCase<R> {
    abstract suspend operator fun invoke(): R
}