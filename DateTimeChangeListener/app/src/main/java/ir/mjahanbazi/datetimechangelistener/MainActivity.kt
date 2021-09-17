package ir.mjahanbazi.datetimechangelistener

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentFilter = IntentFilter()
        /**
         * Broadcast Action: The current time has changed.  Sent every
         * minute.  You cannot receive this through components declared
         * in manifests, only by explicitly registering for it with
         * Context.registerReceiver().
         */
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        /**
         * Broadcast Action: The timezone has changed.
         */
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED)
        /**
         * Broadcast Action: The time was set.
         */
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED)
        /**
         * Broadcast Action: The date has changed.
         */
        intentFilter.addAction(Intent.ACTION_DATE_CHANGED)
        registerReceiver(DateTimeChangeBroadcastReceiver(), intentFilter)
    }
}
