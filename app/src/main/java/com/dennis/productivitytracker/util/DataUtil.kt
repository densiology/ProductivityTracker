package com.dennis.productivitytracker.util

import android.annotation.SuppressLint
import com.dennis.productivitytracker.R
import java.text.SimpleDateFormat
import java.util.*

object DataUtil {

    @SuppressLint("SimpleDateFormat")
    fun String.toDate() : Date? {
        return SimpleDateFormat("yyyy-MM-dd").parse(this)
    }

    @SuppressLint("SimpleDateFormat")
    fun Date.toSimpleString() : String {
        return SimpleDateFormat("yyyy-MM-dd").format(this)
    }

    fun getPriorityRange(): IntRange {
        return 1..10
    }

    fun getPartitionRange() : IntRange {
        return 1..96
    }
}