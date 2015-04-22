package org.mtri.navdraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by sam on 3/23/15.
 *
 *  This fragment displays all data to be submitted.
 *
 */


public class fragment7 extends android.support.v4.app.Fragment {
    private TextView year;
    private TextView month;
    private TextView day;
    private TextView latitude;
    private TextView longitude;
    private TextView IDFarm;
    private TextView temperature;
    private TextView rainfall;
    private TextView crop1;
    private TextView excellent;
    private TextView good;
    private TextView fair;
    private TextView poor;
    private TextView vpoor;
    private TextView humus;
    private TextView surplus;
    private TextView adequate;
    private TextView vshort;
    private TextView vvshort;

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootview = inflater.inflate(R.layout.fragment7_layout, container, false);

        // get activityData
        final MainActivity activityData = (MainActivity)getActivity();

        // set layout objects
        year = (TextView) rootview.findViewById(R.id.submit_year);
        month = (TextView) rootview.findViewById(R.id.submit_month);
        day = (TextView) rootview.findViewById(R.id.submit_day);
        latitude = (TextView) rootview.findViewById(R.id.submit_latitude);
        longitude = (TextView) rootview.findViewById(R.id.submit_longitude);
        IDFarm = (TextView) rootview.findViewById(R.id.submit_IDFarm);
        temperature = (TextView) rootview.findViewById(R.id.submit_temperature);
        rainfall = (TextView) rootview.findViewById(R.id.submit_rainfall);
        crop1 = (TextView) rootview.findViewById(R.id.submit_crop1);
        excellent = (TextView) rootview.findViewById(R.id.submit_c1_excellent);
        good = (TextView) rootview.findViewById(R.id.submit_c1_good);
        fair = (TextView) rootview.findViewById(R.id.submit_c1_fair);
        poor = (TextView) rootview.findViewById(R.id.submit_c1_poor);
        vpoor = (TextView) rootview.findViewById(R.id.submit_c1_vpoor);
        humus = (TextView) rootview.findViewById(R.id.submit_humus);
        surplus = (TextView) rootview.findViewById(R.id.submit_surplus);
        adequate = (TextView) rootview.findViewById(R.id.submit_sm_adequate);
        vshort = (TextView) rootview.findViewById(R.id.submit_sm_short);
        vvshort = (TextView) rootview.findViewById(R.id.submit_sm_vshort);

        // display activityData
        year.setText(String.valueOf(activityData.year));
        month.setText(String.valueOf(activityData.month));
        day.setText(String.valueOf(activityData.day));
        latitude.setText(String.valueOf(activityData.latitude));
        longitude.setText(String.valueOf(activityData.latitude));
        IDFarm.setText(activityData.owner);
        temperature.setText(String.valueOf(activityData.temperature));
        rainfall.setText(String.valueOf(activityData.rainfall));
        crop1.setText(activityData.crop_1);
        excellent.setText(String.valueOf(activityData.crop1_excellent));
        good.setText(String.valueOf(activityData.crop1_good));
        fair.setText(String.valueOf(activityData.crop1_fair));
        poor.setText(String.valueOf(activityData.crop1_poor));
        vpoor.setText(String.valueOf(activityData.crop1_vpoor));
        humus.setText(activityData.humus);
        surplus.setText(String.valueOf(activityData.soilmoisture_surplus));
        adequate.setText(String.valueOf(activityData.soilmoisture_adequate));
        vshort.setText(String.valueOf(activityData.soilmoisture_short));
        vvshort.setText(String.valueOf(activityData.soilmoisture_veryshort));

        return rootview;
    }

}
