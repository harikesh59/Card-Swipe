package com.harikesh.swipe.domain.test.factory

import java.util.*

/**
 * Factory class for data instances
 */
class DataFactory {

    companion object Factory {

        fun randomUuid(): String {
            return UUID.randomUUID().toString()
        }

        fun randomId(): Int {
            return (1..10).shuffled().first()
        }

    }

}