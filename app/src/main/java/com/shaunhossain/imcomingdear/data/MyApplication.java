package com.shaunhossain.imcomingdear.data;

import android.app.Application;

import com.shaunhossain.imcomingdear.data.manager.LoginManager;
import com.shaunhossain.imcomingdear.data.manager.impl.LoginManagerImpl;

import javax.inject.Singleton;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

/**
 * Created by adriaboschsaez on 22/02/2018.
 */

@Singleton
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Scope applicationScope = Toothpick.openScope(this);
        installToothPickModules(applicationScope);
    }

    public void installToothPickModules(Scope scope) {
        scope.installModules(new Module() {{
            bind(LoginManager.class).to(LoginManagerImpl.class);
        }});
    }

}
