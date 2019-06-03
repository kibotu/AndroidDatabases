package net.kibotu.androiddatabases.objectbox

import android.text.TextUtils
import androidx.lifecycle.observe
import com.exozet.android.core.extensions.textTrimmed
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.android.synthetic.main.notes.*
import net.kibotu.android.recyclerviewpresenter.Presenter
import net.kibotu.android.recyclerviewpresenter.PresenterModel
import net.kibotu.androiddatabases.NoteActivity
import net.kibotu.androiddatabases.R
import net.kibotu.androiddatabases.objectbox.model.MyObjectBox
import net.kibotu.androiddatabases.objectbox.model.Note
import net.kibotu.androiddatabases.objectbox.model.Note_


class ObjectBoxActivity : NoteActivity() {

    override val presenter: Presenter<*> = NotePresenter()

    val db by lazy { MyObjectBox.builder().androidContext(applicationContext).build() }

    val box by lazy { db.boxFor(Note::class.java) }

    override fun subscribe() {
        val liveData: ObjectBoxLiveData<Note> = ObjectBoxLiveData(box.query().order(Note_.text).build())
        liveData.observe(this) {
            onItems(it)
        }
    }

    fun onItems(it: List<Note>) {
        val items = it.map { PresenterModel(model = it, layout = R.layout.item_note, uuid = it.id.toString()) }
        adapter?.submitList(items)
    }

    override fun deleteAll() {
        box.removeAll()
    }

    override fun insert() {
        val text = input.textTrimmed
        if (TextUtils.isEmpty(text))
            return

        val note = Note(text = text)
        val box = db?.boxFor(Note::class.java)
        box?.attach(note)
        box?.put(note)

        input.setText("")
    }

    override fun onDestroy() {
        super.onDestroy()
        db?.close()
    }
}