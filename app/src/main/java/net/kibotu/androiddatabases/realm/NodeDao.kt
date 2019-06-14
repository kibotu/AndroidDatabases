package net.kibotu.androiddatabases.realm

import io.realm.Realm
import net.kibotu.androiddatabases.realm.model.Node


open class NodeDao(private val realm: Realm) {

    fun getAll() = realm.where(Node::class.java).findAllAsync().asLiveData()

    fun insert(item: Node) {
        realm.executeTransactionAsync {
            it.insert(item)
        }
    }

    fun deleteAll() {
        realm.executeTransactionAsync {
            it.where(Node::class.java).findAll().deleteAllFromRealm()
        }
    }
}