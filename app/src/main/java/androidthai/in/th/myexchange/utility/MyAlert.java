package androidthai.in.th.myexchange.utility;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by masterung on 7/1/2018 AD.
 */

public class MyAlert {

    //    Explicit
    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    public void normalDialog(String titleString, String messageString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);

    }


}   // Main Class
