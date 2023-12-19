package com.example.busschedule

import android.app.Application
import com.example.busschedule.database.AppDatabase

// 建立 Application class 的子類別，並建立 lazy 屬性來存放 getDatabase() 的結果
class BusScheduleApplication: Application() {
    // 新增 database 屬性，return 在 AppDatabase class 上呼叫 getDatabase() 的結果
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}