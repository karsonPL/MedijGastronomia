package co.gostyn.karson.medijgastronomia;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Karol on 2017-06-06.
 */

public class MenuFragment extends Fragment {

    @Bind(R.id.menu_data)
    TextView sectionLabel;
    @Bind(R.id.web1)
    WebView web1;

    public static final String TAG = "TAG_KARSON";

    // @Bind(R.id.tvMenuFragment)




    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //tvMenuFragment.setText("ddf");


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
