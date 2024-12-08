package com.headbook.player.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.headbook.R
import com.headbook.ui.theme.HeadbookTheme
import com.headbook.ui.theme.LightGray

@Composable
fun TextSwitcher(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onValueChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .height(IntrinsicSize.Max)
            .toggleable(
                value = checked,
                onValueChange = onValueChange
            )
            .border(
               border = BorderStroke(1.dp, LightGray),
                shape = CircleShape
            )
            .background(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.background
            )
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(
                    shape = CircleShape,
                    color = if (checked) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary
                )
                .aspectRatio(1f)

            ,
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = R.drawable.ic_audio),
                contentDescription = "player",
                tint = if (checked) Color.Black else Color.White
            )
        }
        VerticalDivider(
            modifier = Modifier.fillMaxHeight(),
            color = MaterialTheme.colorScheme.background,
        )
        Box(
            modifier = Modifier
                .background(
                    shape = CircleShape,
                    color = if (checked) MaterialTheme.colorScheme.primary else  MaterialTheme.colorScheme.background
                )
                .aspectRatio(1f)
            ,
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = R.drawable.ic_text),
                contentDescription = "text",
                tint = if (checked) Color.White else Color.Black
            )
        }
    }
}


@Preview
@Composable
private fun TextSwitcherPreview() {
    HeadbookTheme {
        TextSwitcher(
            Modifier,
            true,
            {}
        )
    }
}