package com.koleff.resumeproject.presentation.adapters

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

class AdapterDashboard(private val tickersList: TickerData) :
    RecyclerView.Adapter<AdapterDashboard.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivCompanyLogo: ImageView
        private val tvCompanyName: TextView
        private val tvCompanyTag: TextView
        private val tvClosePrice: TextView
        private val tvAmountOfChange: TextView
        private val tvPercentageOfChange: TextView
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

        fun bindData(tickerData: StockData) {
//            ivCompanyLogo use web scraper?
            tvCompanyName.text = tickerData.companyName
            tvCompanyTag.text = tickerData.stockTag
            tvClosePrice.text = tickerData.closePrice.toString()
            tvAmountOfChange.text = tickerData.dayDifference.toString()
            tvPercentageOfChange.text = tickerData.changePercent.toString()
        }

        companion object {
            private val defaultClickListener: () -> Unit = {} //Open details screen...
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_stock, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.llSeparator.visibility =
            if (position < tickersList.size - 1) View.VISIBLE else View.GONE
        viewHolder.bindData(tickersList[position])
    }

    override fun getItemCount() = tickersList.size
}
