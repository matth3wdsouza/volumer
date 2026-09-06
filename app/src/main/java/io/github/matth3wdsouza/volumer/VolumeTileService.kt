package io.github.matth3wdsouza.volumer

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.IBinder
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class VolumeTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()

        qsTile?.apply {
            contentDescription = label
            state = Tile.STATE_ACTIVE
            icon = Icon.createWithResource(this@VolumeTileService, R.drawable.ic_volumer_tile)
            updateTile()
        }
    }

    override fun onBind(intent: Intent?): IBinder? = super.onBind(intent)

    override fun onClick() {
        super.onClick()
        showVolumeDialog()
    }
}