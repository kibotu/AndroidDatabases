package net.kibotu.androiddatabases.room

import android.text.TextUtils
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.exozet.android.core.extensions.textTrimmed
import kotlinx.android.synthetic.main.notes.*
import kotlinx.coroutines.launch
import net.kibotu.android.recyclerviewpresenter.Presenter
import net.kibotu.android.recyclerviewpresenter.PresenterModel
import net.kibotu.androiddatabases.NoteActivity
import net.kibotu.androiddatabases.R
import net.kibotu.androiddatabases.room.model.Note


class RoomActivity : NoteActivity() {

    val db by lazy { NoteDatabase.create() }

    val dao by lazy { db.noteDao() }

    override val presenter: Presenter<*> = NotePresenter()

    override fun subscribe() {
        val liveData = dao.getAll()
        liveData.observe(this) {
            onItems(it)
        }
    }

    fun onItems(it: List<Note>) {
        val items = it.map { PresenterModel(model = it, layout = R.layout.item_note, uuid = it.id.toString()) }
        adapter?.submitList(items)
    }

    override fun deleteAll() {
        lifecycleScope.launch {
            dao.deleteAll()
        }
    }

    override fun insert() {
        lifecycleScope.launch {
            val text = input.textTrimmed
            if (TextUtils.isEmpty(text))
                return@launch

            dao.insert(Note(text = text))
        }
    }
}