package net.kibotu.androiddatabases

import androidx.recyclerview.widget.RecyclerView
import com.exozet.android.core.extensions.resInt
import com.exozet.android.core.provider.GsonProvider
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

inline fun <reified VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.decorateWithAlphaScaleAdapter(): ScaleInAnimationAdapter {
    val alphaAdapter = AlphaInAnimationAdapter(this, 0f).apply {
        setFirstOnly(false)
        setDuration(android.R.integer.config_mediumAnimTime.resInt)
    }
    val scaleInAdapter = ScaleInAnimationAdapter(alphaAdapter, 1.5f).apply {
        setFirstOnly(false)
        setDuration(android.R.integer.config_shortAnimTime.resInt)
    }
    return scaleInAdapter
}

inline fun <reified T> String.fromJson(): T = GsonProvider.gson.fromJson<T>(this, T::class.java)