package net.kibotu.androiddatabases.realm

import com.google.common.truth.Truth.assertThat
import net.kibotu.androiddatabases.realm.model.Note
import org.junit.Test
import org.mockito.Mockito.`when`


class NoteRealmTests : BaseRealmTests() {

    @Test
    fun shouldBeAbleToCreateARealmObject() {
        val note = Note()
        `when`(mockRealm.createObject(Note::class.java)).thenReturn(note)
        val output = mockRealm.createObject(Note::class.java)
        assertThat(output).isEqualTo(note)
    }
}