package net.kibotu.androiddatabases.room

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.exozet.android.core.extensions.textTrimmed
import kotlinx.android.synthetic.main.notes.*
import kotlinx.coroutines.launch
import net.kibotu.androiddatabases.NoteActivity
import net.kibotu.androiddatabases.room.model.Note


class RoomActivity : NoteActivity() {

    val db by lazy { NoteDatabase.create() }

    val dao by lazy { db.noteDao() }

    override fun subscribe() {
        dao.getAll().observe(this) {
            onItems(it)
        }
    }

    override fun deleteAll() {
        lifecycleScope.launch {
            dao.deleteAll()
        }
    }

    override fun insert() {
        lifecycleScope.launch {
            val text = input.textTrimmed
            if (text.isNotEmpty()) {
                dao.insert(Note(text = text))
                input.setText("")
            }
        }
    }
}