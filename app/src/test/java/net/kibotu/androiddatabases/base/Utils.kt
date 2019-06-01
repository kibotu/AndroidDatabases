package net.kibotu.androiddatabases.base

import org.mockito.Mockito


inline fun <reified T> lambdaMock(): T = Mockito.mock(T::class.java)