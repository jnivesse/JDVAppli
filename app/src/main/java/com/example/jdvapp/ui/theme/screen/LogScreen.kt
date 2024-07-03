package com.example.jdvapp.ui.theme.screen

import android.content.res.Configuration
import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jdvapp.R
import com.example.jdvapp.ui.theme.JDVAppTheme
import com.example.jdvapp.ui.theme.Routes
import com.example.jdvapp.ui.theme.model.JDVViewModel
import org.w3c.dom.Text


@Composable
fun LogScreen(navHostController: NavHostController? , JDVViewModel: JDVViewModel) {
    var gridString1 by remember { mutableStateOf("Grille 1 vide") }
    var gridString2 by remember { mutableStateOf("Grille 2 vide") }
    var gridString3 by remember { mutableStateOf("Grille 3 vide") }
    var gridString4 by remember { mutableStateOf("Grille 4 vide") }
    var gridString5 by remember { mutableStateOf("Grille 5 vide") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bellesetoilesbrillantesdanscielnocturne),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
        )

        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically){
            AccountCreationButton(JDVViewModel)
            AccountLoadButton(JDVViewModel)
            Image(painter =painterResource(id = R.drawable.fleche), contentDescription = "retour",modifier = Modifier
                .size(150.dp)
                .clickable(onClick = {
                    println(navHostController)
                    if (navHostController != null) {
                        navHostController.navigate(Routes.GridScreen.route)
                    }
                }))
        }


             Column{
                 Button(
                     onClick = {
                         JDVViewModel.gridVm = JDVViewModel.user.value.userGrid1?.let {
                             JDVViewModel.stringToGrid(it)
                         }!!
                         if (navHostController != null) {
                             navHostController.navigate(Routes.GridScreen.route)
                         }
                     },
                     colors = ButtonDefaults.buttonColors( Color.Gray),
                     shape = RoundedCornerShape(16.dp),  // This sets the corners to be rounded
                     modifier = Modifier
                         .padding(16.dp)
                 ) {
                     Text(
                         text = gridString1,
                         maxLines = 1,
                         overflow = TextOverflow.Ellipsis
                     )
                 }
                 Button(
                     onClick = {
                         JDVViewModel.gridVm = JDVViewModel.user.value.userGrid2?.let {
                             JDVViewModel.stringToGrid(it)
                         }!!
                         if (navHostController != null) {
                             navHostController.navigate(Routes.GridScreen.route)
                         }
                     },
                     colors = ButtonDefaults.buttonColors( Color.Gray),
                     shape = RoundedCornerShape(16.dp),  // This sets the corners to be rounded
                     modifier = Modifier
                         .padding(16.dp)
                 ) {
                     Text(
                         text = gridString2,
                         maxLines = 1,
                         overflow = TextOverflow.Ellipsis
                     )
                 }
                 Button(
                     onClick = {
                         JDVViewModel.gridVm = JDVViewModel.user.value.userGrid3?.let {
                             JDVViewModel.stringToGrid(it)
                         }!!
                         if (navHostController != null) {
                             navHostController.navigate(Routes.GridScreen.route)
                         }
                     },
                     colors = ButtonDefaults.buttonColors( Color.Gray),
                     shape = RoundedCornerShape(16.dp),  // This sets the corners to be rounded
                     modifier = Modifier
                         .padding(16.dp)
                 ) {
                     Text(
                         text = gridString3,
                         maxLines = 1,
                         overflow = TextOverflow.Ellipsis
                     )
                 }
                 Button(
                     onClick = {
                         JDVViewModel.gridVm = JDVViewModel.user.value.userGrid4?.let {
                             JDVViewModel.stringToGrid(it)
                         }!!
                         if (navHostController != null) {
                             navHostController.navigate(Routes.GridScreen.route)
                         }
                     },
                     colors = ButtonDefaults.buttonColors( Color.Gray),
                     shape = RoundedCornerShape(16.dp),  // This sets the corners to be rounded
                     modifier = Modifier
                         .padding(16.dp)
                 ) {
                     Text(
                         text = gridString4,
                         maxLines = 1,
                         overflow = TextOverflow.Ellipsis
                     )
                 }
                 Button(
                     onClick = {
                         JDVViewModel.gridVm = JDVViewModel.user.value.userGrid5?.let {
                             JDVViewModel.stringToGrid(it)
                         }!!
                         if (navHostController != null) {
                             navHostController.navigate(Routes.GridScreen.route)
                         }
                     },
                     colors = ButtonDefaults.buttonColors( Color.Gray),
                     shape = RoundedCornerShape(16.dp),  // This sets the corners to be rounded
                     modifier = Modifier
                         .padding(16.dp)
                 ) {
                     Text(
                         text = gridString5,
                         maxLines = 1,
                         overflow = TextOverflow.Ellipsis
                     )
                 }

             }

    }
}


@Composable
fun AccountCreationButton(JDVViewModel: JDVViewModel?) {
    var showDialog by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf(TextFieldValue("").toString()) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Chargement de compte") },
            text = {
                Column {
                    Text(text = "Veuillez entrer votre pseudo.")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = { Text("Pseudo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                            if (JDVViewModel != null) {
                                JDVViewModel.createUser(userName)
                            }
                        if (JDVViewModel != null) {
                            if (JDVViewModel.user!=null){
                                println(JDVViewModel.user.value)
                            }
                        }
                        showDialog = false }
                ) {
                    Text("Confirmer")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("Annuler")
                }
            }
        )
    }

    Button(
        onClick = { showDialog = true },
        colors = ButtonDefaults.buttonColors(Color.Red),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Création de compte",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun AccountLoadButton(JDVViewModel: JDVViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    val user by JDVViewModel.user

    // Les états des chaînes de grilles
    var gridString1 by remember { mutableStateOf("Grille 1 vide") }
    var gridString2 by remember { mutableStateOf("Grille 2 vide") }
    var gridString3 by remember { mutableStateOf("Grille 3 vide") }
    var gridString4 by remember { mutableStateOf("Grille 4 vide") }
    var gridString5 by remember { mutableStateOf("Grille 5 vide") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Chargez votre compte") },
            text = {
                Column {
                    Text(text = "Veuillez entrer votre pseudo.")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = { Text("Pseudo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        JDVViewModel.loadUser(userName)
                        if (JDVViewModel != null) {

                            if (JDVViewModel.user!=null){
                                println(JDVViewModel.user.value)
                            }
                        }
                        showDialog = false
                    }
                ) {
                    Text("Confirmer")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("Annuler")
                }
            }
        )
    }

    // Utiliser un effet pour mettre à jour les chaînes de grilles lorsque l'utilisateur change
    LaunchedEffect(user) {
        gridString1 = user.userGrid1 ?: "Grille 1 vide"
        gridString2 = user.userGrid2 ?: "Grille 2 vide"
        gridString3 = user.userGrid3 ?: "Grille 3 vide"
        gridString4 = user.userGrid4 ?: "Grille 4 vide"
        gridString5 = user.userGrid5 ?: "Grille 5 vide"
    }

    Button(
        onClick = { showDialog = true },
        colors = ButtonDefaults.buttonColors(Color.Red),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Chargez votre compte",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
