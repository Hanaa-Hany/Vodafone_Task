package com.hanaahany.task.utils




fun changeDateFormat(timestamp: String): String {
    val parts = timestamp.split("T")
    val datePart = parts[0]
    return datePart
}