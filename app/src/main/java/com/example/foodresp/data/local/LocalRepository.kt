package com.example.foodresp.data.local

import android.content.Context
import kotlinx.coroutines.flow.Flow

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
class LocalRepository(context: Context){
    private val recipeDao = RecipeDatabase.getInstance(context).getRecipeDao()

    //插入数据
    suspend fun insertRecipe(recipeEntity: RecipeEntity){
        recipeDao.insertRecipe(recipeEntity)
    }

    //查询数据
    fun getRecipes(type:String): Flow<List<RecipeEntity>>{
        return recipeDao.getRecipes(type)
    }

    //更新数据
    suspend fun updateRecipe(recipeEntity: RecipeEntity){
        recipeDao.updateRecipe(recipeEntity)
    }

}