package net.kibotu.androiddatabases.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.github.florent37.application.provider.application
import net.kibotu.androiddatabases.room.model.Note
import net.kibotu.logger.Logger.logv


@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = true
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDoa

    companion object {

        fun create() = Room.databaseBuilder(application!!, NoteDatabase::class.java, "note-db")
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    logv("db created")
                }
            }).build()
    }
}