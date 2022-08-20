package com.organizee.boundary.shared.utils

import java.text.Normalizer

//fun String.toSlug() = lowercase(Locale.getDefault())
//    .replace("\n", " ")
//    .replace("[^a-z\\d\\s]".toRegex(), " ")
//    .split(" ")
//    .joinToString("-")
//    .replace("-+".toRegex(), "-")

fun String.toSlug() = Normalizer
    .normalize(this, Normalizer.Form.NFD)
    .replace("[^\\p{ASCII}]".toRegex(), "")
    .replace("[^a-zA-Z0-9\\s]+".toRegex(), "").trim()
    .replace("\\s+".toRegex(), "-").lowercase()

