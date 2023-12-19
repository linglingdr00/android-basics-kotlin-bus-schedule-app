package com.example.busschedule.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// 建立實體(entity)
@Entity
data class Schedule(
    // 新增 id 為主鍵(primary key)
    @PrimaryKey val id: Int,
    // 新增 stop_name column (column 的 value 不可為 null)
    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,
    // arrival_time column (column 的 value 不可為 null)
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int

)