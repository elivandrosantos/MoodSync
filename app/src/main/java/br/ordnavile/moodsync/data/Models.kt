package br.ordnavile.moodsync.data

import androidx.compose.ui.graphics.Color

data class Mood(
    val id: String,
    val label: String,
    val emoji: String,
    val baseColor: Color
)

enum class Category {
    ACTIVITY, MOVIE, MUSIC
}

data class Recommendation(
    val title: String,
    val category: Category,
    val description: String,
    val moodId: String,
    val imageUrl: String? = null
)
