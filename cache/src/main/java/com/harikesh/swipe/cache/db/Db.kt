package com.harikesh.swipe.cache.db

/**
 * This class defines the tables found within the application Database. All table
 * definitions should contain column names and a sequence for creating the table.
 */
object Db {

    object CardDataTable {
        const val TABLE_NAME = "cardData"

        const val CARD_ID = "card_id"
        const val TEXT = "text"

        const val CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        TEXT + " TEXT NOT NULL" + "); "


    }

}