package net.kibotu.androiddatabases.realm.model

import androidx.room.PrimaryKey
import io.realm.RealmObject

open class Note(
    @PrimaryKey
    var id: Int? = null,
    var text: String? = null
) : RealmObject()