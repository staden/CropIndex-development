package org.mtri.navdraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
//import android.location.LocationListener;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationRequest;
import android.os.Handler;
import android.provider.Settings;
import android.net.ConnectivityManager;

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
public class fragment1 extends android.support.v4.app.Fragment implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    // get layout objects
    private TextView displayOwner;
    private TextView displayDate;
    private TextView displayLocation;

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    private GoogleApiClient mGoogleApiClient;

    private LocationRequest mLocationRequest;

    // make a view for the fragment
    View rootview;

    double latitude;
    double longitude;

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

        Button btnShowLocation = (Button) rootview.findViewById(R.id.show_location);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                CheckEnableGPS();

                /*
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        "Wait 10 Seconds to Refresh Location Again", Toast.LENGTH_LONG).show();
                */
                activityData.latitude = latitude;
                activityData.longitude = longitude;

                /*
                mHandler.postDelayed(new Runnable() {
                    public void run() {
                        doStuff();
                    }
                }, 10000);
                */

                displayLocation.setText(new StringBuilder()
                        .append("Latitude: ").append(activityData.latitude)
                        .append(", Longitude: ").append(activityData.longitude).append(" "));

                Toast.makeText(
                        getActivity().getApplicationContext(),
                        "Your Location is -\nLat: " + latitude + "\nLong: "
                                + longitude, Toast.LENGTH_LONG).show();
            }
        });

        return rootview;
    }

    private void CheckEnableGPS(){
        String provider = Settings.Secure.getString(getActivity().getApplicationContext().getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if(provider.contains("gps")){
            Toast.makeText(this.getActivity().getApplicationContext(), "GPS Enabled",
                    Toast.LENGTH_LONG).show();
        }else{
            //GPS Enabled
            Toast.makeText(this.getActivity().getApplicationContext(), "GPS Disabled, the location is not accurate, check to see if you have the GPS enabled",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this.getActivity().getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private Handler mHandler = new Handler();
    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        Toast.makeText(
                this.getActivity().getApplicationContext(),
                "Wait 10 Seconds to Refresh Location", Toast.LENGTH_LONG).show();

        this.getActivity().getApplicationContext();
        ConnectivityManager connMgr = (ConnectivityManager)
                this.getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //GPS Enabled
            Toast.makeText(this.getActivity().getApplicationContext(), "Network Enabled",
                    Toast.LENGTH_LONG).show();
        } else {
            //GPS Enabled
            Toast.makeText(this.getActivity().getApplicationContext(), "Network Disabled",
                    Toast.LENGTH_LONG).show();
        }

        mHandler.postDelayed(new Runnable() {
            public void run() {
                doStuff();
            }
        }, 10000);
    }

    private void doStuff() {
        Toast.makeText(this.getActivity().getApplicationContext(), "Ok, you may refresh your location!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
    @Override
    public void onConnected(Bundle connectionHint) {
        /*
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            latitude = Double.parseDouble(String.valueOf(mLastLocation.getLatitude()));
            longitude = Double.parseDouble(String.valueOf(mLastLocation.getLongitude()));
        } else {
            Toast.makeText(this.getActivity(), R.string.no_location_detected, Toast.LENGTH_LONG).show();
        }
        */
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
       LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Toast.makeText(
                this.getActivity().getApplicationContext(),
                "Connection to Google Play Services Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }


    @Override
    public void onPause() {
        super.onPause();
        //stopLocationUpdates();
    }
    /*
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }
    */
    @Override
    public void onResume(){
        super.onResume();
        //startLocationUpdates();
    }
}

