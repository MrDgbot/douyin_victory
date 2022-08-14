package com.qxy.victory.utils

import android.content.Context
import dalvik.system.DexFile
import java.io.IOException
import java.util.*


class NetWorkUtils {
  companion object {
    val instance = NetWorkHolder.holder
  }

  object NetWorkHolder {
    val holder = NetWorkUtils()
  }

  var urlClassMap: Map<String, List<String>> = mutableMapOf()

  fun init(context: Context) {
    getClassName(context, "network.url")
  }

  fun getClassName(context: Context, packageName: String?) {
    try {
      // 通过DexFile查找当前的APK中可执行文件
      val df = DexFile(context.packageCodePath)
      // 获取df中的元素 这里包含了所有可执行的类名 该类名包含了包名+类名的方式
      val enumeration: Enumeration<String> = df.entries()
      // 遍历所有的类名
      while (enumeration.hasMoreElements()) {
        val className = enumeration.nextElement() as String
        // 在当前所有可执行的类里面查找包含有该包名的所有类
        if (className.contains(packageName!!)) {

          val classObject = Class.forName(className);
          val currentPropertyList = mutableListOf<String>()

          classObject.declaredFields.forEach {
            val name = it.name.lowercase(Locale.getDefault())

            if (name !in "instance" && name !in "token") {
              it.get(it)?.let { it1 -> currentPropertyList.add(it1.toString()) }
            }
          }

          this.urlClassMap += classObject.simpleName to currentPropertyList
        }
      }
    } catch (e: IOException) {
      e.printStackTrace()
    }
  }
}