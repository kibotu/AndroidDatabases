package net.kibotu.androiddatabases.base

import org.mockito.Mockito

/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

inline fun <reified T> lambdaMock(): T = Mockito.mock(T::class.java)