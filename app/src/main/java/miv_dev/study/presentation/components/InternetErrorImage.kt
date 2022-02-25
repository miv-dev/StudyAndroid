package miv_dev.study.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BugReport
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InternetErrorImage() {
    Box(Modifier.fillMaxSize()){
        Box(
            Modifier
                .requiredSize(128.dp)
                .align(Alignment.Center)
                .background(MaterialTheme.colors.error, RoundedCornerShape(64.dp))
        ) {
            Icon(
                imageVector = Icons.Rounded.BugReport,
                contentDescription = "Network lost",
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )

                    .fillMaxSize(0.8f),
                tint =  MaterialTheme.colors.background
            )
        }
    }
}