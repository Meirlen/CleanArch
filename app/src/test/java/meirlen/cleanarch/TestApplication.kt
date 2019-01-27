package meirlen.cleanarch

import android.app.Application
import org.koin.android.ext.android.startKoin


class TestApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, emptyList())
    }
}
