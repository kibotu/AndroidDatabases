package net.kibotu.androiddatabases.realm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.kibotu.androiddatabases.R

class RealmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes)
    }
}