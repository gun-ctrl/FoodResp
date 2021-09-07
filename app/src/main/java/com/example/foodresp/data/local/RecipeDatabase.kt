package com.example.foodresp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodresp.data.local.entity.FavoriteEntity
import com.example.foodresp.data.local.entity.RecipeEntity
import com.example.foodresp.utils.RecipeTypeConverter

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
@TypeConverters(RecipeTypeConverter::class)
@Database(entities = [RecipeEntity::class,FavoriteEntity::class],
version = 1,
exportSchema = false)
abstract class RecipeDatabase :RoomDatabase(){
    abstract fun getRecipeDao():RecipeDao

    companion object{
        @Volatile
        private var instance:RecipeDatabase?=null
        fun getInstance(context: Context):RecipeDatabase{
            if (instance!=null){
                return instance!!
            }
            synchronized(this){
                if (instance == null){
                    instance = Room.databaseBuilder(context,RecipeDatabase::class.java,
                        "food_recipe.db").build()
                }
                return instance!!
            }
        }
    }
}