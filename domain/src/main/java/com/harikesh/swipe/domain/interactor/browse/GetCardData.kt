package com.harikesh.swipe.domain.interactor.browse

import io.reactivex.Single
import com.harikesh.swipe.domain.executor.PostExecutionThread
import com.harikesh.swipe.domain.executor.ThreadExecutor
import com.harikesh.swipe.domain.interactor.SingleUseCase
import com.harikesh.swipe.domain.model.CardData
import com.harikesh.swipe.domain.repository.Repository
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [CardData] instances from the [Repository]
 */
open class GetCardData @Inject constructor(val repository: Repository,
                                           threadExecutor: ThreadExecutor,
                                           postExecutionThread: PostExecutionThread):
        SingleUseCase<List<CardData>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<List<CardData>> {
        return repository.getCardData()
    }

}