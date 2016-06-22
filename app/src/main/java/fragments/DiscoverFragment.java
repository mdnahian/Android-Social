package fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aqurytech.pinetree.R;

/**
 * Created by Md Islam on 6/20/2016.
 */
public class DiscoverFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.discover_fragment, container, false);

        final ImageView listBtn = (ImageView) rootView.findViewById(R.id.listBtn);
        final ImageView mapBtn = (ImageView) rootView.findViewById(R.id.mapBtn);

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listBtn.setBackgroundColor(Color.parseColor("#eeeeee"));
                mapBtn.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mapBtn.setBackgroundColor(Color.parseColor("#eeeeee"));
                listBtn.setBackgroundColor(Color.parseColor("#ffffff"));

            }
        });


        return rootView;
    }
}
