/*
 * Copyright (C) 2017 The halogenOS Project
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
package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class CrystalVersionPreferenceController extends BasePreferenceController {

    private static final String PROPERTY_CRYSTAL_VERSION_NUMBER = "ro.crystal.version";
    private static final String PROPERTY_CRYSTAL_VERSION_NAME = "ro.crystal.version.name";

    public CrystalVersionPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        if (!TextUtils.isEmpty(SystemProperties.get(PROPERTY_CRYSTAL_VERSION_NUMBER)) ||
        	!TextUtils.isEmpty(SystemProperties.get(PROPERTY_CRYSTAL_VERSION_NAME))) return AVAILABLE;
        return CONDITIONALLY_UNAVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
    	final String versionNumber = SystemProperties.get(PROPERTY_CRYSTAL_VERSION_NUMBER);
    	final String versionName = SystemProperties.get(PROPERTY_CRYSTAL_VERSION_NAME);
    	if (!TextUtils.isEmpty(versionNumber) && !TextUtils.isEmpty(versionName)) {
        	return versionNumber + " | " + versionName;
        } else {
        	return mContext.getString(R.string.unknown);
        }
    }
}
