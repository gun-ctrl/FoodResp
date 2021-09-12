package com.example.foodresp.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodresp.data.local.LocalRepository
import com.example.foodresp.data.local.entity.RecipeEntity
import com.example.foodresp.utils.NetworkResult
import com.example.foodresp.data.model.FoodRecipe
import com.example.foodresp.data.remote.RemoteRepository
import com.example.foodresp.utils.showToast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
class MainViewModel(application: Application):AndroidViewModel(application) {
    //网络请求对象
    private val remoteRepository = RemoteRepository()
    //数据库的操作对象
    private val localRepository = LocalRepository(getApplication())

    //提供给外部观察数据变化
    var recipes: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()


    //外部通过这个方法发起网路请求
    fun fetchFoodRecipes(type: String) {
        //判断是否有网络连接
        if (hasInternetConnection()) {
            viewModelScope.launch {
                val result = localRepository.getRecipes(type)
                result.collect {
                    if (it.isNotEmpty()) {
                        val entity = it.first()
                        val data = entity.recipe
                        recipes.value = NetworkResult.Success(data)
                    } else {
                        //处于loading状态
                        recipes.value = NetworkResult.Loading()

                        val response = remoteRepository.fetchFoodRecipes(type)
                        if (response.isSuccessful) {
                            //获取数据成功 处于success状态
                            recipes.value = NetworkResult.Success(response.body())
                            //需要将数据保存到数据库
                            localRepository.insertRecipe(RecipeEntity(0, type, response.body()!!))
                        } else {
                            //获取数据失败 处于error状态
                            recipes.value = NetworkResult.Error(response.message())
                        }
                    }
                }
            }
        } else {
            //没有网络连接
            showToast(getApplication(),"No Internet Connection")
            viewModelScope.launch {
                val result = localRepository.getRecipes(type)
                result.collect {
                    if (it.isNotEmpty()) {
                        val entity = it.first()
                        val data = entity.recipe
                        recipes.value = NetworkResult.Success(data)
                    }else{
                        recipes.value = NetworkResult.Error("NO Data!")
                    }
                }
            }
        }
    }
        //判断是否有网络连接
        private fun hasInternetConnection(): Boolean {
            //获取系统的网络连接管理器
            val connectivityManager = getApplication<Application>()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capability =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                //wifi
                capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                //手机本身的网络 蜂窝网
                capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //以太网
                capability.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
}