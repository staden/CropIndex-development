package org.mtri.navdraw;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by sam on 3/23/15.
 */
public class fragment1 extends android.support.v4.app.Fragment implements View.OnClickListener{

    Button btnShowLocation;

    GPSTracker gps;

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootview = inflater.inflate(R.layout.fragment1_layout, container, false);


        btnShowLocation = (Button) rootview.findViewById(R.id.show_location);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gps = new org.mtri.navdraw.GPSTracker(getActivity());

                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(
                            getActivity().getApplicationContext(),
                            "Your Location is -\nLat: " + latitude + "\nLong: "
                                    + longitude, Toast.LENGTH_LONG).show();
                } else {
                    gps.showSettingsAlert();
                }
            }
        });

        return rootview;




    }



    @Override
    public void onClick(View v) {

    }
}


