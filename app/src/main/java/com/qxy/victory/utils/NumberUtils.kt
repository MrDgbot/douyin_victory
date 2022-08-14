package com.qxy.victory.utils

import java.math.BigDecimal


class NumberUtils {
  fun formatNum(num: String?): StringBuffer? {
    val sb = StringBuffer()
    val b1 = BigDecimal("10000")
    val b2 = BigDecimal("100000000")
    val b3 = BigDecimal(num)
    var formatNumStr = ""
    var unit = ""

    // 以万为单位处理
    if (b3.compareTo(b1) == -1) {
      formatNumStr = b3.toString()
    } else if (b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1
      || b3.compareTo(b2) == -1
    ) {
      unit = "万"
      formatNumStr = b3.divide(b1).toString()
    } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
      unit = "亿"
      formatNumStr = b3.divide(b2).toString()
    }
    if ("" != formatNumStr) {
      var i = formatNumStr.indexOf(".")
      if (i == -1) {
        sb.append(formatNumStr).append(unit)
      } else {
        i += 1
        val v = formatNumStr.substring(i, i + 1)
        if (v != "0") {
          sb.append(formatNumStr.substring(0, i + 1)).append(unit)
        } else {
          sb.append(formatNumStr.substring(0, i - 1)).append(unit)
        }
      }
    }
    return sb.ifEmpty { sb.append("0") }
  }
}