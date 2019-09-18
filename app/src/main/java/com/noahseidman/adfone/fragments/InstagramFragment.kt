package com.noahseidman.adfone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.noahseidman.adfone.R
import com.noahseidman.adfone.adapter.MultiTypeDataBoundAdapter
import com.noahseidman.adfone.databinding.FragmentInstagramBinding
import com.noahseidman.adfone.delegates.LoadPostsImpl
import com.noahseidman.adfone.interfaces.LoadPosts
import com.noahseidman.adfone.interfaces.PostCallback
import com.noahseidman.adfone.viewmodels.PostViewModel

class InstagramFragment : Fragment(), PostCallback, LoadPosts by LoadPostsImpl() {

    private val authToken by lazy {
        arguments!!.getString(AUTH_TOKEN)
    }

    private val user by lazy {
        arguments!!.getString(USER)
    }

    lateinit var binding: FragmentInstagramBinding
    private val adapter = MultiTypeDataBoundAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInstagramBinding.inflate(inflater)
        val activity = activity as? AppCompatActivity
        activity?.setSupportActionBar(binding.toolbar)
        activity?.supportActionBar?.setTitle(R.string.user_stream)
        binding.recycler.layoutManager = LinearLayoutManager(context!!)
        binding.recycler.adapter = adapter
        loadPosts(authToken, user, {
            binding.progress.visibility = View.VISIBLE
        }, { userData ->
            adapter.addItems(userData.data.map { PostViewModel(it) })
            binding.progress.visibility = View.GONE
            binding.switcher.displayedChild = 1
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelPostLoading()
    }

    companion object {
        const val TAG = "InstagramFragment"
        const val AUTH_TOKEN = "AUTH_TOKEN"
        const val USER = "USER"

        fun show(activity: FragmentActivity, authToken: String, user: String) {
            val instagramFragment = InstagramFragment()
            val bundle = Bundle()
            bundle.putString(AUTH_TOKEN, authToken)
            bundle.putString(USER, user)
            instagramFragment.arguments = bundle
            activity.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container,
                    instagramFragment,
                    TAG
                )
                .addToBackStack(TAG)
                .commit()
        }
    }
}