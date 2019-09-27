package com.harikesh.swipe.presentation.browse

import com.harikesh.swipe.presentation.BasePresenter
import com.harikesh.swipe.presentation.BaseView
import com.harikesh.swipe.presentation.model.CardDataView

/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface BrowseContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showCardData(cardData: List<CardDataView>)

        fun hideCardData()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {

        fun retrieveCard()

    }

}