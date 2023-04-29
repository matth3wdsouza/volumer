package io.github.matth3wdsouza.volumer

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.os.IBinder
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
class TileService : TileService() {
    private val tag = TileService::class.java.simpleName

    override fun onTileAdded() {
        super.onTileAdded()
        Log.d(tag, "onTileAdded()")
    }

    override fun onStartListening() {
        super.onStartListening()
        Log.d(tag, "onStartListening()")

        if (qsTile != null) {
            qsTile.contentDescription = qsTile.label
            qsTile.state = Tile.STATE_ACTIVE
            qsTile.icon = Icon.createWithResource(this, R.drawable.ic_volumer_tile)
            qsTile.updateTile()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(tag, "onBind()")
        return super.onBind(intent)
    }

    override fun onClick() {
        super.onClick()
        Log.d(tag, "onClick()")

        invokeVolumeDialog(this)
    }

    override fun onStopListening() {
        super.onStopListening()
        Log.d(tag, "onStopListening()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(tag, "onDestroy()")
    }

    override fun onTileRemoved() {
        super.onTileRemoved()
        Log.d(tag, "onTileRemoved()")
    }
}