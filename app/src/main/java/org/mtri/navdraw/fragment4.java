package org.mtri.navdraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
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
 *  This fragment records updates to activityData.crop1
 *                                               .crop1_excellent
 *                                               .crop1_good
 *                                               .crop1_fair
 *                                               .crop1_poor
 *                                               .crop1_vpoor
 *
 */

public class fragment4 extends android.support.v4.app.Fragment {

    // set layout objects
    private TextView soilmoisture_surplus;
    private TextView soilmoisture_adequate;
    private TextView soilmoisture_short;
    private TextView soilmoisture_veryshort;
    private TextView humus;

    // make view for fragment
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootview = inflater.inflate(R.layout.fragment4_layout, container, false);

        // get activityData
        final MainActivity activityData = (MainActivity)getActivity();

        // set layout objects
        soilmoisture_surplus = (TextView) rootview.findViewById(R.id.soilmoisture_surplus);
        soilmoisture_adequate = (TextView) rootview.findViewById(R.id.soilmoisture_adequate);
        soilmoisture_short = (TextView) rootview.findViewById(R.id.soilmoisture_short);
        soilmoisture_veryshort = (TextView) rootview.findViewById(R.id.soilmoisture_veryshort);
        humus = (TextView) rootview.findViewById(R.id.displayHumus);

        // display activityData
        humus.setText(activityData.humus);
        soilmoisture_surplus.setText(activityData.soilmoisture_surplus.toString());
        soilmoisture_adequate.setText(activityData.soilmoisture_adequate.toString());
        soilmoisture_short.setText(activityData.soilmoisture_short.toString());
        soilmoisture_veryshort.setText(activityData.soilmoisture_veryshort.toString());

        // Tie record_humus button to picker dialog and activityData
        Button record_humus_none = (Button) rootview.findViewById(R.id.record_humus_none);
        record_humus_none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelected();
            }
            private void onSelected() {
                activityData.humus = "None";
                humus.setText(activityData.humus);
            }
        });
        // Tie record_humus button to picker dialog and activityData
        Button record_humus_low = (Button) rootview.findViewById(R.id.record_humus_low);
        record_humus_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelected();
            }
            private void onSelected() {
                activityData.humus = "Low";
                humus.setText(activityData.humus);
            }
        });
        // Tie record_humus button to picker dialog and activityData
        Button record_humus_moderate = (Button) rootview.findViewById(R.id.record_humus_moderate);
        record_humus_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelected();
            }
            private void onSelected() {
                activityData.humus = "Moderate";
                humus.setText(activityData.humus);
            }
        });
        // Tie record_humus button to picker dialog and activityData
        Button record_humus_high = (Button) rootview.findViewById(R.id.record_humus_high);
        record_humus_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelected();
            }
            private void onSelected() {
                activityData.humus = "High";
                humus.setText(activityData.humus);
            }
        });
        // Tie record_humus button to picker dialog and activityData
        Button record_humus_vhigh = (Button) rootview.findViewById(R.id.record_humus_vhigh);
        record_humus_vhigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelected();
            }
            private void onSelected() {
                activityData.humus = "Very High";
                humus.setText(activityData.humus);
            }
        });


        // Tie record_soilmoisture button to picker dialog and activityData.crop1_excellent
        final Button record_sm_surplus = (Button) rootview.findViewById(R.id.record_sm_surplus);
        record_sm_surplus.setOnClickListener(new View.OnClickListener() {

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
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alertdialog.setView(input);

                alertdialog.setMessage(R.string.surplus)
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
                                // update activityData.owner and displayOwner
                                if (input.getText() != null) {
                                    activityData.soilmoisture_surplus = new Integer(input.getText().toString());
                                    soilmoisture_surplus.setText(activityData.soilmoisture_surplus.toString());
                                } else {
                                    dialog.cancel();
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // Tie record_soilmoisture button to picker dialog and activityData.crop1_excellent
        final Button record_sm_adequate = (Button) rootview.findViewById(R.id.record_sm_adequate);
        record_sm_adequate.setOnClickListener(new View.OnClickListener() {

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
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alertdialog.setView(input);

                alertdialog.setMessage(R.string.adequate)
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
                                // update activityData.owner and displayOwner
                                if (input.getText() != null) {
                                    activityData.soilmoisture_adequate = new Integer(input.getText().toString());
                                    soilmoisture_adequate.setText(activityData.soilmoisture_adequate.toString());
                                } else {
                                    dialog.cancel();
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // Tie record_soilmoisture button to picker dialog and activityData.crop1_excellent
        final Button record_sm_short = (Button) rootview.findViewById(R.id.record_sm_short);
        record_sm_short.setOnClickListener(new View.OnClickListener() {

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
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alertdialog.setView(input);

                alertdialog.setMessage(R.string.vshort)
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
                                // update activityData.owner and displayOwner
                                if (input.getText() != null) {
                                    activityData.soilmoisture_short = new Integer(input.getText().toString());
                                    soilmoisture_short.setText(activityData.soilmoisture_short.toString());
                                } else {
                                    dialog.cancel();
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // Tie record_soilmoisture button to picker dialog and activityData.crop1_excellent
        final Button record_sm_veryshort = (Button) rootview.findViewById(R.id.record_sm_veryshort);
        record_sm_veryshort.setOnClickListener(new View.OnClickListener() {

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
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alertdialog.setView(input);

                alertdialog.setMessage(R.string.vvshort)
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
                                // update activityData.owner and displayOwner
                                if (input.getText() != null) {
                                    activityData.soilmoisture_veryshort= new Integer(input.getText().toString());
                                    soilmoisture_veryshort.setText(activityData.soilmoisture_veryshort.toString());
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
