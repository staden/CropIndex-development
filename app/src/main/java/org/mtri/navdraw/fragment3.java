package org.mtri.navdraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
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
public class fragment3 extends android.support.v4.app.Fragment {

    // set layout objects
    private TextView crop_1;
    private TextView crop1_excellent;
    private TextView crop1_good;
    private TextView crop1_fair;
    private TextView crop1_poor;
    private TextView crop1_vpoor;

    // make view for fragment
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootview = inflater.inflate(R.layout.fragment3_layout, container, false);

        // get activityData
        final MainActivity activityData = (MainActivity)getActivity();

        // set layout objects
        crop_1 = (TextView) rootview.findViewById(R.id.crop_1);
        crop1_excellent = (TextView) rootview.findViewById(R.id.crop1_excellent);
        crop1_good = (TextView) rootview.findViewById(R.id.crop1_good);
        crop1_fair = (TextView) rootview.findViewById(R.id.crop1_fair);
        crop1_poor = (TextView) rootview.findViewById(R.id.crop1_poor);
        crop1_vpoor = (TextView) rootview.findViewById(R.id.crop1_vpoor);

        // display activityData
        crop_1.setText(activityData.crop_1);
        crop1_excellent.setText(activityData.crop1_excellent.toString());
        crop1_good.setText(activityData.crop1_good.toString());
        crop1_fair.setText(activityData.crop1_fair.toString());
        crop1_poor.setText(activityData.crop1_poor.toString());
        crop1_vpoor.setText(activityData.crop1_vpoor.toString());

        // Tie record_crop1 button to picker dialog and activityData.crop_1
        Button record_crop1 = (Button) rootview.findViewById(R.id.record_crop1);
        record_crop1.setOnClickListener(new View.OnClickListener() {

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

                alertdialog.setMessage(R.string.crop1_field_title)
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
                                activityData.crop_1 = input.getText().toString();
                                crop_1.setText(activityData.crop_1);
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // Tie record_c1_excellent button to picker dialog and activityData.crop1_excellent
        final Button record_c1_excellent = (Button) rootview.findViewById(R.id.record_c1_excellent);
        record_c1_excellent.setOnClickListener(new View.OnClickListener() {

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

                alertdialog.setMessage(R.string.crop1_field_title)
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
                                if (input.getText().toString().length() != 0) {
                                    activityData.crop1_excellent = Integer.valueOf(input.getText().toString());
                                    crop1_excellent.setText(activityData.crop1_excellent.toString());
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // Tie record_c1_good button to picker dialog and activityData.crop_1
        final Button record_c1_good = (Button) rootview.findViewById(R.id.record_c1_good);
        record_c1_good.setOnClickListener(new View.OnClickListener() {

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

                alertdialog.setMessage(R.string.crop1_field_title)
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
                                if (input.getText().toString().length() != 0) {
                                    activityData.crop1_good = Integer.valueOf(input.getText().toString());
                                    crop1_good.setText(activityData.crop1_good.toString());
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // Tie record_c1_fair button to picker dialog and activityData.crop1_fair
        final Button record_c1_fair = (Button) rootview.findViewById(R.id.record_c1_fair);
        record_c1_fair.setOnClickListener(new View.OnClickListener() {

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

                alertdialog.setMessage(R.string.crop1_field_title)
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
                                if (input.getText().toString().length() != 0) {
                                    activityData.crop1_fair = Integer.valueOf(input.getText().toString());
                                    crop1_fair.setText(activityData.crop1_fair.toString());
                                } else {
                                    dialog.cancel();
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // Tie record_c1_poor button to picker dialog and activityData.crop1_poor
        final Button record_c1_poor = (Button) rootview.findViewById(R.id.record_c1_poor);
        record_c1_poor.setOnClickListener(new View.OnClickListener() {

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

                alertdialog.setMessage(R.string.crop1_field_title)
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
                                if (input.getText().toString().length() != 0) {
                                    // update activityData.owner and displayOwner
                                    activityData.crop1_poor = Integer.valueOf(input.getText().toString());
                                    crop1_poor.setText(activityData.crop1_poor.toString());
                                } else {
                                    dialog.cancel();
                                }
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // Tie record_c1_vpoor button to picker dialog and activityData.crop1_vpoor
        final Button record_c1_vpoor = (Button) rootview.findViewById(R.id.record_c1_vpoor);
        record_c1_vpoor.setOnClickListener(new View.OnClickListener() {

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

                alertdialog.setMessage(R.string.crop1_field_title)
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
                                if (input.getText().toString().length() != 0) {
                                    // update activityData.owner and displayOwner
                                    activityData.crop1_vpoor = Integer.valueOf(input.getText().toString());
                                    crop1_vpoor.setText(activityData.crop1_vpoor.toString());
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
