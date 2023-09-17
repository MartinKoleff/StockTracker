package com.koleff.resumeproject.adapters

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.koleff.resumeproject.KoleffApp
import com.koleff.resumeproject.R
import com.koleff.resumeproject.types.KoleffFonts


class AdapterNavigationSettings(
    private val context: Context,
    private val navigationOptions: List<SettingItem>
) : BaseAdapter() {

    override fun getCount() = navigationOptions.size

    override fun getItem(position: Int) = navigationOptions.get(position)

    override fun getItemId(position: Int): Long = navigationOptions.get(position).SettingID.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater =
            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val settingItem = getItem(position)

        val settingView: View = layoutInflater.inflate(R.layout.item_setting, null)
        val holder = ViewHolder()

        holder.imageView = settingView.findViewById(R.id.image_view)
        holder.textTitle = settingView.findViewById(R.id.text_title)
        holder.lineSeparator = settingView.findViewById(R.id.llSeparator)

        holder.imageView.setImageResource(settingItem.ImageID)
        holder.textTitle.text = settingItem.Name
        holder.textTitle.textSize = 17.0f

//        holder.textTitle.setTypeface(KoleffFonts.interBoldFont, Typeface.NORMAL) //can't find font...
        holder.textTitle.setTextColor(-0x848478)
        holder.imageView.setColorFilter(-0x848478)


        holder.lineSeparator.setBackgroundColor(-0x10100c)

        return settingView
    }

    class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var textTitle: TextView
        lateinit var lineSeparator: LinearLayout
//        var switchButton: SwitchButton? = null
    }

}

class SettingItem(var SettingID: Int, var Name: String, var ImageID: Int) {
    var isSwitchOn = false

    companion object {
        const val id_language = 0
        const val id_password = 1
        const val id_email = 2
        const val id_logout = 3
        const val id_biometrics = 4
        const val id_notifications = 5
        const val id_night_mode = 6
    }
}

