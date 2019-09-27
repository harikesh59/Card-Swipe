package com.harikesh.swipe.ui.test.factory

import com.harikesh.swipe.presentation.model.CardDataView
import com.harikesh.swipe.ui.test.factory.DataFactory.Factory.randomInt
import com.harikesh.swipe.ui.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for CardData related instances
 */
class CardDataFactory {

    companion object Factory {

        fun makeCardDataView(): CardDataView {
            return CardDataView(randomInt(), randomUuid())
        }

    }

}