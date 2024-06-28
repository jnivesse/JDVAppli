package com.example.jdvapp.ui.theme.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.random.Random

const val SLEEP = 0
const val LIVE = 1
class JDVViewModel:ViewModel() {

    //initialisation de la grille

    var gridWidthVm = mutableIntStateOf(10)
    var gridHeightVm = mutableIntStateOf(10)
    var gridVm =  randomGrid(gridHeightVm,gridWidthVm)

    var user = mutableStateOf(UserBean(userPseudo = ""))
    fun randomGrid(gridHeightVm: MutableIntState,gridWidthVm: MutableIntState):MutableList<MutableList<Int>> {
        return MutableList(gridHeightVm.intValue) { MutableList(gridWidthVm.intValue) { if (Random.nextBoolean()) LIVE else SLEEP } }
    }

    //check2 & check3 dans une seule fonction
    fun check2et3(grid: List<List<Int>>, tempoGrid: MutableList<MutableList<Int>>, ligne: Int, colonne: Int): List<List<Int>> {
        var around = 0
        for (k in -1..1) {
            var tempoLigne = ligne + k
            if (tempoLigne < 0) {
                tempoLigne = grid.size - 1
            }
            if (tempoLigne > grid.size - 1) {
                tempoLigne = 0
            }
            for (l in -1..1) {
                var tempoColonne = colonne + l
                if (tempoColonne < 0) {
                    tempoColonne = grid[tempoLigne].size - 1
                }
                if (tempoColonne > grid[tempoLigne].size - 1) {
                    tempoColonne = 0
                }
                if (grid[tempoLigne][tempoColonne] == 1) {
                    around++
                }
            }
        }
        if (around == 3) {
            tempoGrid[ligne][colonne] = 1
        }
        if (around == 4 && grid[ligne][colonne] == 1) {
            tempoGrid[ligne][colonne] = 1
        }
        return tempoGrid
    }

    // faire un saut de 1 avec la barre espace
    fun evolution(grid: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        val tempoGrid = MutableList(gridHeightVm.intValue) { MutableList(gridWidthVm.intValue) { 0 } }
        for (ligne in 0 until grid.size) {
            for (colonne in 0 until grid[ligne].size) {
                check2et3(grid, tempoGrid, ligne, colonne)
            }
        }
        viewModelScope.launch(Dispatchers.Main) {
            try {
                for (j in 0 until gridHeightVm.intValue) {
                    for (i in 0 until gridWidthVm.intValue) {
                        if (tempoGrid[j][i] == 1) {
                            grid[j][i] = 1
                        } else if (tempoGrid[j][i] == 0) {
                            grid[j][i] = 0
                        }
                    }
                }
            }catch(e: IOException) {
                println("catch")
                e.printStackTrace()
            }
        }
        return grid
    }
    fun exterminatus(grid: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        for (i in 0 until grid.size) {
            for (j in 0 until grid[i].size) {
                grid[i][j] = 0
            }
        }
        return grid
    }
    //fonction de lecture de grille en console
    fun printGrid(grid: MutableList<MutableList<Int>>) {
        for (i in 0 until grid.size) {
            println()
            for (j in 0 until grid[i].size) {
                print(grid[i][j])
            }
        }
    }
    fun stringToGrid(jsonString: String): ArrayList<ArrayList<Int>> {
        val gson = Gson()
        val type = object : TypeToken<ArrayList<ArrayList<Int>>>() {}.type
        return gson.fromJson(jsonString, type)
    }

    fun addGrid(){
        user.value.id?.let { JDVAPI.addGrid(it, grid = gridVm.toString()) }
    }

}