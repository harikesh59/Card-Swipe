package com.harikesh.swipe.presentation.test.factory

import com.harikesh.swipe.domain.model.CardData
import com.harikesh.swipe.presentation.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for CardData related instances
 */
class CardDataFactory {

    companion object Factory {

        fun makeCardList(count: Int): List<CardData> {
            val cardList = mutableListOf<CardData>()
            repeat(count) {
                cardList.add(makecardModel())
            }
            return cardList
        }

        fun makecardModel(): CardData {
            return CardData(0, randomUuid())
        }

    }

}