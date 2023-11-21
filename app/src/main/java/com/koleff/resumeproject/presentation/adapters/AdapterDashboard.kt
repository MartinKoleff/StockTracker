package com.koleff.resumeproject.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.koleff.resumeproject.R
import com.koleff.resumeproject.domain.wrappers.StockData

typealias TickerData = List<StockData>

class AdapterDashboard :
    RecyclerView.Adapter<AdapterDashboard.ViewHolder>() {

    private var tickersList: MutableList<StockData> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: TickerData) {
        tickersList.clear()
        tickersList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_stock, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val stockItem = tickersList[position]

        viewHolder.llSeparator.visibility =
            if (position < tickersList.size - 1) View.VISIBLE else View.GONE

//        ivCompanyLogo use web scraper?
        viewHolder.tvCompanyName.text = stockItem.companyName
        viewHolder.tvCompanyTag.text = stockItem.stockTag
        viewHolder.tvClosePrice.text = stockItem.closePrice.toString()
        viewHolder.tvAmountOfChange.text = stockItem.dayDifference.toString()
        viewHolder.tvPercentageOfChange.text = stockItem.changePercent.toString()
    }

    override fun getItemCount() = tickersList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCompanyLogo: ImageView
        val tvCompanyName: TextView
        val tvCompanyTag: TextView
        val tvClosePrice: TextView
        val tvAmountOfChange: TextView
        val tvPercentageOfChange: TextView
        val llSeparator: LinearLayout
        //graph

        init {
            ivCompanyLogo = view.findViewById(R.id.iv_company_logo)
            tvCompanyName = view.findViewById(R.id.tv_company_name)
            tvCompanyTag = view.findViewById(R.id.tv_company_tag)
            tvClosePrice = view.findViewById(R.id.tv_close_price)
            tvAmountOfChange = view.findViewById(R.id.tv_amount_of_change)
            tvPercentageOfChange = view.findViewById(R.id.tv_percentage_of_change)
            llSeparator = view.findViewById(R.id.llSeparator)
            //graph

            view.setOnClickListener { defaultClickListener.invoke() }
        }

        companion object {
            private val defaultClickListener: () -> Unit = {} //Open details screen...
        }
    }
}
