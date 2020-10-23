package com.fosusstart.task2.ui.main.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class CounterService : Service() {

    private val executorService = Executors.newSingleThreadScheduledExecutor()
    private lateinit var future: ScheduledFuture<*>

    override fun onBind(p0: Intent?): IBinder? = null // Приложение не будет связываться с другими, => null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.w("COUNT_SERVICE", "Service started!")

        var count = 0
        // Подсчет каждую секунду
        future = executorService.scheduleAtFixedRate({
            count++
            Log.w("COUNT_SERVICE", "counts = $count")

            // Отправка значения в фрагмент
            val broadcastIntent = Intent(ServiceConstants.counterIntentAction)
            broadcastIntent.putExtra(ServiceConstants.counterIntentValue, count)
            sendBroadcast(broadcastIntent)

        }, 0, 1, TimeUnit.SECONDS)

        return START_STICKY // Такой режим используется, если служба явно запускается и останавливается
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("COUNT_SERVICE", "Service stopped!")
        future.cancel(true) // Отменяем подсчет при остановке сервиса
    }
}
