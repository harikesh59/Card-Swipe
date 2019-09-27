package com.harikesh.swipe.ui

import com.harikesh.swipe.ui.mapper.CardMapper
import com.harikesh.swipe.ui.test.factory.CardDataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CardMapperTest {

    private lateinit var cardMapper: CardMapper

    @Before
    fun setUp() {
        cardMapper = CardMapper()
    }

    @Test
    fun mapToViewMapsData() {
        val cardDataView = CardDataFactory.makeCardDataView()
        val cardDataViewModel = cardMapper.mapToViewModel(cardDataView)

        assertEquals(cardDataView.id, cardDataViewModel.id)
        assertEquals(cardDataView.text, cardDataViewModel.text)
    }

}