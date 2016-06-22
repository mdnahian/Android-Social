package activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aqurytech.pinetree.R;

/**
 * Created by Md Islam on 5/27/2016.
 */
public class ParentActivity extends Activity {

    public AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public AlertDialog buildDialog(String title, String message, String button, View.OnClickListener onClickListener){
        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);

        TextView t = (TextView) dialogView.findViewById(R.id.title);
        t.setText(title);

        TextView m = (TextView) dialogView.findViewById(R.id.description);
        m.setText(message);

        TextView b = (TextView) dialogView.findViewById(R.id.positiveBtn);
        b.setText(button);

        b.setOnClickListener(onClickListener);

        return new AlertDialog.Builder(ParentActivity.this).setView(dialogView).create();
    }





}
