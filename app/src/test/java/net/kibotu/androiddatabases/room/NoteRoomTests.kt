package net.kibotu.androiddatabases.room

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.kibotu.androiddatabases.base.lambdaMock
import net.kibotu.androiddatabases.room.model.Note
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*

/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

@UseExperimental(ExperimentalCoroutinesApi::class)
class NoteRoomTests : BaseRoomTest() {

    @Test
    fun getAll() {

        val liveData = MutableLiveData<Note>()

        fun observeChanges(lifecycle: Lifecycle, observer: (Note) -> Unit) {
            liveData.observe({ lifecycle }) {
                it?.let(observer)
            }
        }

        val note = Note(UUID.randomUUID().toString().hashCode(), "hallo world")
        assertThat(note).isNotNull()

        val observer = lambdaMock<(Note) -> Unit>()
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        observeChanges(lifecycle, observer)

        liveData.postValue(note)

        verify(observer).invoke(note)

        assertThat(liveData.value).isEqualTo(note)
    }

    @Test
    fun getAllDb() {

        val observer = lambdaMock<(List<Note>) -> Unit>()
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        coroutinesTestRule.testDispatcher.runBlockingTest {

            val note = Note(UUID.randomUUID().toString().hashCode(), "hallo world")
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