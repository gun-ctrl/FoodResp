package com.example.foodresp.utils

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
/**
 * 密封类
 * 我的理解：根据这里的需要，封装了不同的状态，比如数据加载成功、失败、正在加载……
 */
sealed class NetworkResult<T>(
    val data:T? = null,
    val message:String?=null){
    class Error<T>(errMsg:String): NetworkResult<T>(message = errMsg)
    class Success<T>(data: T?): NetworkResult<T>(data)
    class Loading<T>(): NetworkResult<T>()
}
