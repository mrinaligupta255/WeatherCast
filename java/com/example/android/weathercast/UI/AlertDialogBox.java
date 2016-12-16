package com.example.android.weathercast.UI;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Dell on 7/7/2016.
 */
public class AlertDialogBox extends DialogFragment{
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Sorry")
                .setMessage("Error!,please try again later!!")
                .setPositiveButton("Ok ", null);
        //Creating dialog box
        AlertDialog dialog = builder.create();
        return dialog;
    }
}