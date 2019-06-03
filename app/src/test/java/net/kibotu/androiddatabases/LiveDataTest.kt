package net.kibotu.androiddatabases

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import net.kibotu.androiddatabases.base.BaseTest
import net.kibotu.androiddatabases.base.lambdaMock
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class Presenter {

    val titleLiveData = MutableLiveData<String>()

    fun observeTitleChanges(lifecycle: Lifecycle, observer: (String) -> Unit) {
        titleLiveData.observe({ lifecycle }) { title ->
            title?.let(observer)
        }
    }

    fun showTitle(title: String) {
        titleLiveData.postValue(title)
    }
}

class LiveDataTest : BaseTest() {

    @Test
    fun showTitleTest() {
        val presenter = Presenter()

        val observer = lambdaMock<(String) -> Unit>()
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(ON_RESUME)

        presenter.observeTitleChanges(lifecycle, observer)

        presenter.showTitle("title")

        verify(observer).invoke("title")
    }
}