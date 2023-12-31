package com.koleff.resumeproject.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.koleff.resumeproject.R
import com.koleff.resumeproject.domain.wrappers.TickerData

class AdapterDashboard(private val tickersList: List<TickerData>):
    RecyclerView.Adapter<AdapterDashboard.ViewHolder>() {

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

        fun bindData(tickersData: TickerData) {
//            ivCompanyLogo use web scraper?
//                tvCompanyName.text = tickersData.name
//                    tvCompanyTag.text = tickersData.stockTag
//                    tvClosePrice.text =
//                    tvAmountOfChange.text =
//                    tvPercentageOfChange.text =
        }

        companion object{
           private val defaultClickListener: () -> Unit = {} //Open details screen...
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_stock, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.llSeparator.visibility = if (position < tickersList.size - 1) View.VISIBLE else View.GONE
        viewHolder.bindData(tickersList[position])
    }

    override fun getItemCount() = tickersList.size
}
