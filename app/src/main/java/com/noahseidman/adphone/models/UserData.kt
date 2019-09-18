package com.noahseidman.adphone.models

data class UserData(val data: Array<PostData>)
data class PostData(val images: Images, val caption: Caption)
data class Images(
    val thumbnail: Thumbnail,
    val low_resolution: LowResolution,
    val standard_resolution: StandardResolution
)

data class Thumbnail(val width: Int, val height: Int, val url: String)
data class LowResolution(val width: Int, val height: Int, val url: String)
data class StandardResolution(val width: Int, val height: Int, val url: String)
data class Caption(val from: From, val text: String)
data class From(val profile_picture: String, val username: String)