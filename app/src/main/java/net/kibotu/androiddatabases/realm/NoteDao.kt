package net.kibotu.androiddatabases.realm

import io.realm.Realm
import net.kibotu.androiddatabases.realm.model.Note

open class NoteDao(private val realm: Realm) {

    fun getAll() = realm.where(Note::class.java).findAllAsync().asLiveData()

    fun insert(item: Note) {
        realm.executeTransactionAsync {
            it.insert(item)
        }
    }

    fun deleteAll() {
        realm.executeTransactionAsync {
            it.where(Note::class.java).findAll().deleteAllFromRealm()
        }
    }
}