package com.tutorials.hp.realmrecyclermulticolumn;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.Toast;

import com.tutorials.hp.realmrecyclermulticolumn.m_Realm.RealmHelper;
import com.tutorials.hp.realmrecyclermulticolumn.m_Realm.Spacecraft;
import com.tutorials.hp.realmrecyclermulticolumn.m_UI.MyAdapter;

import io.realm.Realm;
import io.realm.RealmChangeListener;


public class MainActivity extends AppCompatActivity {

    Realm realm;
    RealmChangeListener realmChangeListener;
    MyAdapter adapter;
    RecyclerView rv;
    EditText nameEditTxt,propEditTxt,descEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //SETUP RV
        rv= (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //SETUP REALM
        realm=Realm.getDefaultInstance();
        final RealmHelper helper=new RealmHelper(realm);

        //RETRIEVE
        helper.retrieveFromDB();

        //SETUP ADAPTER
        adapter=new MyAdapter(this,helper.justRefresh());
        rv.setAdapter(adapter);

        //DATA CHANGE EVENTS AND REFRESH
        realmChangeListener=new RealmChangeListener() {
            @Override
            public void onChange() {

                adapter=new MyAdapter(MainActivity.this,helper.justRefresh());
                rv.setAdapter(adapter);

            }
        };
        //ADD IT TO REALM
        realm.addChangeListener(realmChangeListener);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //DISPLAY INPUT DIALOG
                displayInputDialog();
            }
        });
    }

    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save To Realm");
        d.setContentView(R.layout.input_dialog);

        //EDITTXTS
        nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        propEditTxt= (EditText) d.findViewById(R.id.propellantEditText);
        descEditTxt= (EditText) d.findViewById(R.id.descEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name=nameEditTxt.getText().toString();
                String propellant=propEditTxt.getText().toString();
                String desc=descEditTxt.getText().toString();

                // ADD TO SPACECR
                Spacecraft s=new Spacecraft();
                s.setName(name);
                s.setPropellant(propellant);
                s.setDescription(desc);

                //VALIDATE
                if(name != null && name.length()>0)
                {
                    //SAVE
                    RealmHelper helper=new RealmHelper(realm);
                    if(helper.save(s))
                    {
                        nameEditTxt.setText("");
                        propEditTxt.setText("");
                        descEditTxt.setText("");

                    }else {
                        Toast.makeText(MainActivity.this,"Input is not valid",Toast.LENGTH_SHORT).show();

                    }


                }else {
                    Toast.makeText(MainActivity.this,"Name cannot be empty",Toast.LENGTH_SHORT).show();
                }



            }
        });



        d.show();
    }

    //Release Res


    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}









