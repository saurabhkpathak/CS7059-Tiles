package com.example.saurabhpathak.tiles;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by saurabhpathak on 08/11/2016.
 */

public class ButtonAdapter extends BaseAdapter {
    ArrayList<Tile> tileList;
    Context context;
    int clickCounter;

    private static LayoutInflater inflater=null;
    public ButtonAdapter(Context context, ArrayList<Tile> itemList) {
        this.tileList = itemList;
        this.context = context;
        this.clickCounter = 0;
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
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.tile_layout, null);
        final Button tile = (Button) rowView.findViewById(R.id.tileBtn);

        if (position == getCount() - 1) {
            tile.setVisibility(View.GONE);
        } else {
            tile.setText(tileList.get(position).getVisibleValue());

            // event handler when a tile is clicked
            tile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Chronometer chronometer = (Chronometer) ((Activity)context).findViewById(R.id.chronometer);
                    chronometer.start();
                    clickCounter++;
                    TextView clicks = (TextView) ((Activity)context).findViewById(R.id.clickCount);
                    clicks.setText("Number Of Clicks: " + String.valueOf(clickCounter));
                    CustomAnimations.rotateAnimation(tile);
                    tile.setText(tileList.get(position).getValue());

                    // if this is the first click case
                    if(tileList.get(getCount() - 1).getValue() == null) {
                        tileList.set(getCount() - 1, tileList.get(position));
                        tileList.get(getCount() - 1).setVisibleValue(String.valueOf(position));
                    }
                    // if this is the second click case
                    else {
                        String prevPos = tileList.get(getCount() - 1).getVisibleValue();
                        final int prevInt = Integer.parseInt(prevPos);

                        // if same button is clicked successively
                        if (position == prevInt) {
                            return;
                        }
                        // if second click is made on unlocked tile
                        else if (tileList.get(position).getStatus() == Tile.Status.unlocked) {
                            return;
                        }
                        // if the two clicked tiles match
                        else if (tileList.get(getCount() - 1).getValue() == tile.getText()) {
                            tileList.get(position).setStatus(Tile.Status.unlocked);
                            int pos = Integer.parseInt(tileList.get(getCount() - 1).getVisibleValue());
                            tileList.get(pos).setStatus(Tile.Status.unlocked);
                            tileList.set(getCount() - 1, new Tile(Tile.Status.locked, null, null));
                            tile.setBackgroundColor(context.getResources().getColor(R.color.tile_matched));
                            Button prevBtn = (Button) parent.getChildAt(prevInt).findViewById(R.id.tileBtn);
                            prevBtn.setBackgroundColor(context.getResources().getColor(R.color.tile_matched));
                        }
                        // if the two clicked tiles do not match
                        else {
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tile.setText("TILE");
                                    CustomAnimations.rotateAnimation(tile);
                                    View prev = parent.getChildAt(prevInt);
                                    Button prevBtn = (Button) prev.findViewById(R.id.tileBtn);
                                    prevBtn.setText("TILE");
                                    CustomAnimations.rotateAnimation(prevBtn);
                                }
                            }, 500);
                            tileList.set(getCount() - 1, new Tile(Tile.Status.locked, null, null));
                        }
                    }
                    if (Utils.isListUnlocked(tileList)) {
                        chronometer.stop();
                        TextView tv = (TextView)((Activity)context).findViewById(R.id.tv_winStatus);
                        tv.setText("Game Finished!!!\n Your final Score is:" + null);
                        Log.d("Pathak", "Unlocked");
                    } else {
                        Log.d("Pathak", "Locked");
                    }
                }
            });
        }

        return rowView;
    }
}
