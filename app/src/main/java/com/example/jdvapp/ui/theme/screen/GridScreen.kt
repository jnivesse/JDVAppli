package com.example.jdvapp.ui.theme.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jdvapp.R
import com.example.jdvapp.ui.theme.JDVAppTheme
import com.example.jdvapp.ui.theme.Routes
import com.example.jdvapp.ui.theme.model.JDVViewModel


const val SLEEP = 0
const val LIVE = 1

@Composable
fun GridScreen(navHostController: NavHostController? , JDVViewModel: JDVViewModel) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
        contentAlignment = Alignment.TopCenter    ) {
        Image(
            //fond d'écran
            painter = painterResource(id = R.drawable.bellesetoilesbrillantesdanscielnocturne),
            contentDescription = "des étoiles",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth
        )
        Column (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)){
            Row (modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically){
                //Boutons de navigation
                Text("Connexion / Inscription",
                    color = Color.White,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable(onClick = {
                        println("connexion")
                        if (navHostController != null) {
                            navHostController.navigate(Routes.LogScreen.route)
                        }
                    })
                )
                Image(painter =painterResource(id = R.drawable.hauteur), contentDescription = "hauteur",modifier = Modifier
                    .size(75.dp)
                )
                Image(painter =painterResource(id = R.drawable.largeur), contentDescription = "largeur",modifier = Modifier
                    .size(95.dp)
                )
                Image(painter =painterResource(id = R.drawable.regles), contentDescription = "regles",modifier = Modifier
                    .size(95.dp)
                    .clickable(onClick = {
                        println(navHostController)
                        navHostController?.navigate(Routes.RulesScreen.route)
                    })
                )
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),

                )
            {
                // La grille
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Les potars
                    SliderAdvancedExample(JDVViewModel)
                    // L'affichage de la grille
                    DisplayGrid(JDVViewModel)
                }
            }
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ){
//Les boutons d'action sur la grille
            Image(painter =painterResource(id = R.drawable.jump), contentDescription = "saut",modifier = Modifier
                .size(75.dp)
                .clickable(onClick = {
                    JDVViewModel.evolution(JDVViewModel.gridVm)
                    println(JDVViewModel.printGrid(JDVViewModel.gridVm))
                })
            )
            if(JDVViewModel.user.value.id!=null){
                Image(painter = painterResource(id = R.drawable.enregistrer), contentDescription = "enregisté",modifier = Modifier
                    .size(75.dp)
                    .clickable(onClick = { JDVViewModel.addGrid() })
                )}
            Image(painter = painterResource(id = R.drawable.exterminatus), contentDescription ="exterminatus",modifier = Modifier
                .size(75.dp)
                .clickable(onClick = {
                    JDVViewModel.exterminatus(JDVViewModel.gridVm)
                    JDVViewModel.printGrid(JDVViewModel.gridVm)
                })
            )
            Image(painter = painterResource(id = R.drawable.lecture) , contentDescription ="lecture",modifier = Modifier
                .size(75.dp)
                .clickable(onClick = { println("lecture") })
            )
        }
    }
}

// La fonction pour les cellules
@Composable
fun SwitchColorButton(
    modifier: Modifier = Modifier,
    size:  Dp = 15.dp,
    colorState:Int,
    onClick: () -> Unit
) {
    var isRed by remember { mutableIntStateOf(colorState) }
    val buttonColor = if (isRed == LIVE) Color.Red.copy(alpha = 0.85f) else Color.White.copy(alpha = 0.85f)
    Surface(
        modifier = modifier
            .size(size),
        //.shadow(elevation = 1.dp, shape= RoundedCornerShape(50), )
        //.background(buttonColor),
        shape = CircleShape,
        tonalElevation = 2.dp,
        color = buttonColor,
        onClick = {
            isRed = 1-isRed
            onClick()
        }
    ) {}
}
//fonction pour les potars
@Composable
fun SliderAdvancedExample(JDVViewModel: JDVViewModel) {
    var sliderPositionH by remember { mutableFloatStateOf(10f) }
    var sliderPositionW by remember { mutableFloatStateOf(10f) }
    JDVViewModel.gridHeightVm.intValue=sliderPositionH.toInt()
    JDVViewModel.gridWidthVm.intValue=sliderPositionW.toInt()
    JDVViewModel.gridVm=JDVViewModel.randomGrid(JDVViewModel.gridHeightVm,JDVViewModel.gridWidthVm)
    Row {
        Slider(
            value = sliderPositionH,
            onValueChange = { sliderPositionH = it },
            modifier = Modifier.width(150.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTrackColor = Color.Red,
                inactiveTrackColor = Color.White,
            ),
            steps = 530,
            valueRange = 10f..63f
        )
        Text(text = sliderPositionW.toString())
        Slider(
            value = sliderPositionW,
            onValueChange = { sliderPositionW = it },
            modifier = Modifier.width(150.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTrackColor = Color.Red,
                inactiveTrackColor = Color.White,
            ),
            steps = 530,
            valueRange = 10f..63f
        )
    }
}
//fonction d'affichage de la grille

@Composable
fun DisplayGrid(JDVViewModel: JDVViewModel){
    for (i in 0 until JDVViewModel.gridVm.size) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            verticalAlignment = Alignment.CenterVertically){

            for (j in 0 until JDVViewModel.gridVm[i].size) {
                SwitchColorButton(
                    colorState = JDVViewModel.gridVm[i][j],
                    onClick = {
                        JDVViewModel.gridVm[i][j]=1-JDVViewModel.gridVm[i][j]
                        println(JDVViewModel.gridVm[i][j])
                    }
                )
            }
        }
    }
}

/*
@Composable
fun PlayButton() {
    var compteur by remember { mutableStateOf(0) }
    var imageRes by remember { mutableStateOf(R.drawable.lecture) } // Initial image
    var intervalEvolution by remember { mutableStateOf(600L) }

    LaunchedEffect(compteur) {
        when (compteur) {
            1 -> {
                imageRes = R.drawable.avancerapide
                intervalEvolution = 600L
            }
            2 -> {
                imageRes = R.drawable.avancetresrapide
                intervalEvolution = 300L
            }
            3 -> {
                imageRes = R.drawable.pause
                intervalEvolution = 150L
            }
            4 -> {
                imageRes = R.drawable.lecture
                intervalEvolution = 0L
                compteur = 0
            }
        }
        // Here you would handle your interval logic based on intervalEvolution
        // This is where you would start and stop your intervals
    }

    Button(onClick = { compteur++ }) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
*/