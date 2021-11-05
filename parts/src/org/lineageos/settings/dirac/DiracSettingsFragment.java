/*
 * Copyright (C) 2018 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.dirac;

<<<<<<< HEAD
import android.app.ActionBar;
=======
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
>>>>>>> 36c7c00a (olivewood: parts: Adapt to S style)
import android.os.Bundle;
import android.os.Handler;
import android.widget.Switch;
<<<<<<< HEAD
import android.widget.TextView;

=======
>>>>>>> 36c7c00a (olivewood: parts: Adapt to S style)
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreference;

import org.lineageos.settings.R;

import com.android.settingslib.widget.MainSwitchPreference;
import com.android.settingslib.widget.OnMainSwitchChangeListener;

public class DiracSettingsFragment extends PreferenceFragment implements
<<<<<<< HEAD
        Preference.OnPreferenceChangeListener, CompoundButton.OnCheckedChangeListener {
=======
        OnPreferenceChangeListener, OnMainSwitchChangeListener {

>>>>>>> 36c7c00a (olivewood: parts: Adapt to S style)

    private static final String PREF_HEADSET = "dirac_headset_pref";
    private static final String PREF_PRESET = "dirac_preset_pref";
    private static final String PREF_ENABLE = "dirac_enable";

    private MainSwitchPreference mSwitchBar;

    private ListPreference mHeadsetType;
    private ListPreference mPreset;

    private DiracUtils mDiracUtils;
    private Handler mHandler = new Handler();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.dirac_settings);

        mDiracUtils = new DiracUtils(getContext());

        final ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        boolean enhancerEnabled = mDiracUtils.isDiracEnabled();

        mSwitchBar = (MainSwitchPreference) findPreference(PREF_ENABLE);
        mSwitchBar.addOnSwitchChangeListener(this);
        mSwitchBar.setChecked(enhancerEnabled);

        mHeadsetType = (ListPreference) findPreference(PREF_HEADSET);
        mHeadsetType.setOnPreferenceChangeListener(this);
        mHeadsetType.setEnabled(enhancerEnabled);

        mPreset = (ListPreference) findPreference(PREF_PRESET);
        mPreset.setOnPreferenceChangeListener(this);
        mPreset.setEnabled(enhancerEnabled);
    }

    @Override
<<<<<<< HEAD
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.dirac,
                container, false);
        ((ViewGroup) view).addView(super.onCreateView(inflater, container, savedInstanceState));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        boolean enhancerEnabled = mDiracUtils.isDiracEnabled();

        mTextView = view.findViewById(R.id.switch_text);
        mTextView.setText(getString(enhancerEnabled ?
                R.string.switch_bar_on : R.string.switch_bar_off));

        mSwitchBar = view.findViewById(R.id.switch_bar);
        Switch switchWidget = mSwitchBar.findViewById(android.R.id.switch_widget);
        switchWidget.setChecked(enhancerEnabled);
        switchWidget.setOnCheckedChangeListener(this);
        mSwitchBar.setActivated(enhancerEnabled);
        mSwitchBar.setOnClickListener(v -> {
            switchWidget.setChecked(!switchWidget.isChecked());
            mSwitchBar.setActivated(switchWidget.isChecked());
        });
    }

    @Override
=======
>>>>>>> 36c7c00a (olivewood: parts: Adapt to S style)
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        switch (preference.getKey()) {
            case PREF_HEADSET:
                mDiracUtils.setHeadsetType(Integer.parseInt(newValue.toString()));
                return true;
            case PREF_PRESET:
                mDiracUtils.setLevel(String.valueOf(newValue));
                return true;
            default:
                return false;
        }
    }

    @Override
<<<<<<< HEAD
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        mDiracUtils.setEnabled(isChecked);
        mTextView.setText(getString(isChecked ? R.string.switch_bar_on : R.string.switch_bar_off));
        if (isChecked) {
=======
    public void onSwitchChanged(Switch switchView, boolean isChecked) {
        mSwitchBar.setChecked(isChecked);
        if (isChecked){
>>>>>>> 36c7c00a (olivewood: parts: Adapt to S style)
            mSwitchBar.setEnabled(false);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        mSwitchBar.setEnabled(true);
                        setEnabled(isChecked);
                    } catch(Exception ignored) {
                    }
                }
            }, 1020);
        } else {
            setEnabled(isChecked);
        }
    }

    private void setEnabled(boolean enabled){
        mSwitchBar.setActivated(enabled);
        mHeadsetType.setEnabled(enabled);
        mPreset.setEnabled(enabled);
    }
}
