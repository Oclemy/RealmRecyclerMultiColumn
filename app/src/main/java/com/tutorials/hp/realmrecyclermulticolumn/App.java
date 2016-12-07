package com.tutorials.hp.realmrecyclermulticolumn;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Oclemy on 6/15/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //SET UP REALM
        RealmConfiguration config=new RealmConfiguration.Builder(this).schemaVersion(3).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }
}
