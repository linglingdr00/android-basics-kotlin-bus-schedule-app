package com.example.busschedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao


// 新增 abstract class AppDatabase，繼承 RoomDatabase
@Database(entities = arrayOf(Schedule::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    // return ScheduleDao，方便其他 class 存取 DAO class
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        // 新增 INSTANCE (type 為 AppDatabase)
        private var INSTANCE: AppDatabase? = null

        // 定義一個 function 以 return AppDatabase instance
        fun getDatabase(context: Context): AppDatabase {
            /* 如果已有 instance，使用 Elvis 運算子 return database 的現有 instance，
               如果尚未有 instance 則首次建立 database */
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/bus_schedule.db")
                    .build()
            INSTANCE = instance

            instance
            }

        }
    }
}