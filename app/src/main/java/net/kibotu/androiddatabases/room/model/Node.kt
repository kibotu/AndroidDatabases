package net.kibotu.androiddatabases.room.model

import net.kibotu.androiddatabases.objectbox.model.Node


/**
 * Tree Structure
 */
data class Node(var id: Int? = null, var children: List<Node>? = null)