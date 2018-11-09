package com.example.lenovoz51.oruprograma;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo Z51 on 2018-11-09.
 */

public class ContactAdapter extends BaseAdapter {
    List<Weather> list = new ArrayList<Weather>();
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader=AppControler.getmInstance().getmImageLoader();

    public ContactAdapter(Activity activity, List<Weather> list){
        this.activity=activity;
        this.list=list;
    }

    public int getCount(){
        return list.size();
    }

    public Object getItem (int i){
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View convertView, final ViewGroup parent){
        if(layoutInflater==null){
            layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        convertView = layoutInflater.inflate(R.layout.row_layout,null);
        imageLoader=AppControler.getmInstance().getmImageLoader();
        TextView diena = (TextView) convertView.findViewById(R.id.diena);
        TextView pavadinimas = (TextView)convertView.findViewById((R.id.aprasas));
        Weather receptas = list.get(position);
        diena.setText(receptas.time);
        pavadinimas.setText((CharSequence) receptas.temperature);


        return convertView;
    }


}
