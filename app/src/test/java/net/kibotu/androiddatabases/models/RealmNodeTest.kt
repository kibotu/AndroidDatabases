package net.kibotu.androiddatabases.models


import net.kibotu.androiddatabases.base.SerializableTests
import net.kibotu.androiddatabases.realm.model.Node

/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

class RealmNodeTest : SerializableTests(Node::class.java) {

    override val expectedJson = "tree.json".stringFromAssets()
}