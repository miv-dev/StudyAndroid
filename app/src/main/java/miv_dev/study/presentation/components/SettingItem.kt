package miv_dev.study.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SettingItem(
    leadingIcon: ImageVector,
    leadingIconColor: Color,
    title: String,
    trailing: @Composable () -> Unit
) {


    Row(Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        LeadingIcon(leadingIcon, leadingIconColor)
        Spacer(Modifier.width(8.dp))
        Text(text = title, style = MaterialTheme.typography.subtitle1)
        Spacer(Modifier.weight(1.0f))
        trailing()

    }
}

@Composable
fun LeadingIcon(icon: ImageVector, color: Color) {
    Box(
        Modifier
            .size(40.dp)
            .background(color.copy(.3F), RoundedCornerShape(20.dp))

    ) {
        Icon(
            imageVector = icon,
            contentDescription = icon.name,
            tint = color,
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}
