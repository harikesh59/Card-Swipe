package com.harikesh.swipe.remote.mapper

import com.harikesh.swipe.remote.test.factory.CardDataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class PojoEntityMapperTest {

    private lateinit var pojoEntityMapper: PojoEntityMapper

    @Before
    fun setUp() {
        pojoEntityMapper = PojoEntityMapper()
    }

    @Test
    fun mapFromRemoteMapsData() {
        val model = CardDataFactory.model()
        val entity = pojoEntityMapper.mapFromRemote(model)

        assertEquals(model.text, entity.text)
        assertEquals(model.id, entity.id)
    }

}