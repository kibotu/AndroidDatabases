package net.kibotu.androiddatabases

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.exozet.android.core.extensions.onClick
import com.exozet.android.core.extensions.resDimension
import com.fievx.polet.Polet
import com.fievx.polet.decorationSelector.PositioningSelector
import com.fievx.polet.spacingDecoration.SimpleSpacingDecoration
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.notes.*
import net.kibotu.android.recyclerviewpresenter.PresenterAdapter
import net.kibotu.android.recyclerviewpresenter.PresenterModel
import net.kibotu.androiddatabases.room.model.Note
import net.kibotu.androiddatabases.ui.NotePresenter


abstract class NoteActivity : AppCompatActivity() {

    var adapter: PresenterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes)

        setupRecyclerView()

        subscribe()

        submit.onClick {
            insert()
        }

        clear.onClick {
            deleteAll()
        }
    }

    abstract fun subscribe()

    abstract fun deleteAll()

    abstract fun insert()

    fun onItems(it: List<Note>) {
        val items = it.map { PresenterModel(model = it, layout = R.layout.item_note, uuid = it.id.toString()) }
        adapter?.submitList(items)
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