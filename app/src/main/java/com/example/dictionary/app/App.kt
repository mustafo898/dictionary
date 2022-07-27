package com.example.phone.app

import android.app.Application
import com.example.dictionary.database.Database
//import com.example.phone.database.Database
//import com.example.phone_db.database.Database

//import uz.micro.star.lesson_17.database.Database

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Database.init(this).createDatabase().open()
    }

    override fun onTerminate() {
        Database.getDatabase().close()
        super.onTerminate()
    }
}