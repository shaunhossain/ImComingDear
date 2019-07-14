package com.shaunhossain.imcomingdear.data.view.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources;

import com.shaunhossain.imcomingdear.data.manager.impl.UserManager;
import com.shaunhossain.imcomingdear.data.models.User;

import java.util.List;

/**
 * Created by adriaboschsaez on 08/02/2018.
 */

public class UserViewModel extends ViewModel {

    private UserManager userManager = new UserManager();

    private MutableLiveData<List<User>> userListMutable;

    public void setResources(Resources resources)
    {
        userManager.setResources(resources);
    }

    public MutableLiveData<List<User>> getUserList() {
        if(userListMutable == null) {
            userListMutable = new MutableLiveData<>();
            populateUserList();
        }

        return userListMutable;
    }

    private void populateUserList() {
        List<User> userList = userManager.getUsersList();
        userListMutable.setValue(userList);
    }

    public void requestData() {
        populateUserList();
    }

}