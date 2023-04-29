package io.github.matth3wdsouza.volumer

import android.annotation.SuppressLint
import android.app.StatusBarManager
import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Icon
import android.media.AudioManager
import android.os.Build

fun invokeVolumeDialog(context: Context) {
    val audioManager: AudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    audioManager.adjustStreamVolume(
        AudioManager.STREAM_MUSIC,
        AudioManager.ADJUST_SAME,
        AudioManager.FLAG_SHOW_UI
    )
}

@SuppressLint("WrongConstant")
fun addTileService(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val statusBarManager =
            context.getSystemService(Context.STATUS_BAR_SERVICE) as StatusBarManager

        statusBarManager.requestAddTileService(
            ComponentName(
                context,
                TileService::class.java
            ),
            context.getString(R.string.app_name),
            Icon.createWithResource(context, R.drawable.ic_volumer_tile),
            {},
            {}
        )
    }
}