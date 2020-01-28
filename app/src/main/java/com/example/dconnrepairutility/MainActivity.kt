package com.example.dconnrepairutility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    var lastConnectionName = "None"
    var lastConnectionSpeed = 0
    var isActive = false
    var timeInterval = 30
    var downloadThreshold = 2
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StopDaemonButton.isEnabled = false
        LastConnectionValue.setText("Null")
        DownSpeedValue.setText("Null")
        TimeIntervalValue.setText(timeInterval.toString())
        ThresholdValue.setText(downloadThreshold.toString())

        StartDaemonButton.setOnClickListener {
            isActive = true
            isDaemonActiveLight.buttonTintList = getColorStateList(R.color.colorGreenStart)
            StartDaemonButton.isEnabled = false
            StopDaemonButton.isEnabled = true

            job = GlobalScope.launch(Dispatchers.IO) {
                d("daemon", "Daemon started with ThreadId: " + Thread.currentThread().id)
                d("daemon", "Daemon started with JobId: " + job!!.key)
                val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                var networkCapabilities: NetworkCapabilities?

                while (true)
                {
                    // Check connection speed
                    networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    lastConnectionName = networkCapabilities.toString()
                    lastConnectionSpeed = networkCapabilities.linkDownstreamBandwidthKbps / 1000
                    d("daemon", "Tested connection to: " + lastConnectionName)
                    d("daemon", "Speed: " + lastConnectionSpeed)
                    //var upSpeed = nc.linkUpstreamBandwidthKbps
                    try {
                        LastConnectionValue.setText(lastConnectionName)
                    }
                    catch (e: Exception)
                    {
                        d("daemon", "Could not write string: " + lastConnectionName)
                    }
                    DownSpeedValue.setText(lastConnectionSpeed.toString())

                    // If connection below the threshold run repair service
                    if(lastConnectionSpeed < downloadThreshold * 1000)
                    {
                        // Open dialer and run code *#*#34963#*#*
                            // Alternatively run repair service and grab statistics from it
                    }

                    delay(timeInterval.toLong() * 60000)
                }
            }
        }

        StopDaemonButton.setOnClickListener {
            isActive = false
            StopDaemonButton.isEnabled = false
            StartDaemonButton.isEnabled = true
            isDaemonActiveLight.buttonTintList = getColorStateList(R.color.colorRedStatusLight)
            job!!.cancel()
        }

        LogFileButton.setOnClickListener {

        }

        EditButton.setOnClickListener {

        }
    }
}
