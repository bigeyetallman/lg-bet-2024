package com.bigeyetallman.welcometopranking.list

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bigeyetallman.welcometopranking.MainActivity
import com.bigeyetallman.welcometopranking.R
import com.bigeyetallman.welcometopranking.databinding.UserChartListItemBinding.inflate
import com.bigeyetallman.welcometopranking.utils.Values


class MainUserChartListViewAdapter(
    private val context: Context,
    private val items: MutableList<UserChartItem>
) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): UserChartItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val binding = inflate(LayoutInflater.from(context))

        if (position + 1 > items.size) {
            return binding.root
        }

        val item = items[position]

        binding.textViewRanking.text = (position + 1).toString()

        binding.textViewName.text = item.name

        binding.textViewTotalPoint.text = String.format("%.2f", item.totalPoint)

        Values.playerSelectMap[item.name]?.forEach { playerName ->
            var playerView = View.inflate(context, R.layout.user_chart_player_item, null)
            (playerView.findViewById<View>(R.id.text_view_player_name) as TextView).text =
                playerName
            (playerView.findViewById<View>(R.id.text_view_player_point) as TextView).text =
                String.format("%.2f", (context as MainActivity).values.playerPointMap[playerName])

            binding.layoutPlayerList.addView(playerView)
        }

        return binding.root
    }
}