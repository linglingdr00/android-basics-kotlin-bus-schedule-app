package com.example.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

// 定義 view model 的 class
class BusScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {

    // 取得完整 schedule
    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()
    // 取得依 stop name 進行篩選的 schedule
    fun scheduleForStopName(name: String): Flow<List<Schedule>> = scheduleDao.getStop(name)

}

// 建立 factory
class BusScheduleViewModelFactory(private val scheduleDao: ScheduleDao): ViewModelProvider.Factory {

    // 覆寫 create() method，對 view model 執行實例化(instantiate)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}