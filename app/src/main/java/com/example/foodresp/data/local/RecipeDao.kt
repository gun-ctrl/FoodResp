package com.example.foodresp.data.local

import androidx.room.*
import com.example.foodresp.data.local.entity.FavoriteEntity
import com.example.foodresp.data.local.entity.RecipeEntity
import com.example.foodresp.data.model.Result
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

    /**------------favorite----------*/
    //查询所有收藏的食谱
    @Query("select * from favorite_table")
    fun getAllFavorites():Flow<List<FavoriteEntity>>

    //插入收藏的食谱
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteEntity: FavoriteEntity)

    //删除收藏
    @Delete
    fun deleteFavorite(favoriteEntity: FavoriteEntity)
}