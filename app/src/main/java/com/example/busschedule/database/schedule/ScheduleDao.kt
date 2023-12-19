package com.example.busschedule.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    // 查詢所有 data，依 arrival time 遞增排序
    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    // return 所有 Schedule list (用 Flow<> 包起來，使 DAO 持續從 database 發出 data)
    fun getAll(): Flow<List<Schedule>>

    // 查詢 selected stop name data，依 arrival time 遞增排序
    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    // return 所選 stop_name 的 Schedule list (用 Flow<> 包起來，使 DAO 持續從 database 發出 data)
    fun getStop(stopName: String): Flow<List<Schedule>>
}