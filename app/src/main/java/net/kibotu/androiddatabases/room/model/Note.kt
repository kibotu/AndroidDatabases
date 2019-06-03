package net.kibotu.androiddatabases.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Value Class
 */
@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var text: String? = null
)