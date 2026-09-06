package io.github.matth3wdsouza.volumer

import android.app.Activity
import android.os.Bundle

/**
 * Target for the pinned home screen shortcut - reproduces the original
 * app's behavior of showing the dialog and immediately closing, with no
 * screen of its own.
 */
class InvokeDialogActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showVolumeDialog()
        finishAndRemoveTask()
    }
}