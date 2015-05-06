package org.mtri.navdraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sam on 3/23/15.
 *
 *  This class records updates to activityData.rainfall
 *                                               .temperature
 *
 *  This class also inflates and updates objects displayed in fragment2_layout.xml
 *
 */

public class fragment2 extends android.support.v4.app.Fragment {

    // set layout objects
    private TextView displayRainfall;
    private TextView displayTemperature;

    // make view for fragment
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootview = inflater.inflate(R.layout.fragment2_layout, container, false);

        // get activityData
        final MainActivity activityData = (MainActivity)getActivity();

        // set layout objects
        displayRainfall = (TextView) rootview.findViewById(R.id.displayRainfall);
        displayTemperature = (TextView) rootview.findViewById(R.id.displayTemp);

        // display activityData
        displayRainfall.setText(activityData.rainfall);
        displayTemperature.setText(activityData.temperature);

        // Tie record_temperature button to picker dialog and activityData.temperature
        Button record_temperature = (Button) rootview.findViewById(R.id.record_temp);
        record_temperature.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OnCreateDialog();
            }

            private Dialog OnCreateDialog() {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());

                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertdialog.setView(input);

                alertdialog.setMessage(R.string.temperature_field_title)
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
                                    activityData.temperature = input.getText().toString();
                                    displayTemperature.setText(activityData.temperature);
                                } else {
                                    dialog.cancel();
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });


        // Tie record_rainfall button to picker dialog and activityData.rainfall
        Button record_rainfall = (Button) rootview.findViewById(R.id.record_rainfall);
        record_rainfall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OnCreateDialog();
            }

            private Dialog OnCreateDialog() {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());

                final EditText input = new EditText(getActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertdialog.setView(input);

                alertdialog.setMessage(R.string.rainfall_field_title)
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
                                    activityData.rainfall = input.getText().toString();
                                    displayRainfall.setText(activityData.rainfall);
                            } else {
                                    dialog.cancel();
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        return rootview;
    }

}
