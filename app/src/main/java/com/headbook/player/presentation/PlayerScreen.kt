package com.headbook.player.presentation

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.headbook.R
import com.headbook.core.presentation.formatTime
import com.headbook.ui.theme.HeadbookTheme

@Composable
fun PlayerScreenRoot(
    viewModel: PlayerViewModel,
) {
    val state = viewModel.playerState.collectAsState()

    if (!state.value.showText)
        PlayerScreen(
            bookCoverId = R.drawable.book_cover,
            currentPosition = formatTime(state.value.currentPosition),
            sliderValue = if (state.value.sliderValue.isNaN()) 0f else state.value.sliderValue,
            audioDuration = formatTime(state.value.duration),
            currentAudioNumber = state.value.currentAudioNumber,
            audioCount = state.value.audioCount,
            audioSpeed = state.value.speed,
            showText = state.value.showText,
            onSpeedClicked = {
                viewModel.onSpeedClicked()
            },
            onPreviousClicked = {
                viewModel.onPreviousClicked()
            },
            onNextClicked = {
                viewModel.onNextClicked()
            },
            isPlaying = state.value.isPlaying,
            onSeekBackwardClicked = {
                val newPosition = state.value.currentPosition - 5000
                viewModel.seekTo(newPosition)
            },
            onPauseClicked = {
                viewModel.playPause()
            },
            onSeekForwardClicked = {
                val newPosition = state.value.currentPosition + 15000
                viewModel.seekTo(newPosition)
            },
            onTextSwitcherClicked = {
                viewModel.onTextSwitcherClicked(it)
            },
            onSliderValueChanged = {
                viewModel.sliderValueChanged(it)
            }
        )
    else
        TextScreen(
            text = state.value.chapterText,
            showText = true,
            onTextSwitcherClicked = {
                viewModel.onTextSwitcherClicked(it)
            }
        )
}


@Composable
fun PlayerScreen(
    bookCoverId: Int,
    currentPosition: String,
    sliderValue: Float,
    audioDuration: String,
    currentAudioNumber: Int,
    audioCount: Int,
    audioSpeed: Float,
    isPlaying: Boolean,
    showText: Boolean,
    onSpeedClicked: () -> Unit,
    onPreviousClicked: () -> Unit,
    onSeekBackwardClicked: () -> Unit,
    onPauseClicked: () -> Unit,
    onSeekForwardClicked: () -> Unit,
    onNextClicked: () -> Unit,
    onTextSwitcherClicked: (Boolean) -> Unit,
    onSliderValueChanged: (Float) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        Image(
            modifier = Modifier.size(360.dp),
            painter = painterResource(id = bookCoverId),
            contentDescription = "Book image"
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(text = "KEY POINTS $currentAudioNumber OF $audioCount")
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "This is a description of the book")
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Text(text = currentPosition)
            Spacer(modifier = Modifier.size(8.dp))
            Slider(
                value = sliderValue,
                onValueChange = { value ->
                    onSliderValueChanged(value)
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = audioDuration)

        }
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = onSpeedClicked
        ) {
            Text("Speed x$audioSpeed")
        }
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Button(
                onClick = onPreviousClicked
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_previous),
                    contentDescription = "previous"
                )
            }

            Button(
                onClick = onSeekBackwardClicked
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_seek_backward),
                    contentDescription = "seek backward"
                )
            }

            Button(
                onClick = onPauseClicked
            ) {
                if (isPlaying)
                    Image(
                        painter = painterResource(id = R.drawable.ic_pause),
                        contentDescription = "pause"
                    )
                else
                    Image(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "pause"
                    )
            }

            Button(
                onClick = onSeekForwardClicked
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_seek_forward),
                    contentDescription = "seek forward"
                )
            }

            Button(
                onClick = onNextClicked
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = "next"
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        Switch(
            checked = showText,
            onCheckedChange = onTextSwitcherClicked,
        )
    }
}


@Composable
fun TextScreen(
    text: String,
    showText: Boolean,
    onTextSwitcherClicked: (Boolean) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
        Switch(
            checked = showText,
            onCheckedChange = onTextSwitcherClicked,
            modifier = Modifier
                .padding(32.dp)
                .align(alignment = Alignment.BottomCenter)
        )
    }

}


fun getRawResourceUri(context: Context, resourceId: Int): Uri {
    return Uri.parse("android.resource://${context.packageName}/$resourceId")
}

//
//@Preview
//@Composable
//private fun PlayerScreenPreview() {
//    HeadbookTheme {
//        PlayerScreen()
//    }
//}

@Preview
@Composable
private fun TextScreenPreview() {
    HeadbookTheme {
        TextScreen(
            text = mockedText,
            showText = true,
            onTextSwitcherClicked = {}
        )
    }
}

val mockedText =
        "\n" +
        "Lorem ipsum dolor sit amet. Qui accusamus facilis et facere repudiandae et adipisci mollitia qui provident quia et fuga repellendus. Et possimus sequi 33 incidunt sint quo facere ipsam ab ducimus molestias qui quod nemo cum excepturi amet. Quo error mollitia et fugiat unde et veritatis sapiente est soluta architecto et reiciendis quaerat eos officia neque. Rem expedita nemo ad voluptate veritatis et consequatur vitae ab rerum officia est deserunt corporis qui ratione suscipit?\n" +
        "\n" +
        "Et omnis nesciunt At tenetur quisquam et sunt tenetur. Ut dolores dignissimos est quidem galisum ut sequi maxime. Est quasi nostrum eum sint culpa non officia similique aut voluptate reiciendis aut accusantium velit est quam aspernatur. Est aliquam sint et odit magni et deserunt dolorum ex recusandae molestias!\n" +
        "\n" +
        "Ab dolorum esse est amet galisum sed voluptas laudantium qui quia magnam et aliquam aliquam sed doloremque porro sed sint accusantium! Est nulla quae aut repudiandae unde hic amet dolorem ut corrupti modi est quia recusandae est unde quidem? Est labore quibusdam quo mollitia illo ut Quis eius non beatae quaerat ex maxime quod et harum natus ut voluptas numquam. Aut quaerat dolorem vel delectus quos quo doloribus provident cum assumenda neque sed culpa aperiam est aspernatur quod.\n"