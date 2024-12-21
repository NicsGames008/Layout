package pt.iade.games.gamedevedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.iade.games.gamedevedex.controllers.StudentController
import pt.iade.games.gamedevedex.models.Project
import pt.iade.games.gamedevedex.models.ProjectAsset
import pt.iade.games.gamedevedex.models.Student
import pt.iade.games.gamedevedex.ui.components.ProjectCard
import pt.iade.games.gamedevedex.ui.theme.GamedevedexTheme
import java.net.URI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamedevedexTheme {
                MainView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    var student by remember { mutableStateOf<Student?>(null) }
    val studentController = StudentController()
    studentController.GetById(
        id = 123,
        onSuccess = { studentReq ->
            student = studentReq
        }
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Gamedevedex")
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (student != null) {
                Text(student!!.name)
            } else {
                // TODO: Show progress circle thingy.
            }

            ProjectCard(
                modifier = Modifier.padding(vertical = 20.dp),
                project = ProjectExample()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainViewPreview() {
    GamedevedexTheme {
        MainView()
    }
}

fun ProjectExample(): Project {
    return Project(
        id = 1,
        title = "Detective Ribbit",
        coverImageUri = R.drawable.logo1,
        description = "This is a very descriptive description with lots of words...",
        semester = 1,
        votes = 123,
        students = listOf(
            Student(
                id = 123,
                name = "Artur Nicolau",
                biography = "Gay",
                mood = "Lucky",
                avatar = R.drawable.ic_student_icon
            ),
            Student(
                id = 124,
                name = " Diogo Carvalho",
                biography = "Gambling",
                mood = "Lucky",
                avatar = R.drawable.screenshot_10
            ),
            Student(
                id = 125,
                name = "Anna-Maria Tsocheva",
                biography = "Macedonian",
                mood = "Lucky",
                avatar = R.drawable.screenshot_9
            )
        ),
        assets = listOf(
            ProjectAsset(R.drawable.megagamer, "Gameplay Screenshot 1"),
            ProjectAsset(R.drawable.placeholder_cover_image, "Gameplay Screenshot 2 (srr got lazy)"),
            ProjectAsset(R.drawable.frorg, "Main Character Art"),
            ProjectAsset(R.drawable.bzzzbzzz, "Enemy Concepts"),
            ProjectAsset(R.drawable.screenshot_31, "Level Design Map")
        )
    )
}
