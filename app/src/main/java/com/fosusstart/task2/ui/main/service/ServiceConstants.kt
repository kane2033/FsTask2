package com.fosusstart.task2.ui.main.service

/*
* Класс хранит строковые константы, используемые для создания Intent.
* Хранение констант в отдельном классе необходимо, чтобы intent совпали.
* */
class ServiceConstants {
    companion object {
        const val counterIntentAction = "counterIntentAction"
        const val counterIntentValue = "count"
    }
}