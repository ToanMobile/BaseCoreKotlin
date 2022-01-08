package com.toan_mobile.module.util.extension

fun List<String>?.equalOther(other: List<String>?): Boolean {
    if (this?.size != other?.size)
        return false
    var equal = true
    for (index in other!!.indices) {
        if (this?.get(index) != other[index])
            equal = false
    }
    return equal
}
