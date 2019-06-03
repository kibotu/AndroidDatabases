package net.kibotu.androiddatabases.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.kibotu.androiddatabases.base.BaseTest
import net.kibotu.androiddatabases.base.CoroutinesTestRule
import net.kibotu.androiddatabases.base.lambdaMock
import net.kibotu.androiddatabases.room.model.Note
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.io.IOException
import java.util.*

/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

@UseExperimental(ExperimentalCoroutinesApi::class)
class RoomTests : BaseTest() {

    private lateinit var db: NoteDatabase

    private lateinit var noteDoa: NoteDoa

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

    @Test
    fun getAll() {

        val livedata = MutableLiveData<Note>()

        fun observeChanges(lifecycle: Lifecycle, observer: (Note) -> Unit) {
            livedata.observe({ lifecycle }) {
                it?.let(observer)
            }
        }

        val note = Note(UUID.randomUUID().toString().hashCode())
        assertThat(note).isNotNull()

        val observer = lambdaMock<(Note) -> Unit>()
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        observeChanges(lifecycle, observer)

        livedata.postValue(note)

        verify(observer).invoke(note)

        assertThat(livedata.value).isEqualTo(note)
    }

    @Test
    fun getAllDb() {

        val observer = lambdaMock<(List<Note>) -> Unit>()
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        coroutinesTestRule.testDispatcher.runBlockingTest {

            val note = Note(UUID.randomUUID().toString().hashCode())
            assertThat(note).isNotNull()
            noteDoa.insert(note)

            val all = noteDoa.getAll()

            fun observeChanges(lifecycle: Lifecycle, observer: (List<Note>) -> Unit) {
                all.observe({ lifecycle }) {
                    it?.let(observer)
                }
            }

            observeChanges(lifecycle, observer)

            verify(observer).invoke(listOf(note))

            assertThat(all.value).isNotEmpty()
        }
    }
}