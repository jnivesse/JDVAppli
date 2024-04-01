package com.example.jdvapp.ui.theme.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.random.Random

object JDVAlgo {
    const val SLEEP = 0
    const val LIVE = 1

    //initialisation de la grille
    var gridWidth = 10
    var gridHeight = 10
    var grid = ArrayList<ArrayList<Int>>(gridHeight);

    fun randomGrid():ArrayList<ArrayList<Int>> {
        for (i in 0 until gridHeight) {
            val row = ArrayList<Int>(gridWidth)
            for (j in 0 until gridWidth) {
                val randomState = if (Random.nextBoolean()) LIVE else SLEEP
                row.add(randomState)
            }
            grid.add(row)
        }
        return grid
    }

    //check2 & check3 dans une seule fonction
    fun check2et3(
        grid: ArrayList<ArrayList<Int>>,
        tempoGrid: ArrayList<ArrayList<Int>>,
        ligne: Int,
        colonne: Int
    ): ArrayList<ArrayList<Int>> {
        var around = 0;
        for (k in -1 .. 1) {
            var tempoLigne = ligne + k;
            if (tempoLigne < 0) {
                tempoLigne = grid.size - 1;
            }
            if (tempoLigne > grid.size - 1) {
                tempoLigne = 0;
            }
            for (l in -1 .. 1) {
                var tempoColonne = colonne + l;
                if (tempoColonne < 0) {
                    tempoColonne = grid[tempoLigne].size - 1;
                }
                if (tempoColonne > grid[tempoLigne].size - 1) {
                    tempoColonne = 0;
                }
                if (grid[tempoLigne][tempoColonne] == 1) {
                    around++;
                }
            }
        }
        if (around == 3) {
            tempoGrid[ligne][colonne] = 1;
        }
        if (around == 4 && grid[ligne][colonne] == 1) {
            tempoGrid[ligne][colonne] = 1;
        }
        return tempoGrid;
    }

    // faire un saut de 1 avec la barre espace
    fun evolution(grid: ArrayList<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
        var tempoGrid = ArrayList<ArrayList<Int>>();
        for (j in 0 until grid.size) {
            var tabm = ArrayList<Int>();
            for (i in 0 until grid[j].size) {
                tabm.add(0);
            }
            tempoGrid.add(tabm);
        }
        for (ligne in 0 until grid.size) {
            for (colonne in 0 until grid[ligne].size) {
                check2et3(grid, tempoGrid, ligne, colonne)
            }
        }
        for (j in 0 until gridHeight) {
            for (i in 0 until gridWidth) {
                if (tempoGrid[j][i] == 1) {
                    grid[j][i] = 1;
                } else if (tempoGrid[j][i] == 0) {
                    grid[j][i] = 0;
                }
            }
        }
        return grid;
    }

    //fonction de lecture de grille en console
    fun printGrid(grid: ArrayList<ArrayList<Int>>) {
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
}

fun main() {
    var test =JDVAlgo.randomGrid()

    var testString=test.toString()
    println(testString)
    var bbb=JDVAlgo.stringToGrid(testString)
    JDVAlgo.printGrid(bbb)
}