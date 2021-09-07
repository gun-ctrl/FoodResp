package com.example.foodresp.utils

import androidx.room.TypeConverter
import com.example.foodresp.data.model.FoodRecipe
import com.example.foodresp.data.model.Result
import com.google.gson.Gson

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
class RecipeTypeConverter {
    //foodRecipe -> String
    @TypeConverter
    fun foodRecipeToString(recipe:FoodRecipe):String{
        return Gson().toJson(recipe)
    }

    //String -> foodRecipe
    @TypeConverter
    fun stringToFoodRecipe(string: String):FoodRecipe{
        return Gson().fromJson(string,FoodRecipe::class.java)
    }

    @TypeConverter
    fun resultToString(recipe:Result):String{
        return Gson().toJson(recipe)
    }
    @TypeConverter
    fun stringToResult(str:String):Result{
        return Gson().fromJson(str,Result::class.java)
    }
}