package com.qxy.victory.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.qxy.victory.netapi.BoardNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException


object Repository {

    fun getBoards(id : Int)=liveData(Dispatchers.IO){
        val result=try {
            val boardResponse=BoardNetwork.getBoards(id)
            Log.d("AlphTest", "getBoards: "+boardResponse.toString())
        }catch (e:Exception){

            Log.d("AlphTest", "boardResponse fail: ")
        }
        emit(result)


    }


}