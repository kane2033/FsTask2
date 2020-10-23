package com.fosusstart.task2.ui.main.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.fosusstart.task2.R

/*
* Фрагмент, отображающий результат вычислений сервиса
* */
class ServiceFragment : Fragment(){

    companion object {
        fun newInstance() = ServiceFragment()
    }

    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layoutInflater = inflater.inflate(R.layout.service_fragment, container, false)
        val countView = layoutInflater.findViewById<TextView>(R.id.countView) // Поле вывода результата сервиса

        // Создание бродкаста, с помощью которого будет получена переменная
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                countView.text = intent?.getIntExtra(ServiceConstants.counterIntentValue, 0).toString()
            }
        }

        // Регистрация бродкаста
        val intentFilter = IntentFilter(ServiceConstants.counterIntentAction)
        activity?.registerReceiver(broadcastReceiver, intentFilter)

        // Intent CounterService
        val serviceClass = CounterService::class.java
        val serviceIntent = Intent(activity, serviceClass)

        // Кнопка запуска сервиса
        layoutInflater.findViewById<Button>(R.id.start_service_button)
            .setOnClickListener() {
                activity?.startService(serviceIntent)
            }

        // Кнопка остановки сервиса
        layoutInflater.findViewById<Button>(R.id.stop_service_button)
            .setOnClickListener() {
                activity?.stopService(serviceIntent)
                countView.text = "0"
            }

        return layoutInflater
    }

    override fun onDestroy() {
        super.onDestroy()
        // Отписка от получения обновлений с сервиса
        // при выходе из фрагмента
        activity?.unregisterReceiver(broadcastReceiver)
    }
}