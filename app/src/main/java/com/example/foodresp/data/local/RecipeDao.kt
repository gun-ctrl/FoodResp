package com.example.foodresp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
@Dao
interface RecipeDao {
    //插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    //查询数据
    @Query("select * from foodRecipeTable where type=:type")
    fun getRecipes(type:String):Flow<List<RecipeEntity>>

    //更新数据
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecipe(recipeEntity: RecipeEntity)
}