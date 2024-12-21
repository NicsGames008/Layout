package pt.iade.games.gamedevedex.models

import java.io.Serializable
import java.net.URI


data class Project(
    var id: Int,
    var title: String,
    var coverImageUri: Int,
    var description: String,
    var semester: Int,
    var votes: Int,
    var students: List<Student>,
    var assets: List<ProjectAsset>
) : Serializable

