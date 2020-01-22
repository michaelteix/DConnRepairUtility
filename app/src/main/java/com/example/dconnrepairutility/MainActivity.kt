package com.example.dconnrepairutility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dconnrepairutility.UtilityViewModel.UtilityVM.StartDaemon
import com.example.dconnrepairutility.UtilityViewModel.UtilityVM.StopDaemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val utilityVM = UtilityViewModel()

        StartDaemonButton.setOnClickListener {
            utilityVM.run { StartDaemon() }
        }

        StopDaemonButton.setOnClickListener {
            utilityVM.run { StopDaemon() }
        }

        LogFileButton.setOnClickListener {

        }

        EditButton.setOnClickListener {

        }
    }
}
