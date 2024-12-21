package pt.iade.games.gamedevedex.ui.components

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import pt.iade.games.gamedevedex.ProjectDetailActivity
import pt.iade.games.gamedevedex.ProjectSingleton
import pt.iade.games.gamedevedex.R
import pt.iade.games.gamedevedex.models.Project

@Composable
fun ProjectCard(
    project: Project,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    // var votes = project.votes
    var votes by remember { mutableIntStateOf(project.votes) }

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {

            val intent = Intent(context, ProjectDetailActivity::class.java)
            ProjectSingleton.definitelyDidntAskFilippo = project
            context.startActivity(intent)
        }
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = project.coverImageUri,
                placeholder = painterResource(R.drawable.placeholder_cover_image),
                contentDescription = "Cover image of the game",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "$votes",
                    fontSize = 17.sp,
                    color = Color(255, 255, 255)
                )
            }
        }

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = project.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.weight(weight = 1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Button(
                onClick = {
                    votes++
                    project.votes++
                },
                modifier = Modifier.padding(start = 30.dp)
            ) {
                Text("Vote")
            }
        }
    }
}
/*
@Composable
@Preview()
fun ProjectCardPreview() {
    Column {
        ProjectCard(
            modifier = Modifier.padding(vertical = 20.dp),
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
                        avatar = R.drawable.ic_student_icon
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
        )
    }
}*/