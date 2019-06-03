package net.kibotu.androiddatabases.ui

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import net.kibotu.android.recyclerviewpresenter.Adapter
import net.kibotu.android.recyclerviewpresenter.Presenter
import net.kibotu.android.recyclerviewpresenter.PresenterModel
import net.kibotu.androiddatabases.R
import net.kibotu.androiddatabases.room.model.Note

class NotePresenter : Presenter<Note>() {

    override val layout = R.layout.item_note

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder, item: PresenterModel<Note>, position: Int, payloads: MutableList<Any>?, adapter: Adapter) = with(viewHolder.itemView) {
        label.text = item.model.text
    }
}