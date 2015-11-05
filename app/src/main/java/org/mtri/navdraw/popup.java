package org.mtri.navdraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by gmsulliv on 10/26/2015.
 */
public class popup extends android.support.v4.app.Fragment {

    View rootview;
    private service_reciever mReceiver;
    private TextView email;
    private View view;
    ViewGroup parent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.popup, null);
    }

    /*
    private boolean popup(){

        boolean var = false;
        LayoutInflater layoutInflater
                = (LayoutInflater)getActivity()
                .getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup, null);

        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(popupView);
        popupWindow.setWidth(1000);
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        EditText input = (EditText) rootview.findViewById(R.id.email);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        Button btnCancel = (Button)popupView.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
            }

        });

        Button btnEnter = (Button)popupView.findViewById(R.id.enter);
        btnEnter.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText() != null) {
                    // update activityData.owner and displayOwner
                    activityData.owner = input.getText().toString();
                    displayOwner.setText(activityData.owner);
                } else {
                    dialog.cancel();
                }
                activityData.email = input.getText().toString();
                popupWindow.dismiss();
            }

        });

        return var;
    }

    private Dialog onCreateDialog() {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());

        final EditText input = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertdialog.setView(input);

        alertdialog.setMessage("Please enter a valid email address, and we will email you when a data connection is avaiable")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel, go back
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        while ((isValidEmail(activityData.email)) == false) {
                            if (input.getText() != null) {
                                if (isValidEmail(input.getText().toString())) {
                                    activityData.email = input.getText().toString();
                                } else {
                                    email_check();
                                }
                                // update activityData.owner and displayOwner

                                //email.setText(activityData.owner);
                            } else {
                                dialog.cancel();
                            }
                        }
                    }
                });
        alertdialog.show();
        return alertdialog.create();
    }
    private Dialog email_check(){
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());

        final EditText input = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertdialog.setView(input);

        alertdialog.setMessage("This is an invalid email, do you wish to proceed anyway?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel, go back
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertdialog.show();
        return alertdialog.create();
    }*/
}
