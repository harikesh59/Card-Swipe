package com.harikesh.swipe.remote

import io.reactivex.Single
import com.harikesh.swipe.remote.model.CardModel
import retrofit2.http.GET

/**
 * Defines the abstract methods used for interacting with the CardData API
 */
interface SwipeApi {

    @GET("CardData.json")
    fun getCardData(): Single<CardDataResponse>

    class CardDataResponse {
        lateinit var data: List<CardModel>
    }

}
