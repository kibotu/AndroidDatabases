package net.kibotu.androiddatabases.objectbox.model

import io.objectbox.BoxStore
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id


/**
 * Value Class
 */
@Entity
data class Note(
    @Id
    var id: Long = 0,
    var text: String? = null
) {

    // normally transformer would add field, but need to manually for local unit tests
    @Transient
    public var __boxStore: BoxStore? = null
}