package br.ordnavile.moodsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.ordnavile.moodsync.data.DataSource
import br.ordnavile.moodsync.data.Mood
import br.ordnavile.moodsync.data.Recommendation
import br.ordnavile.moodsync.ui.theme.MoodSyncTheme
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VibeCheckApp()
        }
    }
}

@Composable
fun VibeCheckApp() {
    var selectedMood by remember { mutableStateOf(DataSource.moods[0]) }
    val recommendations = DataSource.recommendations.filter { it.moodId == selectedMood.id }
    var currentRecommendation by remember(selectedMood) {
        mutableStateOf(recommendations.random())
    }

    MoodSyncTheme(mood = selectedMood) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent
        ) { innerPadding ->
            val backgroundColor by animateColorAsState(
                targetValue = selectedMood.baseColor,
                animationSpec = tween(durationMillis = 600),
                label = "BackgroundColorAnimation"
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                backgroundColor.copy(alpha = 0.7f),
                                backgroundColor.copy(alpha = 0.9f),
                                Color.Black
                            )
                        )
                    )
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Mood Grid
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.height(280.dp)
                    ) {
                        items(DataSource.moods) { mood ->
                            MoodCard(
                                mood = mood,
                                isSelected = mood == selectedMood,
                                onClick = { selectedMood = mood }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Recommendation Card
                    RecommendationCard(
                        recommendation = currentRecommendation,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Shuffle Button
                    Button(
                        onClick = {
                            currentRecommendation = recommendations.random()
                        },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .padding(horizontal = 32.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Shuffle,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Shuffle Vibe",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun MoodCard(
    mood: Mood,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color.White else Color.Transparent
    val cardAlpha by animateColorAsState(
        targetValue = if (isSelected) mood.baseColor else mood.baseColor.copy(alpha = 0.4f),
        animationSpec = tween(400),
        label = "CardColorAnimation"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = borderColor,
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = cardAlpha
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(mood.emoji, fontSize = 42.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                mood.label,
                style = MaterialTheme.typography.labelLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun RecommendationCard(
    recommendation: Recommendation,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1E1E).copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Recommendation Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(Color.DarkGray)
            ) {
                if (recommendation.imageUrl != null) {
                    AsyncImage(
                        model = recommendation.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(Color.DarkGray, Color.Black)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            recommendation.category.name,
                            color = Color.White.copy(alpha = 0.1f),
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    recommendation.category.name.replace("_", " "),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        recommendation.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { /* Play/Action */ },
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(50))
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    recommendation.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.LightGray,
                    lineHeight = 22.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun VibeCheckPreview() {
    VibeCheckApp()
}
