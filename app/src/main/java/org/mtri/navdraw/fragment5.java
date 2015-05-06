package org.mtri.navdraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sam on 3/23/15.
 *
 *  This class records updates to activityData.notes
 *                                               .images
 *
 *  This class also inflates and updates objects displayed in fragment5_layout.xml
 *
 */
public class fragment5 extends android.support.v4.app.Fragment {

    // set layout objects
    private ImageView display_image;
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
        image_paths.setText(activityData.images);
        display_image = (ImageView) rootview.findViewById(R.id.display_image1);
        File imgfile = new File(Environment.getExternalStorageDirectory()+"/"+activityData.images);
        if (imgfile.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(imgfile.getAbsolutePath());
            display_image.setImageBitmap(bm);
        }

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

        // tie delete_image_btn to activityData.images
        Button delete_image_btn = (Button) rootview.findViewById(R.id.delete_image_btn);
        delete_image_btn.setOnClickListener(new View.OnClickListener() {
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

                alertdialog.setMessage(R.string.image_path)
                        .setNegativeButton(R.string.clear, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activityData.images = "";
                                image_paths.setText(activityData.images);
                                display_image.setImageBitmap(null);
                            }
                        })
                        .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // update activityData.owner and displayOwner
                                activityData.images = input.getText().toString();
                                image_paths.setText(activityData.images);
                                File image_file = new File(Environment.getExternalStorageDirectory(),activityData.images);
                                Bitmap bp = BitmapFactory.decodeFile(image_file.getAbsolutePath());
                                display_image.setImageBitmap(bp);
                            }
                        });
                alertdialog.show();
                return alertdialog.create();
            }
        });

        // tie launch_camera_btn to camera
        Button launch_camera_btn = (Button) rootview.findViewById(R.id.launch_camera_btn);
        launch_camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });

        return rootview;
    }

    /**
     *  launch the camera
     */
    public void launchCamera(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    /**
     *  Save new image; update fragment5 objects and activityData
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            super.onActivityResult(requestCode, resultCode, data);

            Bitmap bp = (Bitmap) data.getExtras().get("data");
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String new_image_filename = "CI_image" + timeStamp + ".jpg";

            File storageDir = Environment.getExternalStorageDirectory();
            File file = new File(storageDir + "/" + new_image_filename);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                Toast.makeText(getActivity(), new_image_filename + " saved to " + storageDir.toString(), Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "image not saved", Toast.LENGTH_LONG).show();
            }

            // update fragment5 objects
            image_paths.setText(new_image_filename);
            display_image.setImageBitmap(bp);

            // get activityData
            final MainActivity activityData = (MainActivity) getActivity();
            // update activityData.images if images were selected
            if (image_paths.getText() != null) {
                activityData.images = image_paths.getText().toString();
            }
        }

    }

}
