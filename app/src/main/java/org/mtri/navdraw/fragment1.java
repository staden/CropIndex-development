package org.mtri.navdraw;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 *
 *  This class records updates to activityData.year
 *                                            .month
 *                                            .day
 *                                            .latitude
 *                                            .longitude
 *                                            .owner
 *
 *  This class also inflates and updates objects displayed in fragment1_layout.xml
 *
 */
public class fragment1 extends android.support.v4.app.Fragment implements View.OnClickListener{

    // get layout objects
    private TextView displayOwner;
    private TextView displayDate;
    private TextView displayLocation;

    // get GPS Tracker for refresh button
    private GPSTracker gps;

    // make a view for the fragment
    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootview = inflater.inflate(R.layout.fragment1_layout, container, false);

        // get activityData
        final MainActivity activityData = (MainActivity)getActivity();

        // set layout objects
        displayOwner = (TextView) rootview.findViewById(R.id.displayOwner);
        displayDate = (TextView) rootview.findViewById(R.id.displayDate);
        Button changeDate = (Button) rootview.findViewById(R.id.changeDate);
        displayLocation = (TextView) rootview.findViewById(R.id.displayLocation);

        // display activityData
        displayDate.setText(new StringBuilder()
                .append(activityData.month + 1).append(" - ")
                .append(activityData.day).append(" - ")
                .append(activityData.year).append(" "));
        displayOwner.setText(activityData.owner);
        displayLocation.setText(new StringBuffer()
                .append("lat: ").append(activityData.latitude)
                .append(", lng: ").append(activityData.longitude));

        // Tie record_owner button to picker dialog and activityData
        Button btnIDFarm = (Button) rootview.findViewById(R.id.record_owner);

        btnIDFarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onCreateDialog();
            }

            private Dialog onCreateDialog() {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());

                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertdialog.setView(input);

                alertdialog.setMessage(R.string.property_id)
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // cancel, go back
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (input.getText() != null) {
                                    // update activityData.owner and displayOwner
                                    activityData.owner = input.getText().toString();
                                    displayOwner.setText(activityData.owner);
                                } else {
                                    dialog.cancel();
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // Tie changeDate button to displayDate and activityData
        changeDate = (Button) rootview.findViewById(R.id.changeDate);

        changeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onCreateDialog();
            }

            private Dialog onCreateDialog() {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());

                final DatePicker input = new DatePicker(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertdialog.setView(input);

                alertdialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel, go back
                        dialog.cancel();
                    }
                })
                        .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // update activityData.owner and displayOwner
                                activityData.year = input.getYear();
                                activityData.month = input.getMonth();
                                activityData.day = input.getDayOfMonth();
                                displayDate.setText(new StringBuilder()
                                        .append(activityData.month + 1).append(" - ")
                                        .append(activityData.day).append(" - ")
                                        .append(activityData.year).append(" "));
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });


        // Tie show_location button to GPSTracker and activityData
        Button btnShowLocation = (Button) rootview.findViewById(R.id.show_location);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gps = new GPSTracker(getActivity());

                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    activityData.latitude = latitude;
                    activityData.longitude = longitude;

                    displayLocation.setText(new StringBuilder()
                            .append("Latitude: ").append(activityData.latitude)
                            .append(", Longitude: ").append(activityData.longitude).append(" "));

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


