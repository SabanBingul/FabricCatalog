package com.sabanbingul.fabriccatalog.model

data class Fabric(
    val uuid: Int = 0,
    val name: String? = null,
    val weight: String? = null,
    val material: String? = null,
    val width: String? = null,
    val info: String? = null,
    val pic: String? = null // Resim URL'sini ekleyin
)
