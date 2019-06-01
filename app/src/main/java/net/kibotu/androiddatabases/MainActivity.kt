package net.kibotu.androiddatabases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.kibotu.logger.Level
import net.kibotu.logger.LogcatLogger
import net.kibotu.logger.Logger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogger(LogcatLogger(), Level.VERBOSE)
    }
}
