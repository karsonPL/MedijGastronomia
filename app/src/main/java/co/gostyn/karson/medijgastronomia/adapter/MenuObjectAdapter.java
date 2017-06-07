package co.gostyn.karson.medijgastronomia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import co.gostyn.karson.medijgastronomia.R;
import co.gostyn.karson.medijgastronomia.objects.MenuObject;

public class MenuObjectAdapter extends ArrayAdapter<MenuObject> {

    List<MenuObject> modelList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MenuObjectAdapter(Context context, List<MenuObject> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public MenuObject getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MenuObject item = getItem(position);

//        vh.tvNazwa.setText(item.getNazwa());
//        vh.tvOpis1.setText(item.getOpis1());
//        vh.tvOpis2.setText(item.getOpis2());
//        vh.tvIlosc.setText(item.getIlosc());
//        vh.tvCena.setText(item.getCena());
        // Picasso.with(context).load(item.getImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);

        return vh.rootView;
    }


    /**
     * ViewHolder class for layout.<br />
     * <br />
     * Auto-created on 2016-01-05 00:50:26 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final TextView tvNazwa;
        public final TextView tvOpis1;
        public final TextView tvOpis2;
        public final TextView tvIlosc;
        public final TextView tvCena;

        public ViewHolder(RelativeLayout rootView, TextView tvNazwa, TextView tvOpis1, TextView tvOpis2, TextView tvIlosc, TextView tvCena) {
            this.rootView = rootView;
            this.tvNazwa = tvNazwa;
            this.tvOpis1 = tvOpis1;
            this.tvOpis2 = tvOpis2;
            this.tvIlosc = tvIlosc;
            this.tvCena = tvCena;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView tvNazwa = (TextView) rootView.findViewById(R.id.tvNazwa);
            TextView tvOpis1 = (TextView) rootView.findViewById(R.id.tvOpis1);
            TextView tvOpis2 = (TextView) rootView.findViewById(R.id.tvOpis2);
            TextView tvIlosc = (TextView) rootView.findViewById(R.id.tvIlosc);
            TextView tvCena = (TextView) rootView.findViewById(R.id.tvCena);
            return new ViewHolder(rootView, tvNazwa, tvOpis1, tvOpis2, tvIlosc, tvCena);
        }
    }


}
