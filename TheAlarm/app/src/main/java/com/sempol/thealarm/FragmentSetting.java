package com.sempol.thealarm;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;

import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by Firelord on 08/04/2018.
 */

public class FragmentSetting extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SettingFragment settingFragment = new SettingFragment();
        fragmentTransaction.add(R.id.setting, settingFragment,"SETTING_FRAGMENT");
        fragmentTransaction.commit();
    }

    public static class SettingFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting_screen);

        }
    }
}

