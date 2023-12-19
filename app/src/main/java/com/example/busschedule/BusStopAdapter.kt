package com.example.busschedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.databinding.BusStopItemBinding
import java.text.SimpleDateFormat
import java.util.Date

// 繼承 ListAdapter，列出 UI 的 Schedule object list 和 BusStopViewHolder class
/* 1. BusStopViewHolder: 傳遞即將定義的 DiffCallback type
   2. BusStopAdapter: 採用參數 onItemClicked() */

class BusStopAdapter(private val onItemClicked: (Schedule) -> Unit):
    ListAdapter<Schedule, BusStopAdapter.BusStopViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object: DiffUtil.ItemCallback<Schedule>() {

            override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                // 透過檢查 ID 來確認 object (database 的 row) 是否相同
                return oldItem.id == newItem.id
            }

            // 會檢查所有屬性 (不只是 ID) 是否相同
            override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
                // 可讓 ListAdapter 判斷 inserted、updated 及 deleted 哪些 item
                return oldItem == newItem
            }

        }

    }

    // 存取透過 layout 檔案建立的 view
    class BusStopViewHolder(private var binding: BusStopItemBinding): RecyclerView.ViewHolder(binding.root) {

        // 實作 bind() function
        @SuppressLint("SimpleDateFormat")
        fun bind(schedule: Schedule) {
            // 將 stopNameTextView 的 text 設為 stop name
            binding.stopNameTextView.text = schedule.stopName
            // 將 arrivalTimeTextView 的 text 設為格式化日期
            binding.arrivalTimeTextView.text = SimpleDateFormat("h:mm a")
                .format(Date(schedule.arrivalTime.toLong() * 1000))
        }
    }

    // // 覆寫並實作 onCreateViewHolder()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        val viewHolder = BusStopViewHolder(
            // 加載 layout
            BusStopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        // 將 onClickListener() 設定為針對 current position 的 item 呼叫 onItemClicked()
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    // 覆寫並實作 onBindViewHolder()
    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        // 在指定位置(position) bind view
        holder.bind(getItem(position))
    }

}