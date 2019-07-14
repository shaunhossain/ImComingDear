package com.shaunhossain.imcomingdear.data.view.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources;

import com.shaunhossain.imcomingdear.data.manager.impl.XatManager;
import com.shaunhossain.imcomingdear.data.models.Message;

import java.util.List;

/**
 * Created by adriaboschsaez on 14/02/2018.
 */

public class XatViewModel extends ViewModel {

    private XatManager xatManager = new XatManager();

    private MutableLiveData<List<Message>> messageListMutable;

    public void setResources(Resources resources)
    {
        xatManager.setResources(resources);
    }

    public LiveData<List<Message>> getMessageList() {
        if(messageListMutable == null) {
            messageListMutable = new MutableLiveData<>();
            populateMessageList();
        }

        return messageListMutable;
    }

    private void populateMessageList() {
        List<Message> messageList = xatManager.getMessageList();
        messageListMutable.setValue(messageList);
    }

    public void addMessage(Message message) {

        xatManager.addMessage(message);

    }
}
