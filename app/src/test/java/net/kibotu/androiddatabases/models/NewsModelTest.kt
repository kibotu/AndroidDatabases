package net.kibotu.androiddatabases.models

import net.kibotu.androiddatabases.base.SerializableTests
import net.kibotu.androiddatabases.room.model.Note

/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

class NewsModelTest : SerializableTests(Note::class.java) {

    override val expectedJson = "notes.json".stringFromAssets()
}