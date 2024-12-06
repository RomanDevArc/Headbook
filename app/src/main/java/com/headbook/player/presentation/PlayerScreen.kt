package com.headbook.player.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.headbook.R
import com.headbook.ui.theme.HeadbookTheme

@Composable
fun PlayerScreenRoot(

) {
    PlayerScreen()
}


@Composable
fun PlayerScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.size(24.dp))
        Image(
            modifier = Modifier.size(360.dp),
            painter = painterResource(id = R.drawable.book_pic_test),
            contentDescription = "Book image"
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = "KEY POINTS 2 OF 10")
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "This is a description of the book")
        Spacer(modifier = Modifier.size(8.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

        ){
            Text(text = "00:23")
            LinearProgressIndicator()
            Text(text = "00:23")

        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {}
        ) {
            Text("Speed x1")
        }
        Spacer(modifier = Modifier.size(24.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ){
            Button(
                onClick = {}
            ) {
                Image(painter = painterResource(id = R.drawable.ic_previous), contentDescription = "previous")
            }

            Button(
                onClick = {}
            ) {
                Image(painter = painterResource(id = R.drawable.ic_seek_backward), contentDescription = "seek backward")
            }

            Button(
                onClick = {}
            ) {
                Image(painter = painterResource(id = R.drawable.ic_pause), contentDescription = "pause")
            }

            Button(
                onClick = {}
            ) {
                Image(painter = painterResource(id = R.drawable.ic_seek_forward), contentDescription = "seek forward")
            }

            Button(
                onClick = {}
            ) {
                Image(painter = painterResource(id = R.drawable.ic_next), contentDescription = "next")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Switch(
            checked = true,
            onCheckedChange = {},
        )
    }
}










@Preview
@Composable
private fun PlayerScreenPreview() {
    HeadbookTheme {
        PlayerScreen()
    }
}