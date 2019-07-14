package com.shaunhossain.imcomingdear.data.utils;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by adriaboschsaez on 29/01/2018.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String LOGTAG = "android-fcm";

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(LOGTAG, "Token: " + refreshedToken);
    }
}