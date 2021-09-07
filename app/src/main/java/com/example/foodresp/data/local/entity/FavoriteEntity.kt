package com.example.foodresp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodresp.data.model.Result

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val recipe: Result
)