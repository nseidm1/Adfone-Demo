package com.noahseidman.adfone.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.noahseidman.adfone.R
import com.noahseidman.adfone.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private val clientId by lazy {
        arguments!!.getString(CLIENT_ID)
    }

    private val redirectUrl by lazy {
        arguments!!.getString(REDIRECT_URL)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAuthBinding.inflate(inflater)
        val authUrl = String.format(getString(R.string.auth_url), clientId, redirectUrl)
        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                val splits = url.split("access_token=")
                if (splits.size > 1) {
                    val authToken: String = splits[1]
                    val user: String = authToken.split(".")[0]
                    activity?.let { activity ->
                        InstagramFragment.show(activity, authToken, user)
                    }
                } else {
                    view.loadUrl(url)
                }
                return false
            }
        }
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(authUrl)

        return binding.root
    }

    companion object {
        const val TAG = "AuthFragment"
        const val CLIENT_ID = "CLIENT_ID"
        const val REDIRECT_URL = "REDIRECT_URL"


        fun show(activity: FragmentActivity, clientId: String, redirectUrl: String) {
            val authFragment = AuthFragment()
            val bundle = Bundle()
            bundle.putString(CLIENT_ID, clientId)
            bundle.putString(REDIRECT_URL, redirectUrl)
            authFragment.arguments = bundle
            activity.supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container,
                    authFragment,
                    TAG
                )
                .addToBackStack(TAG)
                .commit()
        }
    }
}