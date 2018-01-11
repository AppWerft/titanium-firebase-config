/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2017 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package firebase.config;

import java.util.ArrayList;
import java.util.List;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.TiApplication;
import android.support.annotation.NonNull;
import android.app.Activity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

@Kroll.module(name = "Firebaseconfig", id = "firebase.config")
public class FirebaseconfigModule extends KrollModule {

	// Standard Debugging variables
	private static final String LCAT = "FBconfig";
	private static final boolean DBG = TiConfig.LOGD;
	private static FirebaseRemoteConfig firebaseRemoteConfig;
	private KrollFunction onLoad = null;
	
	// You can define constants with @Kroll.constant, for example:
	@Kroll.constant
	public static final int FETCH_STATUS_NO_FETCH_YET = 0;
	@Kroll.constant
	public static final int FETCH_STATUS_SUCCESS = 1;
	@Kroll.constant
	public static final int FETCH_STATUS_FAILURE = 2;
	@Kroll.constant
	public static final int FETCH_STATUS_THROTTLED = 3;
	@Kroll.constant
	public static final int SOURCE_REMOTE = 10;
	@Kroll.constant
	public static final int SOURCE_DEFAULT = 11;
	@Kroll.constant
	public static final int SOURCE_STATIC = 12;

	public FirebaseconfigModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
		firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
		
	}

	@Kroll.method
	public void setDeveloperModeEnabled() {
		FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
        .setDeveloperModeEnabled(BuildConfig.DEBUG)
        .build();
firebaseRemoteConfig.setConfigSettings(configSettings);
	}
	
	@Kroll.method
	public void activateFetched() {
		firebaseRemoteConfig.activateFetched();
	}
	@Kroll.method
	public void fetch(KrollDict props) {
		
		int expirationDuration = 0;
		if (props.containsKeyAndNotNull("load")) {
			onLoad = (KrollFunction)props.get("load");
		}
		if (props.containsKeyAndNotNull("expirationDuration")) {
			expirationDuration = props.getInt("expirationDuration");
		}
		Activity activity = TiApplication.getAppRootOrCurrentActivity();
		firebaseRemoteConfig.fetch().addOnCompleteListener(activity, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful() && onLoad !=  null) {
                    onLoad.callAsync(getKrollObject(), new KrollDict());
                    firebaseRemoteConfig.activateFetched();
                } else {
                   
                }
               
            }
        });
	}

	@Kroll.method
	public KrollDict objectForKeyedSubscript(String keyedSubscript) {
		KrollDict dict = new KrollDict();
		return dict;

	}

	@Kroll.method
	public KrollDict configValueForKey(String key,
			@Kroll.argument(optional = true) String nameSpace) {
		KrollDict dict = new KrollDict();
		return dict;
	}

	@Kroll.method
	public Object[] allKeysFromSource(String key,
			@Kroll.argument(optional = true) String nameSpace) {
		List<String> list = new ArrayList();
		return list.toArray();
	}

	@Kroll.method
	public Object[] keysWithPrefix(String prefix,
			@Kroll.argument(optional = true) String nameSpace) {
		List<String> list = new ArrayList();
		return list.toArray();
	}

	public void setDefaults(KrollDict defaults,
			@Kroll.argument(optional = true) String nameSpace) {
		firebaseRemoteConfig.setDefaults(defaults);
	}

	public KrollDict defaultValueForKey(String key) {
		KrollDict dict = new KrollDict();
		return dict;
	}
}