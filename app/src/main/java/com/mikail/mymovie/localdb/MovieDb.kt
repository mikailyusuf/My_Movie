package com.mikail.mymovie.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mikail.mymovie.model.Converters
import com.mikail.mymovie.model.Result


@Database(
    entities = [Result::class],
    version = 1

)
@TypeConverters(Converters::class)
 abstract class MovieDb:RoomDatabase() {

    abstract fun getMovie(): MovieDao
}