package co.gostyn.karson.medijgastronomia;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.gostyn.karson.medijgastronomia.adapter.MenuObjectAdapter;
import co.gostyn.karson.medijgastronomia.objects.Keys;
import co.gostyn.karson.medijgastronomia.objects.MenuObject;
import co.gostyn.karson.medijgastronomia.parser.JSONParser;


/**
 * Created by Karol on 2017-06-06.
 */

public class MenuFragment extends Fragment {

    private ArrayList<MenuObject> list;
    private String data;
    private MenuObjectAdapter adapter2;

   // @Bind(R.id.tvMenuFragment)
    TextView tvMenuFragment;
    @Bind(R.id.listView)
    ListView listView;
    MenuObject abc;

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


        list = new ArrayList<>();



        adapter2 = new MenuObjectAdapter(container.getContext(), list);
        listView.setAdapter(adapter2);
      //  new GetDataTask2().execute();





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
