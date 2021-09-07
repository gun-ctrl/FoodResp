package com.example.foodresp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodresp.data.model.FoodRecipe

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
@Entity(tableName = "foodRecipeTable")
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val type:String,
    val recipe: FoodRecipe
)