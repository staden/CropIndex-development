package org.mtri.navdraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *  This class inflates fragment6_layout.xml
 *
 *
 */
public class fragment6 extends android.support.v4.app.Fragment {
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootview = inflater.inflate(R.layout.fragment6_layout, container, false);
        return rootview;
    }

}
