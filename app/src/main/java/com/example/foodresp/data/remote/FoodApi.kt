package com.example.foodresp.data.remote

import com.example.foodresp.data.model.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
interface FoodApi {
    //使用Response包装，判断响应是否成功
    //基地址：https://api.spoonacular.com/
    @GET("recipes/complexSearch?&addRecipeInformation=true&fillIngredients=true&apiKey=cee86c0392064ef8b4a26f4bd5e4404c")
    suspend fun fetchFoodRecipes(@Query("type")type:String):Response<FoodRecipe>
}