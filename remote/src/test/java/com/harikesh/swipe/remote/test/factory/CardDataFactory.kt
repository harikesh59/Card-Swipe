package com.harikesh.swipe.remote.test.factory

import com.harikesh.swipe.remote.SwipeApi
import com.harikesh.swipe.remote.model.CardModel

/**
 * Factory class for CardData related instances
 */
class CardDataFactory {

    companion object Factory {

        fun cardDataResponse(): SwipeApi.CardDataResponse {
            val response = SwipeApi.CardDataResponse()
            response.data = modelList(5)
            return response
        }

        fun modelList(count: Int): List<CardModel> {
            val entities = mutableListOf<CardModel>()
            repeat(count) {
                entities.add(model())
            }
            return entities
        }

        fun model(): CardModel {
            return CardModel(1,"1")
        }

    }

}