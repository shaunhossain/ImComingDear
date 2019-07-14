package com.shaunhossain.imcomingdear.data.view.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources;

import com.shaunhossain.imcomingdear.data.manager.impl.MatchManager;
import com.shaunhossain.imcomingdear.data.models.User;

import java.util.List;

/**
 * Created by adriaboschsaez on 13/02/2018.
 */

public class MatchViewModel extends ViewModel {

    private MatchManager matchManager = new MatchManager();

    private MutableLiveData<List<User>> userListMutable;

    public void setResources(Resources resources)
    {
        matchManager.setResources(resources);
    }

    public MutableLiveData<List<User>> getUserList() {
        if(userListMutable == null) {
            userListMutable = new MutableLiveData<>();
            populateUserList();
        }

        return userListMutable;
    }

    private void populateUserList() {
        List<User> userList = matchManager.getUserList();
        userListMutable.setValue(userList);
    }

}
