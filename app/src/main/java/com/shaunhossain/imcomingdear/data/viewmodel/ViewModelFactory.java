package com.shaunhossain.imcomingdear.data.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.shaunhossain.imcomingdear.data.MyApplication;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * Created by adriaboschsaez on 28/02/2018.
 */
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;
    MyApplication application;

    @Inject
    public ViewModelFactory(MyApplication application) {
        creators = new HashMap<>();
        this.application = application;
        LoginViewModel viewModel = new LoginViewModel(application);
        //creators.put(LoginViewModel.class, viewModel);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        /*Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
        return (T) new LoginViewModel(application);
    }
}
/*
@Singleton
public class ProjectViewModelFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public ProjectViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
        creators = new ArrayMap<>();

        // View models cannot be injected directly because they won't be bound to the owner's view model scope.
        creators.put(ProjectViewModel.class, () -> viewModelSubComponent.projectViewModel());
        creators.put(ProjectListViewModel.class, () -> viewModelSubComponent.projectListViewModel());
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

public static class Factory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application mApplication;

    private final int mProductId;

    private final DataRepository mRepository;

    public Factory(@NonNull Application application, int productId) {
        mApplication = application;
        mProductId = productId;
        mRepository = ((BasicApp) application).getRepository();
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ProductViewModel(mApplication, mRepository, mProductId);
    }


*/