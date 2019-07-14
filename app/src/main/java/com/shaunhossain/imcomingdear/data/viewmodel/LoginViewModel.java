package com.shaunhossain.imcomingdear.data.viewmodel;

import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.shaunhossain.imcomingdear.data.MyApplication;
import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.manager.LoginManager;
import com.shaunhossain.imcomingdear.data.utils.SingleLiveEvent;
import com.shaunhossain.imcomingdear.data.utils.Utils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by adriaboschsaez on 26/02/2018.
 */

@Singleton
public class LoginViewModel extends AndroidViewModel {

    @Inject
    LoginManager loginManager;

    private SingleLiveEvent<Integer> informationEvent = new SingleLiveEvent<>();

    @Inject
    public LoginViewModel(@NonNull MyApplication application) {
        super(application);

    }


    public void onLogin(String email, String password) {

        int resourceId = checkEmailAndPassword(email, password);

        if (resourceId != 0) {
            informationEvent.setValue(resourceId);
            return;
        }

        loginManager.onLogin(email, password);
    }

    public void onLogin(String email, String password, String confirmPassword) {

        int resourceId = checkEmailAndPassword(email, password);

        if (resourceId != 0) {
            informationEvent.setValue(resourceId);
            return;
        }

        if (!password.equals(confirmPassword)) {
            informationEvent.setValue(R.string.login_fragment_confirmation_password);
            return;
        }

        loginManager.onRegister(email, password);
    }

    private int checkEmailAndPassword(String email, String password) {

        if (email == null || email.isEmpty())
            return R.string.login_fragment_email_empty;

        if (!Utils.isValidEmail(email))
            return R.string.login_fragment_email_invalid;

        if (password == null || password.isEmpty())
            return  R.string.login_fragment_password_empty;

        return 0;
    }

    public SingleLiveEvent<Integer> getInformationEvent() {
        return informationEvent;
    }

}
