package net.kibotu.androiddatabases.objectbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.kibotu.androiddatabases.R

class ObjectBoxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes)
    }
}