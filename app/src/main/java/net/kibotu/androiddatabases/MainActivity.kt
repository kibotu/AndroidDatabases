package net.kibotu.androiddatabases

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exozet.android.core.extensions.onClick
import kotlinx.android.synthetic.main.activity_main.*
import net.kibotu.androiddatabases.objectbox.ObjectBoxActivity
import net.kibotu.androiddatabases.realm.RealmActivity
import net.kibotu.androiddatabases.room.RoomActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        room.onClick {
            startActivity(Intent(this, RoomActivity::class.java))
        }
        realm.onClick {
            startActivity(Intent(this, RealmActivity::class.java))
        }
        objectbox.onClick {
            startActivity(Intent(this, ObjectBoxActivity::class.java))
        }
    }
}