package net.kibotu.androiddatabases.realm

import com.google.common.truth.Truth.assertThat
import net.kibotu.androiddatabases.fromJson
import net.kibotu.androiddatabases.realm.model.Node
import org.junit.Test
import org.mockito.Mockito

class NodeRealmTests : BaseRealmTests() {

    @Test
    fun shouldBeAbleToCreateARealmObject() {

        val tree = "tree.json".stringFromAssets().fromJson<Node>()
        assertThat(tree).isNotNull()

        Mockito.`when`(mockRealm.createObject(Node::class.java)).thenReturn(tree)
        val output = mockRealm.createObject(Node::class.java)
        assertThat(output).isEqualTo(tree)
    }
}