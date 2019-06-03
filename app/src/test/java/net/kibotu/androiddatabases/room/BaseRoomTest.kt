package net.kibotu.androiddatabases.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.kibotu.androiddatabases.base.BaseTest
import net.kibotu.androiddatabases.base.CoroutinesTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import java.io.IOException

@UseExperimental(ExperimentalCoroutinesApi::class)
abstract class BaseRoomTest : BaseTest() {

    protected lateinit var db: NoteDatabase

    protected lateinit var noteDoa: NoteDoa

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        noteDoa = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}
