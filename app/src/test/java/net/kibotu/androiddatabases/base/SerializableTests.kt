package net.kibotu.androiddatabases.base

import com.exozet.android.core.extensions.toJsonPrettyPrinting
import com.exozet.android.core.provider.GsonProvider.gson
import net.kibotu.androiddatabases.base.BaseTest
import org.junit.Assert
import org.junit.Test
import org.skyscreamer.jsonassert.JSONAssert

/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

abstract class SerializableTests(var klass: Class<*>) : BaseTest() {

    open val expectedJson: String = ""

    @Test
    open fun serialize() {
        val actual = gson.fromJson(expectedJson.trimMargin(), klass)
        Assert.assertNotNull(actual)
    }

    @Test
    open fun deserialize() {
        val actual = gson.fromJson(expectedJson.trimMargin(), klass)
        Assert.assertNotNull(actual)

        val actualJson = actual.toJsonPrettyPrinting()

        JSONAssert.assertEquals(expectedJson.trimMargin(), actualJson, true)
    }
}