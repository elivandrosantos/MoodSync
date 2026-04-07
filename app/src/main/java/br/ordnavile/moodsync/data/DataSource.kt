package br.ordnavile.moodsync.data

import androidx.compose.ui.graphics.Color

object DataSource {
    val moods = listOf(
        Mood("energetic", "Energetic", "🚀", Color(0xFFFF5722)),
        Mood("chill", "Chill", "🍿", Color(0xFF673AB7)),
        Mood("creative", "Creative", "🎸", Color(0xFF009688)),
        Mood("melancholy", "Melancholy", "🌧️", Color(0xFF3F51B5))
    )

    val recommendations = listOf(
        // Energetic
        Recommendation(
            "Watch an Action Movie",
            Category.MOVIE,
            "Get your adrenaline pumping with high-octane stunts and epic battles. Try 'Mad Max: Fury Road'",
            "energetic",
            "https://images.unsplash.com/photo-1536440136628-849c177e76a1?q=80&w=1000&auto=format&fit=crop"
        ),
        Recommendation(
            "High-Intensity Interval Training",
            Category.ACTIVITY,
            "A quick 20-minute workout to boost your energy and focus.",
            "energetic",
        ),
        Recommendation(
            "Power Rock Anthem",
            Category.MUSIC,
            "Listen to 'Thunderstruck' by AC/DC to get in the zone.",
            "energetic"
        ),
        // Chill
        Recommendation(
            "Listen to Lo-Fi Beats",
            Category.MUSIC,
            "Perfect background music for relaxing or focusing on a hobby.",
            "chill"
        ),
        Recommendation(
            "Start a New Book",
            Category.ACTIVITY,
            "Escape into a fictional world with a cup of herbal tea.",
            "chill"
        ),
        Recommendation(
            "The Grand Budapest Hotel",
            Category.MOVIE,
            "A whimsical and visually stunning adventure.",
            "chill"
        ),
        // Creative
        Recommendation(
            "Digital Illustration",
            Category.ACTIVITY,
            "Try sketching something inspired by your favorite song.",
            "creative"
        ),
        Recommendation(
            "Experimental Jazz Session",
            Category.MUSIC,
            "Broaden your horizons with complex rhythms and melodies.",
            "creative"
        ),
        Recommendation(
            "Inception",
            Category.MOVIE,
            "A mind-bending journey through layers of dreams.",
            "creative"
        ),
        // Melancholy
        Recommendation(
            "Acoustic Folk Playlist",
            Category.MUSIC,
            "Soft, meaningful lyrics for reflective moments.",
            "melancholy"
        ),
        Recommendation(
            "Journaling",
            Category.ACTIVITY,
            "Put your thoughts on paper to understand them better.",
            "melancholy"
        ),
        Recommendation(
            "Eternal Sunshine",
            Category.MOVIE,
            "A poignant exploration of memory and heartbreak.",
            "melancholy"
        )
    )
}
