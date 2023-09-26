package com.stone.persistent.recyclerview.adapter.viewholder

import android.animation.ObjectAnimator
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.stone.persistent.recyclerview.R
import com.stone.persistent.recyclerview.adapter.FeedsPagerAdapter
import kotlinx.android.synthetic.main.item_main_feeds.view.*
import kotlinx.android.synthetic.main.item_main_feeds2.view.*
import kotlinx.android.synthetic.main.item_main_feeds2.view.main_feeds_tablayout
import kotlinx.android.synthetic.main.item_main_feeds2.view.main_feeds_view_pager


/**
 * 菜单ViewPager.ViewHolder
 */
class FeedsViewHolder2(itemView: View, activity: AppCompatActivity) :
    RecyclerView.ViewHolder(itemView) {

    private val tabList = ArrayList<TextView>()
    private val feedsViewPager = itemView.main_feeds_view_pager

    companion object {
        private val COLOR_TAB_NORMAL by lazy { Color.parseColor("#333333") }
        private val COLOR_TAB_SELECTED by lazy { Color.parseColor("#ff0000") }
    }

    init {
        feedsViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        feedsViewPager.adapter = FeedsPagerAdapter(activity)

        bindTabs()
    }

    private fun bindTabs() {
        val tabClickListener = View.OnClickListener {
            val index = tabList.indexOf(it)
            feedsViewPager.currentItem = index
        }

//        itemView.main_feeds_tablayout_test.setTabMode(TabLayout.MODE_AUTO);
        itemView.main_feeds_tablayout.setTabMode(TabLayout.MODE_AUTO);

//        itemView.main_feeds_tablayout.setupWithViewPager(feedsViewPager)
        TabLayoutMediator(itemView.main_feeds_tablayout,feedsViewPager,object :
            TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {

            }
        }).attach()

        setTabLayoutCustomIcons(itemView.main_feeds_tablayout,0)
//        itemView.main_feeds_tablayout.set

        itemView.main_feeds_tablayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                changeTabSelect(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                changeTabNormal(tab)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        itemView.main_feeds_tablayout.getTabAt(0)?.let { changeTabSelect(it) }
        feedsViewPager.currentItem = 0


//        tabList.add(itemView.feeds_tab1)
//        tabList.add(itemView.feeds_tab2)
//        tabList.add(itemView.feeds_tab3)
//        tabList.add(itemView.feeds_tab4)
//        tabList.add(itemView.feeds_tab5)
//        for (itemTab in tabList) {
//            itemTab.setOnClickListener(tabClickListener)
//        }

//        feedsViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                onTabChanged(position)
//            }
//        })
//        onTabChanged(0)
    }

    private fun onTabChanged(position: Int) {
        val num = tabList.size
        for (i in 0 until num) {
            val itemTab = tabList[i]
            if (i == position) {
                itemTab.setTextColor(COLOR_TAB_SELECTED)
                itemTab.paint.isFakeBoldText = true
                itemTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
            } else {
                itemTab.setTextColor(COLOR_TAB_NORMAL)
                itemTab.paint.isFakeBoldText = false
                itemTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
            }
        }
    }


    private fun changeTabSelect(tab: TabLayout.Tab) {
        val view = tab.customView
        val anim = ObjectAnimator
            .ofFloat(view, "", 1.0f, 1.2f)
            .setDuration(200)
        anim.start()
        anim.addUpdateListener { animation ->
            val cVal = animation.animatedValue as Float
            //        view.setAlpha(0.5f + (cVal - 1f) * (0.5f / 0.1f));
            view!!.scaleX = cVal
            view.scaleY = cVal
        }
    }

    /**
     * 改变TabLayout的View到未选中状态
     */
    private fun changeTabNormal(tab: TabLayout.Tab) {
        val view = tab.customView
        val anim = ObjectAnimator
            .ofFloat(view, "", 1.2f, 1f)
            .setDuration(200)
        anim.start()
        anim.addUpdateListener { animation ->
            val cVal = animation.animatedValue as Float
            //        view.setAlpha(1f - (1f - cVal) * (0.5f / 0.1f));
            view!!.scaleX = cVal
            view.scaleY = cVal
        }
    }


    private fun setTabLayoutCustomIcons(tabLayout: TabLayout, @DrawableRes iconResId: Int) {
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
//            tab!!.setIcon(iconResId)
            tab!!.setCustomView(R.layout.tab_layout_icon)
            val tabIcon = tab.customView!!.findViewById<ImageView>(R.id.mImageView)
            tabIcon.setImageResource(R.mipmap.icon_bottle)
        }
    }
}