package net.kibotu.androiddatabases.models

import net.kibotu.androiddatabases.base.SerializableTests
import net.kibotu.androiddatabases.objectbox.model.Note


/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

class ObjectBoxNoteModelTest : SerializableTests(Note::class.java) {

    override val expectedJson = "notes.json".stringFromAssets()
}