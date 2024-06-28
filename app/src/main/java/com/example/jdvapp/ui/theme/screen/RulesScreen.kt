package com.example.jdvapp.ui.theme.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jdvapp.R
import com.example.jdvapp.ui.theme.JDVAppTheme
import com.example.jdvapp.ui.theme.Routes
import com.example.jdvapp.ui.theme.model.JDVViewModel

@Composable
fun RulesScreen(navHostController: NavHostController? , JDVViewModel: JDVViewModel?=null) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bellesetoilesbrillantesdanscielnocturne),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement =  Arrangement.spacedBy(380.dp)){


            Text(
                text = "Les Règles du Jeu de la Vie Expliquées",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Image(painter =painterResource(id = R.drawable.fleche), contentDescription = "retour",modifier = Modifier
                .size(150.dp)
                .clickable(onClick = {
                    println(navHostController)
                    if (navHostController != null) {
                        navHostController.navigate(Routes.GridScreen.route)
                    }
                }))
            }

            UnderlinedText(text = "Création de la Grille :")
            Text(text = "Imagine un grand tableau rempli de ronds, c'est là que se joue le Jeu de la Vie.", color = Color.White)

            UnderlinedText(text = "État Initial :")
            Text(text = "Chaque rond peut être soit tout rouge et en vie, soit tout blanc et endormi.", color = Color.White)

            UnderlinedText(text = "Évolution :")
            Text(text = "Le tableau change au fil du temps, comme une histoire qui se déroule.", color = Color.White)

            UnderlinedText(text = "Règles de Survie :")
            Text(text = "Les ronds rouges (ceux qui sont déjà en vie) ont des règles. S'ils ont 2 ou 3 voisins rouges, ils restent en vie. Mais s'ils en ont trop, ils doivent s'endormir.", color = Color.White)

            UnderlinedText(text = "Règle de Naissance :")
            Text(text = "Un rond blanc (endormi) peut se réveiller ! S'il a exactement 3 voisins rouges, il se réveille et devient rouge à son tour.", color = Color.White)

            UnderlinedText(text = "Stabilité et Oscillation :")
            Text(text = "Parfois, certains ronds restent toujours pareils (stabilité), et d'autres changent tout le temps (oscillation).", color = Color.White)

            UnderlinedText(text = "Motifs Magiques :")
            Text(text = "Le Jeu de la Vie peut créer des dessins magiques, comme des ronds qui bougent ou des formes étonnantes.", color = Color.White)

            Text(
                text = "C'est incroyable comment de simples règles peuvent rendre ce jeu si intéressant ! Même si c'est un jeu imaginaire, il peut nous aider à comprendre des choses dans l'informatique, la biologie, et même comment fonctionnent certains jeux vidéo. Amuse-toi bien!",
                modifier = Modifier.padding(top = 8.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun UnderlinedText(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textDecoration = TextDecoration.Underline
    )
}
