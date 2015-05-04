package org.mtri.navdraw;

import android.app.AlertDialog;
import android.app.Dialog;
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
 *  This fragment records updates to activityData.notes
 *                                               .images
 *
 */
public class fragment5 extends android.support.v4.app.Fragment {

    // set layout objects
    private TextView notes;
    private TextView image_paths;

    // make view for fragment
    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootview = inflater.inflate(R.layout.fragment5_layout, container, false);

        // get activityData
        final MainActivity activityData = (MainActivity)getActivity();

        // set layout objects
        notes = (TextView) rootview.findViewById(R.id.display_notes);
        image_paths = (TextView) rootview.findViewById(R.id.display_image_paths);

        // Tie add_notes button to picker dialog and activityData.notes
        Button add_notes_btn = (Button) rootview.findViewById(R.id.add_notes_btn);
        add_notes_btn.setOnClickListener(new View.OnClickListener() {
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

                alertdialog.setMessage(R.string.notes)
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
                                activityData.notes = input.getText().toString();
                                notes.setText(activityData.notes);
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });



        return rootview;
    }

}
