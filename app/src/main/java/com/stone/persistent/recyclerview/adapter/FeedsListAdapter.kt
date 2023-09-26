package com.stone.persistent.recyclerview.adapter

import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.stone.persistent.recyclerview.R
import com.stone.persistent.recyclerview.adapter.viewholder.CarouselViewHolder
import com.stone.persistent.recyclerview.adapter.viewholder.FeedsViewHolder
import com.stone.persistent.recyclerview.adapter.viewholder.LoadingViewHolder
import com.stone.persistent.recyclerview.utils.NetManager
import kotlinx.android.synthetic.main.item_feeds_product.view.*
import java.util.logging.Handler


/**
 * 商品流列表Adapter
 */
class FeedsListAdapter(context: FragmentActivity) :
    RecyclerView.Adapter<FeedsListAdapter.GoodsViewHolder>() {

    companion object {
        // 轮播图
        private const val VIEW_TYPE_PRODUCT = 0

        // 正在加载tabs
        private const val VIEW_TYPE_LOADING_TABS = 1

    }
    private val inflater = LayoutInflater.from(context)

    private val dataList = NetManager.getProductList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        return when (viewType) {
            VIEW_TYPE_PRODUCT -> {
                val itemView = inflater.inflate(R.layout.item_feeds_product, parent, false)
                return GoodsViewHolder(itemView = itemView)
            }
            else -> {
                // 商品流
                val itemView = inflater.inflate(R.layout.item_loading_footer, parent, false)
                LoadingViewHolder(itemView)
            }
        }

    }

    override fun getItemCount(): Int = dataList.size + 1

    fun setLoadingTabsListener(listener: () -> Unit) {
//        this.loadingTabsListener = listener
    }


    var index=1;
//    private var loadingTabsListener: (() -> Unit)? = null
    private var loadingTabsListener: (() -> Unit)? = {


//    notifyDataSetChanged()
//    isLoading=false

    android.os.Handler().apply {
        this.postDelayed(Runnable {
            ++index
            dataList.addAll(NetManager.getProductList2(index))
           notifyDataSetChanged()
            isLoading=false
        },500)
    }
    }
    var isLoading=false
    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {

        if (holder is LoadingViewHolder) {
            if (isLoading){
                return
            }
            isLoading=true
            // 加载tabs
            loadingTabsListener?.invoke()
        } else if (holder is GoodsViewHolder) {
            holder.bindPosition(position)
        }


    }

    override fun getItemViewType(position: Int): Int = if (position < dataList.size ){
         FeedsListAdapter.VIEW_TYPE_PRODUCT
    }else{
         FeedsListAdapter.VIEW_TYPE_LOADING_TABS
    }




    open inner class GoodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * 刷新View
         */
        fun bindPosition(position: Int) {
//            val itemData = dataList.get(position % 6)
            val itemData = dataList.get(position)
            itemView.goods_title_tv.text = itemData.goodName
            itemView.goods_imageview.setImageResource(itemData.imagRes)
            itemView.goods_price_tv.text = itemData.price

            val imageLp = itemView.goods_imageview.layoutParams as ConstraintLayout.LayoutParams
            imageLp.dimensionRatio = itemData.dimensionRatio.toString()
            itemView.goods_imageview.layoutParams = imageLp
        }
    }


    inner class LoadingViewHolder(itemView: View) : GoodsViewHolder(itemView)

}