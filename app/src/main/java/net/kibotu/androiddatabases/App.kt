package net.kibotu.androiddatabases

import androidx.multidex.MultiDexApplication
import net.kibotu.logger.Level
import net.kibotu.logger.LogcatLogger
import net.kibotu.logger.Logger

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Logger.addLogger(LogcatLogger(), Level.VERBOSE)
    }
}