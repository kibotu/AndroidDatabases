package net.kibotu.androiddatabases.room

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.exozet.android.core.extensions.onClick
import com.exozet.android.core.extensions.resDimension
import com.exozet.android.core.extensions.textTrimmed
import com.fievx.polet.Polet
import com.fievx.polet.decorationSelector.PositioningSelector
import com.fievx.polet.spacingDecoration.SimpleSpacingDecoration
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_room_main.*
import kotlinx.coroutines.launch
import net.kibotu.android.recyclerviewpresenter.PresenterAdapter
import net.kibotu.android.recyclerviewpresenter.PresenterModel
import net.kibotu.androiddatabases.R
import net.kibotu.androiddatabases.decorateWithAlphaScaleAdapter
import net.kibotu.androiddatabases.room.model.Note
import net.kibotu.androiddatabases.ui.NotePresenter


class RoomActivity : AppCompatActivity() {

    val db by lazy { NoteDatabase.create() }

    val dao by lazy { db.noteDao() }

    var adapter: PresenterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_main)

        setupRecyclerView()

        dao.getAll().observe(this) {
            val items = it.map { PresenterModel(model = it, layout = R.layout.item_note, uuid = it.id.toString()) }
            adapter?.submitList(items)
        }

        submit.onClick {
            insert()
        }

        clear.onClick {
            deleteAll()
        }
    }

    private fun deleteAll() {
        lifecycleScope.launch {
            dao.deleteAll()
        }
    }

    private fun insert() {
        lifecycleScope.launch {
            val text = input.textTrimmed
            if (text.isNotEmpty()) {
                dao.insert(Note(text = text))
                input.setText("")
            }
        }
    }

    private fun setupRecyclerView() {
        list.itemAnimator = SlideInUpAnimator().apply {
            setInterpolator(OvershootInterpolator())
        }
        list.addItemDecoration(Polet().apply {
            sizingDecoration = SimpleSpacingDecoration(r = (R.dimen.activity_horizontal_margin.resDimension * 0.5f).toInt())
            selectiveDecoration.add(PositioningSelector().apply {
                includeLast = false
            })
        })

        adapter = PresenterAdapter()
        adapter?.decorateWithAlphaScaleAdapter()
        adapter?.registerPresenter(NotePresenter())
        list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()

        adapter = null
    }
}