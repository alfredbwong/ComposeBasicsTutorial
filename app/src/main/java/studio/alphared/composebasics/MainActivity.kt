package studio.alphared.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import studio.alphared.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android $it" }) {
    var counterState by remember {
        mutableStateOf(0)
    }
    Column(Modifier.fillMaxHeight()) {
        NamesList(names, modifier = Modifier.weight(1f))
        Counter(counterState) { newCount -> counterState = newCount }
        if (counterState > 5) {
            Text("I love to count")
        }
    }

}

@Composable
fun NamesList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) {
            Greeting(name = it, modifier)
            Divider()
        }
    }

}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    Button({ updateCount(count + 1) }) {
        Text("I've been clicked $count times")
    }


}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBasicsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    val targetColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colors.primary else Color.Transparent,
        animationSpec = tween(durationMillis = 4000)
    )
    Surface(color = targetColor) {
        Text(
            text = "Hello $name!",
            modifier = modifier
                .clickable { isSelected = !isSelected }
                .padding(16.dp)
        )

    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}