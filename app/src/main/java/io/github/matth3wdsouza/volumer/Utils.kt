package io.github.matth3wdsouza.volumer

import android.app.StatusBarManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.media.AudioManager
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat

private const val PINNED_SHORTCUT_ID = "volume_dialog_shortcut"

/**
 * Shows the system volume dialog for whichever stream the hardware volume
 * buttons would currently affect (call, ring, alarm, or media).
 */
fun Context.showVolumeDialog() {
    val audioManager = getSystemService<AudioManager>() ?: return

    audioManager.adjustSuggestedStreamVolume(
        AudioManager.ADJUST_SAME,
        AudioManager.USE_DEFAULT_STREAM_TYPE,
        AudioManager.FLAG_SHOW_UI
    )
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
fun isQuickSettingsTileRequestSupported(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

/**
 * Prompts the user to add Volumer's quick settings tile.
 * No-ops below Android 13, where this API doesn't exist.
 */
fun Context.requestVolumeTile() {
    if (!isQuickSettingsTileRequestSupported()) return

    val statusBarManager = getSystemService<StatusBarManager>() ?: return

    statusBarManager.requestAddTileService(
        ComponentName(this, VolumeTileService::class.java),
        getString(R.string.app_name),
        Icon.createWithResource(this, R.drawable.ic_volumer_tile),
        ContextCompat.getMainExecutor(this)
    ) { /* result: Int — one of StatusBarManager.TILE_ADD_REQUEST_RESULT_* */ }
}

fun Context.requestHomeScreenShortcut() {
    if (!ShortcutManagerCompat.isRequestPinShortcutSupported(this)) return

    val shortcutIntent = Intent(this, InvokeDialogActivity::class.java).apply {
        action = Intent.ACTION_VIEW
    }

    val shortcutInfo = ShortcutInfoCompat.Builder(this, PINNED_SHORTCUT_ID)
        .setShortLabel(getString(R.string.app_name))
        .setIcon(IconCompat.createWithResource(this, R.drawable.ic_volumer_shortcut))
        .setIntent(shortcutIntent)
        .build()

    ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
}