# Date And Time Change Listener
This program sets a listener for changing time and date of android device by a BroadcastReceiver.
Type of Broadcast Actions that are used:
- ACTION_TIME_TICK
- ACTION_TIMEZONE_CHANGED
- ACTION_TIME_CHANGED
- ACTION_DATE_CHANGED



## Tech Stack

Kotlin

## Source code

### Register BroadcastReceiver in AndroidManifest.xml
AndroidManifest.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="ir.mjahanbazi.datetimechangelistener">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.DateTimeChangeListener">
        <activity android:name="ir.mjahanbazi.datetimechangelistener.MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <receiver android:name="ir.mjahanbazi.datetimechangelistener.DateTimeChangeBroadcastReceiver">
            <intent-filter>
                <action android:name="android.intent.action.TIME_SET"/>
                <action android:name="android.intent.action.TIMEZONE_CHANGED"/>
                <action android:name="android.intent.action.TIME_TICK"/>
                <action android:name="android.intent.action.DATE_CHANGED"/>
            </intent-filter>
        </receiver>
    </application>

</manifest>
```
### Intent.ACTION_TIME_TICK: Register explicitly with Context.registerReceiver() in MainActivity.kt
        
MainActivity.kt
```kotlin
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
```
DateTimeChangeBroadcastReceiver.kt
```kotlin
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
```
## Contact

mjahanbazi@protonmail.com