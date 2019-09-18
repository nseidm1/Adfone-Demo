package com.noahseidman.adfone

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.noahseidman.adfone.RoboSupport.NetworkSecurityPolicyShadow
import com.noahseidman.adfone.RoboSupport.TabLayoutShadow
import com.noahseidman.adfone.RoboSupport.ViewCompatShadow
import com.noahseidman.adfone.di.DaggerTestComponents
import com.noahseidman.adfone.di.InjectionsUtils
import com.noahseidman.adfone.di.TestComponents
import com.noahseidman.adfone.fragments.InstagramFragment
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(
    sdk = [27],
    shadows = [TabLayoutShadow::class, ViewCompatShadow::class, NetworkSecurityPolicyShadow::class],
    application = TestApplication::class
)
class ExampleUnitTest {

    init {
        InjectionsUtils.setTestComponents(DaggerTestComponents.builder().context(ApplicationProvider.getApplicationContext()).build())
        (InjectionsUtils.components as TestComponents).inject(this)
        Picasso.setSingletonInstance(Picasso.Builder(ApplicationProvider.getApplicationContext()).build())
        RxJavaPlugins.setIoSchedulerHandler { AndroidSchedulers.mainThread() }
        RxJavaPlugins.setComputationSchedulerHandler { AndroidSchedulers.mainThread() }
    }

    @Test
    fun instagramFragment() {
        val bundle = Bundle()
        bundle.putString(InstagramFragment.USER, "1621043365")
        bundle.putString(InstagramFragment.AUTH_TOKEN, "")
        val instagramFragment = FragmentScenario.launch(InstagramFragment::class.java, bundle)
        instagramFragment.onFragment { fragment ->
            check(fragment.binding.recycler.adapter!!.itemCount > 0)
        }
    }
}
