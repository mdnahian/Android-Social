package core;

import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Md Islam on 5/26/2016.
 */
public class InputFilter {

    public static boolean checkInputsEmpty(ArrayList<EditText> inputs){
        for(EditText editText : inputs){
            if(editText.getText().toString().equals("") || editText.getText().length() == 0){
                return false;
            }
        }

        return true;
    }

}
