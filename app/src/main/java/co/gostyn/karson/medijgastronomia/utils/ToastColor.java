package co.gostyn.karson.medijgastronomia.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Karol on 2017-06-18.
 */

public class ToastColor { // Toast bez tła z tekstem w podanym kolorze

    public ToastColor(Context context, String txt, int color) {

        Toast toast = Toast.makeText(context, txt, Toast.LENGTH_SHORT);

        View view = toast.getView();
        view.setBackgroundColor(Color.TRANSPARENT);

        TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
        toastMessage.setTextColor(color);
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        toast.show();

    }
}
