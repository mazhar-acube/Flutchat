package stllpt.com.flutchat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.TimeUnit
//import io.flutter.embedding.android.FlutterActivity
import io.flutter.app.FlutterActivity
import io.flutter.embedding.engine.plugins.PluginRegistry
import stllpt.com.flutchat.ui.InitAuthSDKActivity

import us.zoom.sdk.ZoomError

import us.zoom.sdk.ZoomSDK

import us.zoom.sdk.ZoomSDKInitParams

import us.zoom.sdk.ZoomSDKInitializeListener


/**
 * Entry point for the android project.
 */
//abstract class FirstActivity : FlutterActivity(), io.flutter.plugin.common.PluginRegistry {
//    private var result: MethodChannel.Result? = null
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 300) {
//            data?.extras?.let { it ->
//                val callStartDate: Date = it.getSerializable("callStartTime") as Date
//                result?.success("${callStartDate.getDuration().first}:" +
//                        "${callStartDate.getDuration().second}:" +
//                        callStartDate.getDuration().third)
//            }
//        }
//    }
class FirstActivity : FlutterActivity() {
    private var result: MethodChannel.Result? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)
        MethodChannel(flutterView, "com.stl.flutchat/opentok")
                .setMethodCallHandler { methodCall, result ->
                    methodCall.method?.let {
                        if (it.contentEquals("openVideoChat")) {
                            this@FirstActivity.result = result
                            startActivityForResult(Intent(this, VideoActivity::class.java), 300)
                        }
                        if (it.contentEquals("openZoomChat")) {
//                            this@FirstActivity.result = result
                            startActivityForResult(Intent( this, InitAuthSDKActivity::class.java), 300)
//                            initializeSdk(this);
                        }
                    }
                }
    }

    private fun initializeSdk(context: Context) {
        val sdk = ZoomSDK.getInstance()

        // TODO: Do not use hard-coded values for your key/secret in your app in production!
        val params = ZoomSDKInitParams().apply {
            appKey = "wNSLYyfFeNm5Tx8HQmkpQAnbH1DrOaYpZyPo" // TODO: Retrieve your SDK key and enter it here
            appSecret = "N2uPYUmGI4F7V8imgR8lVAF2S1PB3MrRQpYL" // TODO: Retrieve your SDK secret and enter it here
            domain = "zoom.us"
            enableLog = true // Optional: enable logging for debugging
        }
        // TODO (optional): Add functionality to this listener (e.g. logs for debugging)
        val listener = object : ZoomSDKInitializeListener {
            /**
             * If the [errorCode] is [ZoomError.ZOOM_ERROR_SUCCESS], the SDK was initialized and can
             * now be used to join/start a meeting.
             */
            override fun onZoomSDKInitializeResult(errorCode: Int, internalErrorCode: Int) = Unit
            override fun onZoomAuthIdentityExpired() = Unit
        }

        sdk.initialize(context, listener, params)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 300) {
            data?.extras?.let { it ->
                val callStartDate: Date = it.getSerializable("callStartTime") as Date
                result?.success("${callStartDate.time}:")
            }
        }
    }
}
   /**
     * Extension function to get time difference for current time and provided time.
     */
//    private fun Date.getDuration(): Triple<String, String, String> {
//        val todayDate = Calendar.getInstance()
//        todayDate.time = this
//        val serverDate = Calendar.getInstance()
//        serverDate.time = Date()
//        serverDate.set(todayDate.get(Calendar.YEAR),
//                todayDate.get(Calendar.MONTH),
//                todayDate.get(Calendar.DATE))
//        val hours: Long
//        val minutes: Long
//        val seconds: Long
//        return if (serverDate.after(todayDate)) {
//            hours = TimeUnit.MILLISECONDS.toHours(serverDate.timeInMillis - todayDate.timeInMillis)
//            minutes = TimeUnit.MILLISECONDS.toMinutes(serverDate.timeInMillis - todayDate.timeInMillis)
//            seconds = TimeUnit.MILLISECONDS.toSeconds(serverDate.timeInMillis - todayDate.timeInMillis)
//            Triple(hours.displayAsTwoDecimal(),
//                    (minutes - 60 * hours).displayAsTwoDecimal(),
//                    (seconds - 60 * minutes).displayAsTwoDecimal())
//        } else {
//            Triple("", "", "")
//        }
//    }

    /**
     * Extension function to display number as 2 digit decimals.
     */
    private fun Long.displayAsTwoDecimal(): String {
        val df = DecimalFormat("00")
        df.maximumFractionDigits = 2
        return df.format(this)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        GeneratedPluginRegistrant.registerWith(this)
//        MethodChannel(flutterView, "com.stl.flutchat/opentok")
//                .setMethodCallHandler { methodCall, result ->
//                    methodCall.method?.let {
//                        if (it.contentEquals("openVideoChat")) {
//                            this@FirstActivity.result = result
//                            startActivityForResult(Intent( this, VideoActivity::class.java), 300)
//                        }
//                    }
//                }
//    }
//
//}

    