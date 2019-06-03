package net.kibotu.androiddatabases.objectbox.model

import net.kibotu.androiddatabases.room.model.Node


/**
 * Tree Structure
 */
data class Node(var id: Int? = null, var children: List<Node>? = null)