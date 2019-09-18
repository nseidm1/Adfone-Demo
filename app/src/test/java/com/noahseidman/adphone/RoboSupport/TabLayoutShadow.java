package com.noahseidman.adphone.RoboSupport;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.shadows.ShadowView;

@Implements(TabLayout.class)
public class TabLayoutShadow extends ShadowView {
    public void __constructor__(Context context) {
        // no-op
    }

    public void __constructor__(Context context, AttributeSet attrs, int defStyleAttr) {
        // no-op
    }

    @Implementation
    public void addTab(@NonNull TabLayout.Tab tab) {

    }

    @Implementation
    public void addOnTabSelectedListener(@NonNull TabLayout.BaseOnTabSelectedListener listener) {

    }

    @Implementation
    public int getDefaultHeight() {
        return 0;
    }
}

