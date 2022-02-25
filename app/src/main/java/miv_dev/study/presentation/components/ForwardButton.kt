package miv_dev.study.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@Composable
fun ForwardButton(onTap: () -> Unit) {
    Box(
        Modifier
            .size(40.dp)
            .background(MaterialTheme.colors.onBackground.copy(.08f), RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .clickable { onTap() }

    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowForward,
            contentDescription = Icons.Rounded.ArrowForward.name,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}