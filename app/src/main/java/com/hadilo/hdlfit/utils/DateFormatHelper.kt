package com.hadilo.hdlfit.utils

import android.util.Log

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by null on 17/01/2017.
 */

object DateFormatHelper {

    private val TAG = "DateFormatter"

    val currentTimeStamp: String
        get() = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()).toString() + ""

    //"2015-09-11T04:11:41.000Z
    val currentDateTime1: String
        get() {
            val sdfDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val now = Date()
            return sdfDate.format(now)
        }

    //"2015-09-11T04:11:41Z
    val currentDateTime2: String
        get() {
            val sdfDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val now = Date()
            return sdfDate.format(now)
        }

    fun localToUTC(time: String): String {
        val f = SimpleDateFormat("HH:mm:ss")
        f.timeZone = TimeZone.getTimeZone("UTC")


        val fPilih = SimpleDateFormat("HH:mm:ss")
        var date = Date()
        var dd = ""
        try {
            date = fPilih.parse(time)
            dd = f.format(date)
            println(dd)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return dd
    }

    fun localToUTC(): String { // current berhasil
        val fOutput = SimpleDateFormat("HH:mm")
        fOutput.timeZone = TimeZone.getTimeZone("UTC")

        val dd = fOutput.format(Date())
        println(dd)
        return dd
    }

    /***
     * yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     * @param date
     * @return
     */
    fun convertToDate(date: String): Date? {//2015-12-17T11:14:34Z
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        format.timeZone = TimeZone.getTimeZone("UTC")
        try {
            val dateResult = format.parse(date)
            Log.d("Date_Result", dateResult.toString())
            return dateResult
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            return null
        }

    }

    /**
     * output : February 21, 2015
     * @param time
     * @return
     */
    fun parseDateToMMMMddyyyy(time: String): String? {
        val inputPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val outputPattern = "MMMM dd, yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat(outputPattern)

        var str: String? = null

        try {
            val date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str
    }

    fun parseDate(time: String, format: String): String? {
        val inputPattern = "yyyy-MM-dd HH:mm:ss"
        val inputFormat = SimpleDateFormat(inputPattern)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        //        inputFormat.
        val outputFormat = SimpleDateFormat(format)

        var str: String? = null

        try {
            val date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str
    }

    /**
     * output : February 21, 2015
     * @param time
     * @return
     */
    fun parseDateToMMMMddyyyy_v2(time: String): String? {
        val inputPattern = "yyyy-MM-dd"
        val outputPattern = "MMMM dd, yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat(outputPattern)

        var str: String? = null

        try {
            val date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str
    }

    /**
     * output : Oct 21, 2015 03:43:36 am
     * @param date
     * @return
     */
    fun parseDateTo_MMM_dd_yyyy_KKmmss_a(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("MMM dd, yyyy KK:mm:ss a")
        try {
            val value = outputFormat.format(inputFormat.parse(date))
            val val1 = value.substring(0, value.length - 2)
            val val2 = value.substring(value.length - 2).toUpperCase()
            return val1 + val2
        } catch (e: ParseException) {
            e.printStackTrace()
            return "error..."
        }

    }

    /**
     * output : Oct 21, 2015 03:43:36 am
     * @param date
     * @return
     */
    fun parseDateTo_EEEEddyyyy_KKmma(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val outputFormat = SimpleDateFormat("EEEE, MMM dd yyyy-KK.mm a")
        try {
            val value = outputFormat.format(inputFormat.parse(date))
            val val1 = value.substring(0, value.length - 2)
            val val2 = value.substring(value.length - 2).toUpperCase()
            return val1 + val2
        } catch (e: ParseException) {
            e.printStackTrace()
            return "error..."
        }

    }

    /**
     * input : date = 2016-11-25 but month must +1, because month from datepicker start from 0 not from 1
     * output : Wed, 21 Nov 2016
     * @param year month day
     * @return
     */
    fun parseDatePickerTo_EEE_dd_MMM_yyyy(year: Int, month: Int, day: Int): String {
        val date =
            year.toString() + "-" + (month + 1) + "-" + day // but month must +1, because month from datepicker start from 0 not from 1
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("EEE, dd MMM yyyy")
        try {
            val value = outputFormat.format(inputFormat.parse(date))
            val val1 = value.substring(0, value.length - 2)
            val val2 = value.substring(value.length - 2).toUpperCase()
            return val1 + val2
        } catch (e: ParseException) {
            e.printStackTrace()
            return "error..."
        }

    }


    fun convertTimeStampSecondToDate(timestamp: Long): String {
        val cal = Calendar.getInstance()
        val tz = cal.timeZone

        /* debug: is it local time? */
        Log.d("Time zone: ", tz.displayName)

        /* date formatter in local timezone */
        val sdf = SimpleDateFormat("dd MMMM yyyy-HH:mm")
        sdf.timeZone = tz

        /* print your timestamp and double check it's the date you expect */
        val localTime =
            sdf.format(Date(timestamp * 1000L)) // I assume your timestamp is in seconds and you're converting to milliseconds?
        Log.d("Time: ", timestamp.toString() + "<>"+ localTime)

        return localTime
    }

    fun convertTimeStampMilisecondToDate(timestamp: Long): String {
        val cal = Calendar.getInstance()
        val tz = cal.timeZone

        /* debug: is it local time? */
        Log.d("Time zone: ", tz.displayName)

        /* date formatter in local timezone */
        val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy-HH:mm", Locale.getDefault()) //Locale("in", "ID")
        sdf.timeZone = tz

        /* print your timestamp and double check it's the date you expect */
        val localTime = sdf.format(Date(timestamp)) // I assume your timestamp is in seconds and you're converting to milliseconds?

        return localTime
    }

}
