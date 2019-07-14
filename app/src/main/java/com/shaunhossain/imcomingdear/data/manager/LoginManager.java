package com.shaunhossain.imcomingdear.data.manager;

/**
 * Created by adriaboschsaez on 26/02/2018.
 */

public interface LoginManager {


    void onLogin(String email, String password);

    void onRegister(String email, String password);
}
