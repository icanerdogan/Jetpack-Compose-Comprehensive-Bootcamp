package com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.model.Favorite
import com.ibrahimcanerdogan.jetpackcomposecomprehensivebootcamp.app7weather.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}