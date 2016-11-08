package com.example.saurabhpathak.tiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by saurabhpathak on 08/11/2016.
 */

public class ButtonAdapter extends BaseAdapter {
    ArrayList<String> tileList;
    Context context;

    private static LayoutInflater inflater=null;
    public ButtonAdapter(Context context, ArrayList<String> itemList) {
        this.tileList = itemList;
        this.context = context;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tileList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.tile_layout, null);
        final Button btn = (Button) rowView.findViewById(R.id.tileBtn);
        btn.setText(tileList.get(position));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAnimations.rotateAnimation(btn);
                Toast.makeText(context, "You Clicked " + tileList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }
}
