package com.example.foodresp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodresp.data.local.LocalRepository
import com.example.foodresp.data.local.entity.FavoriteEntity
import com.example.foodresp.data.model.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
class FavoriteViewModel(application: Application):AndroidViewModel(application) {
    private val localRepository = LocalRepository(application)
    val favoriteRecipes:MutableLiveData<List<FavoriteEntity>> = MutableLiveData()

    //查询所有收藏的食谱
    fun readFavorites(){
        viewModelScope.launch {
            localRepository.getAllFavorites().collect {
//                val resultList = mutableListOf<Result>()
//                it.forEach{entity ->
//                    resultList.add(entity.result)
//                }
                favoriteRecipes.value = it
            }
        }
    }
    //插入收藏的食谱
    fun insertFavorite(result:Result){
        viewModelScope.launch {
            val favoriteEntity = FavoriteEntity(0,result)
            localRepository.insertFavorite(favoriteEntity)
        }
    }
    //删除收藏的食谱
    fun deleteFavorite(favoriteEntity: FavoriteEntity){
        viewModelScope.launch {
            localRepository.deleteFavorite(favoriteEntity)
        }
    }

}