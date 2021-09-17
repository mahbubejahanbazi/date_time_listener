package ir.mjahanbazi.datetimechangelistener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateTimeChangeBroadcastReceiver : BroadcastReceiver() {
    private val dateFormat: DateFormat = SimpleDateFormat("EEEE dd MMMM - HH:MM:SS")
    override fun onReceive(context: Context?, intent: Intent?) {

        val action = intent!!.action
        if (action != null
            && (action == Intent.ACTION_TIME_CHANGED
                    || action == Intent.ACTION_TIMEZONE_CHANGED
                    || action == Intent.ACTION_DATE_CHANGED
                    || action == Intent.ACTION_TIME_TICK)) {
            val today = Calendar.getInstance().time
            val strToday: String = dateFormat.format(today)
            Log.i("TIME_CHANGED  ", "action: $action  date: $strToday")
        }
    }
}
