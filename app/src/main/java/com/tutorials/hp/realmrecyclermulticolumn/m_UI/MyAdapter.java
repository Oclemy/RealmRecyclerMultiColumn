package com.tutorials.hp.realmrecyclermulticolumn.m_UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutorials.hp.realmrecyclermulticolumn.R;
import com.tutorials.hp.realmrecyclermulticolumn.m_Realm.Spacecraft;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/15/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public MyAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Spacecraft s=spacecrafts.get(position);

        holder.nameTxt.setText(s.getName());
        holder.propTxt.setText(s.getPropellant());
        holder.descTxt.setText(s.getDescription());

    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }
}
