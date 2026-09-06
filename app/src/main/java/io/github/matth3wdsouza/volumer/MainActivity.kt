package io.github.matth3wdsouza.volumer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.matth3wdsouza.volumer.ui.theme.VolumerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VolumerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VolumerScreen(paddingValues = innerPadding)
                }
            }
        }
    }
}

@Composable
fun VolumerScreen(paddingValues: PaddingValues) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 24.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier.size(72.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_volumer_tile),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .padding(18.dp)
                    .fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = stringResource(R.string.home_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(28.dp))

        Button(
            onClick = { context.showVolumeDialog() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.show_volume_dialog))
        }

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedButton(
            onClick = { context.requestVolumeTile() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.add_tile))
        }

        Spacer(modifier = Modifier.size(12.dp))

        OutlinedButton(
            onClick = { context.requestHomeScreenShortcut() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.add_shortcut))
        }
    }
}