package com.harikesh.swipe.domain.test.factory

import com.harikesh.swipe.domain.model.CardData
import com.harikesh.swipe.domain.test.factory.DataFactory.Factory.randomId
import com.harikesh.swipe.domain.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for CardData related instances
 */
class CardDataFactory {

    companion object Factory {

        fun list(count: Int): List<CardData> {
            val list = mutableListOf<CardData>()
            repeat(count) {
                list.add(cardData())
            }
            return list
        }

        fun cardData(): CardData {
            return CardData(randomId(), randomUuid())
        }

    }

}