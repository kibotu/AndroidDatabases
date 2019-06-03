package net.kibotu.androiddatabases.objectbox

import com.google.common.truth.Truth.assertThat
import net.kibotu.androiddatabases.objectbox.model.Note
import org.junit.Test

/**
 * Created by <a href="https://about.me/janrabe">Jan Rabe</a>.
 */

class NoteBoxTests : BaseObjectBoxTest() {

    @Test
    fun insert() {

        val note = Note(text = "hallo world")
        assertThat(note).isNotNull()

        val box = store?.boxFor(Note::class.java)
        box?.attach(note)
        box?.put(note)
        assertThat(note.id).isNotNull()
    }
}