package com.noahseidman.adphone.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.noahseidman.adphone.R
import com.noahseidman.adphone.adapter.LayoutBinding
import com.noahseidman.adphone.models.PostData

class PostViewModel(val postData: PostData) : BaseObservable(), LayoutBinding {

    override val layoutId: Int
        get() = R.layout.post_item

    @Bindable
    fun getImage(): String {
        return postData.images.standard_resolution.url
    }

    @Bindable
    fun getProfileImage(): String {
        return postData.caption.from.profile_picture
    }

    @Bindable
    fun getUsername(): String {
        return postData.caption.from.username
    }

    @Bindable
    fun getComment(): String {
        return postData.caption.text
    }
}