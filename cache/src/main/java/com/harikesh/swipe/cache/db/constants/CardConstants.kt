package com.harikesh.swipe.cache.db.constants

import com.harikesh.swipe.cache.db.Db

/**
 * Defines DB queries for the CardData Table
 */
object CardConstants {

    internal val QUERY_GET_ALL_DATA = "SELECT * FROM " + Db.CardDataTable.TABLE_NAME

}