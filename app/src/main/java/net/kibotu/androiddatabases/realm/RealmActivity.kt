package net.kibotu.androiddatabases.realm

import android.text.TextUtils
import androidx.lifecycle.observe
import com.exozet.android.core.extensions.stringFromAssets
import com.exozet.android.core.extensions.textTrimmed
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.notes.*
import net.kibotu.android.recyclerviewpresenter.Presenter
import net.kibotu.android.recyclerviewpresenter.PresenterModel
import net.kibotu.androiddatabases.NoteActivity
import net.kibotu.androiddatabases.R
import net.kibotu.androiddatabases.fromJson
import net.kibotu.androiddatabases.realm.model.Node
import net.kibotu.androiddatabases.realm.model.Note
import net.kibotu.logger.Logger.logv


class RealmActivity : NoteActivity() {

    val db by lazy {
        Realm.init(applicationContext)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().name("note.realm").build())
        Realm.getDefaultInstance()
    }

    val noteDao by lazy { NoteDao(db) }

    val noDeDao by lazy { NodeDao(db) }

    val tree by lazy { "tree.json".stringFromAssets().fromJson<Node>() }

    override val presenter: Presenter<*> = NotePresenter()

    override fun subscribe() {

        noDeDao.getAll().observe(this) {

            it.forEach {
                logv("nodes: $it")
            }
        }

        val liveData = noteDao.getAll()
        liveData.observe(this) {
            onItems(it)
        }
    }

    fun onItems(it: List<Note>) {
        val items = it.map { PresenterModel(model = it, layout = R.layout.item_note, uuid = it.id.toString()) }
        adapter?.submitList(items)
    }

    override fun deleteAll() {
        noteDao.deleteAll()
        noteDao.deleteAll()
    }

    override fun insert() {

        noDeDao.insert(tree)

        val text = input.textTrimmed
        if (TextUtils.isEmpty(text))
            return

        noteDao.insert(Note(text = text))
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}