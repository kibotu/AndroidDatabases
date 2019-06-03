package net.kibotu.androiddatabases.realm.model

import androidx.room.PrimaryKey
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Tree Structure
 */
open class Node(
    @PrimaryKey
    var id: Int? = null,
    var text : String? = null,
    var children: RealmList<Node>? = null
) : RealmObject()