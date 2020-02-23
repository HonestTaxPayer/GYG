package gyg.reviews.domain.usecase.base

interface UseCase<I, O> {

    fun execute(params: I): O
}