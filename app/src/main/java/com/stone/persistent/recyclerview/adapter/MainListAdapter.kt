package com.stone.persistent.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.stone.persistent.recyclerview.R
import com.stone.persistent.recyclerview.adapter.viewholder.CarouselViewHolder
import com.stone.persistent.recyclerview.adapter.viewholder.EmptyViewHolder
import com.stone.persistent.recyclerview.adapter.viewholder.FeedsViewHolder
import com.stone.persistent.recyclerview.adapter.viewholder.MenuViewHolder

class MainListAdapter(context: AppCompatActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: AppCompatActivity = context

    private val inflater = LayoutInflater.from(context)

    companion object {
        // 轮播图
        private const val VIEW_TYPE_CAROUSEL = 0

        // 菜单
        private const val VIEW_TYPE_MENU = 1

        // 秒杀的三张图
        private const val VIEW_TYPE_SEC_KILL_PREFIX = 2

        // 秒杀的内容
        private const val VIEW_TYPE_SEC_KILL_CONTENT = 3

        // 今日推荐
        private const val VIEW_TYPE_TODAY_RECOMMEND = 4

        // 新年大街
        private const val VIEW_TYPE_NEW_YEAR_STREET = 5

        // 商品流
        private const val VIEW_TYPE_FEEDS = 6
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_CAROUSEL
            1 -> VIEW_TYPE_MENU
            2 -> VIEW_TYPE_SEC_KILL_PREFIX
            3 -> VIEW_TYPE_SEC_KILL_CONTENT
            4 -> VIEW_TYPE_TODAY_RECOMMEND
            5 -> VIEW_TYPE_NEW_YEAR_STREET
            else -> VIEW_TYPE_FEEDS
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CAROUSEL -> {
                // 轮播图
                val itemView = inflater.inflate(R.layout.item_main_carousel, parent, false)
                CarouselViewHolder(itemView, context)
            }

            VIEW_TYPE_MENU -> {
                // 菜单
                val itemView = inflater.inflate(R.layout.item_main_menu, parent, false)
                MenuViewHolder(itemView, context)
            }

            VIEW_TYPE_SEC_KILL_PREFIX -> {
                // 秒杀的三张图
                val itemView = inflater.inflate(R.layout.item_sec_kill_prefix, parent, false)
                EmptyViewHolder(itemView)
            }

            VIEW_TYPE_SEC_KILL_CONTENT -> {
                // 秒杀的内容
                val itemView = inflater.inflate(R.layout.item_sec_kill_content, parent, false)
                EmptyViewHolder(itemView)
            }

            VIEW_TYPE_TODAY_RECOMMEND -> {
                // 今日推荐
                val itemView = inflater.inflate(R.layout.item_today_recommend, parent, false)
                EmptyViewHolder(itemView)
            }

            VIEW_TYPE_NEW_YEAR_STREET -> {
                // 今日推荐
                val itemView = inflater.inflate(R.layout.item_new_year_street, parent, false)
                EmptyViewHolder(itemView)
            }

            else -> {
                // 商品流
                val itemView = inflater.inflate(R.layout.item_main_feeds, parent, false)
                FeedsViewHolder(itemView, context)
            }
        }
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // do nothing at the moment
    }
}