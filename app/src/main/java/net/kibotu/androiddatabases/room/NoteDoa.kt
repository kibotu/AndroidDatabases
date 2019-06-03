package net.kibotu.androiddatabases.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import net.kibotu.androiddatabases.room.model.Note


@Dao
interface NoteDoa {

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getAllPaged(): DataSource.Factory<Int, Note>

    @Query("SELECT * FROM note WHERE :id")
    fun getById(id: Int): LiveData<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Note)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(items: List<Note>)

    @Delete
    suspend fun delete(item: Note)

    @Query("DELETE FROM note")
    suspend fun deleteAll()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(items: List<Note>)
}