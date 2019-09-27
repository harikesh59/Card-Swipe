package com.harikesh.swipe.cache.db.mapper

import android.content.ContentValues
import android.database.Cursor
import com.harikesh.swipe.cache.db.Db
import com.harikesh.swipe.cache.model.CachedCardData
import javax.inject.Inject

/**
 * Maps a [CachedCardData] instance to a database entity.
 */
class LocalCardDataMapper @Inject constructor() : ModelDbMapper<CachedCardData> {

    /**
     * Construct an instance of [ContentValues] using the given [CachedCardData]
     */
    override fun toContentValues(model: CachedCardData): ContentValues {
        val values = ContentValues()
        values.put(Db.CardDataTable.CARD_ID, model.id)
        values.put(Db.CardDataTable.TEXT, model.text)
        return values
    }

    /**
     * Parse the cursor creating a [CachedCardData] instance.
     */
    override fun parseCursor(cursor: Cursor): CachedCardData {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Db.CardDataTable.CARD_ID))
        val text = cursor.getString(cursor.getColumnIndexOrThrow(Db.CardDataTable.TEXT))
        return CachedCardData(id, text)
    }

}