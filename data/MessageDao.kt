package com.whats2ps.data

import androidx.room.*

@Dao
interface MessageDao {
    @Insert fun insert(message: MessageEntity)
    @Query("SELECT * FROM MessageEntity ORDER BY timestamp DESC") fun getAll(): List<MessageEntity>
}
