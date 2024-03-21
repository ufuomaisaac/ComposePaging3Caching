package com.plcoding.composepaging3caching.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plcoding.composepaging3caching.domain.Beer

@Database(
    entities = [BeerEntity::class],
    version = 1
)
abstract class BeerDataBase: RoomDatabase() {
    abstract val dao: BeerDao

}