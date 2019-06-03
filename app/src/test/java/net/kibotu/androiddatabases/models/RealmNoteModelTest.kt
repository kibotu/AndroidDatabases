package net.kibotu.androiddatabases.models

import net.kibotu.androiddatabases.base.SerializableTests
import net.kibotu.androiddatabases.realm.model.Note

/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

class RealmNoteModelTest : SerializableTests(Note::class.java) {

    override val expectedJson = "notes.json".stringFromAssets()
}