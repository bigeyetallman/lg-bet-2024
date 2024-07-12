package com.bigeyetallman.welcometopranking

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.bigeyetallman.welcometopranking.databinding.ActivityMainBinding
import com.bigeyetallman.welcometopranking.list.MainUserChartListViewAdapter
import com.bigeyetallman.welcometopranking.list.UserChartItem
import com.bigeyetallman.welcometopranking.utils.CustomProgressDialog
import com.bigeyetallman.welcometopranking.utils.Urls
import com.bigeyetallman.welcometopranking.utils.Utils
import com.bigeyetallman.welcometopranking.utils.Values
import org.jsoup.Jsoup
import java.util.LinkedList

class MainActivity : AppCompatActivity(), OnClickListener {

    companion object {
        private var TAG = ""
    }

    private lateinit var binding: ActivityMainBinding

    var values = Values()

    private var isLoading = false;
    private lateinit var loadingDialog: CustomProgressDialog

    private val totalPitcherPage = 2
    private var currentPitcherPage = 0

    private val totalHitterPage = 2
    private var currentHitterPage = 0

    private val userChartListItems = mutableListOf<UserChartItem>()
    private var mainUserChartListViewAdapter: MainUserChartListViewAdapter? = null
    private lateinit var headerUserChartListView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TAG = getString(R.string.title_activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingDialog =
            Utils.getCustomProgressDialog(this, getString(R.string.loading_progress_message))!!

        headerUserChartListView = layoutInflater.inflate(
            R.layout.header_user_chart_list,
            null,
            false
        )

        binding.listViewUserChart.addHeaderView(headerUserChartListView)

        mainUserChartListViewAdapter =
            MainUserChartListViewAdapter(this, userChartListItems)
        binding.listViewUserChart.adapter = mainUserChartListViewAdapter

        init()

        initToBindEventListener()
    }

    private fun setIsLoading(value: Boolean) {
        isLoading = value
        if (isLoading) {
            if (!isFinishing) {
                loadingDialog.show()
            }
        } else {
            loadingDialog.dismiss()
        }
    }

    override fun onDestroy() {
        loadingDialog.dismiss()
        super.onDestroy()
    }

    private fun init() {
        getPointData()
    }

    private fun getPointData() {
        setIsLoading(true)
        currentPitcherPage = 0
        currentHitterPage = 0
        values = Values()
        getPitcherData()
    }

    private fun initToBindEventListener() {
        binding.btnRefresh.setOnClickListener(this)
    }

    private fun getPitcherData() {
        currentPitcherPage += 1

        val targetUrl = Urls.getPitcherUrl(currentPitcherPage)

        Thread {
            val doc = Jsoup.connect(targetUrl).get()

            val pitcherTableElement = doc.select(".rank_filed")

            val tableString = pitcherTableElement.text()

            val tableWordList = tableString.split(" ")

            var index = 0
            for (word in tableWordList) {
                if (values.playerPointMap.containsKey(word)) {
                    values.playerPointMap[word] = tableWordList[index + 2].toDouble()
                }
                if (values.newPlayerPointMap.containsKey(word)) {
                    values.newPlayerPointMap[word] =
                        tableWordList[index + 2].toDouble() - Values.oldScoreMap[word]!!
                }
                index += 1
            }

            if (currentPitcherPage < totalPitcherPage) {
                getPitcherData()
            } else {
                getHitterData()
            }

        }.start()
    }

    private fun getHitterData() {
        currentHitterPage += 1

        val targetUrl = Urls.getHitterUrl(currentHitterPage)

        Thread {
            val doc = Jsoup.connect(targetUrl).get()

            val pitcherTableElement = doc.select(".rank_filed")

            val tableString = pitcherTableElement.text()

            val tableWordList = tableString.split(" ")

            var index = 0
            for (word in tableWordList) {
                if (values.playerPointMap.containsKey(word)) {
                    values.playerPointMap[word] = tableWordList[index + 2].toDouble()
                }
                if (values.newPlayerPointMap.containsKey(word)) {
                    values.newPlayerPointMap[word] =
                        tableWordList[index + 2].toDouble() - Values.oldScoreMap[word]!!
                }
                index += 1
            }

            if (currentHitterPage < totalHitterPage) {
                getHitterData()
            } else {
                calculateUserTotalPoint()
            }

        }.start()
    }

    private fun calculateUserTotalPoint() {

        Log.e(TAG, values.playerPointMap.toString())

        Values.playerSelectMap.forEach { entry ->
            var totalPoint = 0.0
            for (selectedPlayer in entry.value) {
                totalPoint += values.playerPointMap[selectedPlayer]!!
            }

            if(Values.newPlayerSelectMap.containsKey(entry.key)){
                for (newPlayer in Values.newPlayerSelectMap[entry.key]!!) {
                    totalPoint += values.newPlayerPointMap[newPlayer]!!
                }
            }

            if(Values.removePlayerSelectMap.containsKey(entry.key)){
                for (removePlayer in Values.removePlayerSelectMap[entry.key]!!) {
                    totalPoint += Values.oldScoreMap[removePlayer]!!
                }
            }

            values.userTotalPointMap[entry.key] = totalPoint
        }

        Log.e(TAG, values.userTotalPointMap.toString())

        setUserChartList()
    }

    private fun setUserChartList() {
        val entries = LinkedList(values.userTotalPointMap.entries)
        entries.sortByDescending { it.value }

        Log.e(TAG, entries.toString())

        // 이 순서대로 리스트에 추가
        userChartListItems.clear()

        for (user in entries) {
            userChartListItems.add(UserChartItem(0, user.key, user.value))
        }

        Handler(Looper.getMainLooper()).post {
            mainUserChartListViewAdapter?.notifyDataSetChanged()
        }

        setIsLoading(false)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnRefresh -> {
                getPointData()
            }
        }
    }
}