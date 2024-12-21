package pt.iade.games.gamedevedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pt.iade.games.gamedevedex.models.Project
import pt.iade.games.gamedevedex.models.ProjectAsset
import pt.iade.games.gamedevedex.models.Student

class ProjectDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            //val project = intent.getSerializableExtra("project") as? Project
            val project = ProjectSingleton.definitelyDidntAskFilippo
            project?.let {
                ProjectDetailScreen(project) {
                    finish() // Handle back button click
                }
            } ?: run {
                Text("No project data found.")
            }
        }
    }
}

/*
@Composable
@Preview()
fun ProjectDetailPreview() {
    ProjectDetailScreen(
        project = Project(
            id = 1,
            title = "Awesome Game Project",
            coverImageUri = URI.create("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Famogus.io%2Fassets%2Fimages%2Famogusio-share.png&f=1&nofb=1&ipt=dd73d28e17187dcdb31ae38463f404fb9292bafaf2def6779067ab4a91129789&ipo=images"),
            description = "This is a very descriptive description with lots of words...",
            semester = 1,
            votes = 123,
            students = listOf(
                Student(
                    id = 123,
                    name = "João Pedro",
                    biography = "Love playing Valorant. Currently thinking of switching courses.",
                    mood = "Lucky",
                    avatar = URI.create("https://media.gettyimages.com/photos/cristiano-ronaldo-of-portugal-poses-during-the-official-fifa-world-picture-id450555852?k=6&m=450555852&s=612x612&w=0&h=aUh0DVio_ubpFtCVvMv3WLR1MVPQji1sN5PDNKvHCT4=")
                )
            ),
            assets = listOf(
                ProjectAsset("file:///android_asset/screenshot1.png", "Gameplay Screenshot 1"),
                ProjectAsset("file:///android_asset/screenshot2.png", "Gameplay Screenshot 2"),
                ProjectAsset("file:///android_asset/screenshot3.png", "Main Character Art"),
                ProjectAsset("file:///android_asset/screenshot4.png", "Enemy Concepts"),
                ProjectAsset("file:///android_asset/screenshot5.png", "Level Design Map")
            )
        )
    ){}
}
*/
@Composable
fun ProjectDetailScreen(project: Project, onBackClick: () -> Unit) {
    Scaffold(
        topBar = { ProjectTopAppBar(onBackClick) }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item { ProjectHeader(project) }
            item { ProjectDescription(project.votes, project.description) }
            items(project.students) { student ->
                StudentSection(student)
            }
            items(project.assets) { asset ->
                AssetSection(asset)
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectTopAppBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text("Gamedevedex")
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun ProjectHeader(project: Project) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = project.title,
            style = MaterialTheme.typography.headlineSmall // Use Material 3 or equivalent
        )
        Spacer(modifier = Modifier.height(8.dp))
        AsyncImage(
            model = project.coverImageUri,
            contentDescription = "Cover Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Composable
fun ProjectDescription(votes: Int, description: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Votes: $votes", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(description)
    }
}

@Composable
fun StudentSection(student: Student) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = student.avatar),
            contentDescription = "Student Icon",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(student.name, fontWeight = FontWeight.Bold)
            Text(student.biography)
        }
    }
}

@Composable
fun AssetSection(asset: ProjectAsset) {
    Column(modifier = Modifier.padding(8.dp)) {
        AsyncImage(
            model = asset.uri,
            contentDescription = asset.description,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Text(
            text = asset.description,
            style = MaterialTheme.typography.bodyMedium // For Material 3
        )
    }
}



