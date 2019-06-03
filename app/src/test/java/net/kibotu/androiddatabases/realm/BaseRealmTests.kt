package net.kibotu.androiddatabases.realm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.realm.Realm
import io.realm.log.RealmLog
import net.kibotu.androiddatabases.base.BaseTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.RobolectricTestRunner


/**
 * https://github.com/realm/realm-java/blob/master/examples/unitTestExample/src/test/java/io/realm/examples/unittesting/ExampleRealmTest.java
 */

@RunWith(RobolectricTestRunner::class)
@PowerMockIgnore(value = ["org.powermock.*", "org.mockito.*", "org.robolectric.*", "android.*", "androidx.*"])
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest(value = [Realm::class, RealmLog::class])
open class BaseRealmTests : BaseTest() {

    // Robolectric, Using Power Mock https://github.com/robolectric/robolectric/wiki/Using-PowerMock

    @JvmField
    @Rule
    var powerMockRule = PowerMockRule()

    lateinit var mockRealm: Realm

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockStatic(RealmLog::class.java)
        mockStatic(Realm::class.java)

        val mockRealm = PowerMockito.mock(Realm::class.java)

        `when`(Realm.getDefaultInstance()).thenReturn(mockRealm)

        this.mockRealm = mockRealm
    }

    @Test
    fun shouldBeAbleToGetDefaultInstance() {
        assertThat(Realm.getDefaultInstance()).isEqualTo(mockRealm)
    }

    @Test
    fun shouldBeAbleToMockRealmMethods() {
        PowerMockito.`when`(mockRealm.isAutoRefresh).thenReturn(true)
        assertThat(mockRealm.isAutoRefresh).isTrue()

        PowerMockito.`when`(mockRealm.isAutoRefresh).thenReturn(false)
        assertThat(mockRealm.isAutoRefresh).isFalse()
    }
}