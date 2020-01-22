package com.example.dconnrepairutility

class UtilityViewModel
{
    companion object UtilityVM
    {
        private val daemon = Thread {
            while (true)
            {
                // Check connection speed

                // If connection below the threshold run repair service

                Thread.sleep((timeInterval).toLong())
            }
        }

        var lastConnectionName = "None"
        var lastConnectionSpeed = 0.0
        var isActive = false
        var timeInterval = 30
        var downloadThreshold = 2

        fun StartDaemon()
        {
            isActive = true
            daemon.start()
        }

        fun StopDaemon()
        {
            isActive = false
            daemon.interrupt()
        }
    }
}
