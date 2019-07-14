package com.shaunhossain.imcomingdear.data.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.databinding.FragmentLoginBinding;
import com.shaunhossain.imcomingdear.data.viewmodel.LoginViewModel;
import com.shaunhossain.imcomingdear.data.viewmodel.ViewModelFactory;

import java.util.Arrays;

import javax.inject.Inject;

import toothpick.Scope;
import toothpick.Toothpick;

public class LoginFragment extends Fragment {

    public static Fragment newInstance() {

        Fragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    private CallbackManager callbackManager;

    private FragmentLoginBinding binding;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    LoginViewModel loginViewModel;


    private FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            ((LoginActivity) getActivity()).openMainActivity();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException exception) {
            Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Activity owner = getActivity();
        Scope scope = Toothpick.openScopes(owner.getApplication(), owner, this);
        super.onCreate(savedInstanceState);

        Toothpick.inject(this, scope);

        loginViewModel = ViewModelProviders.of(this/*, viewModelFactory*/).get(LoginViewModel.class);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        binding.setHandler(this);

        return binding.getRoot();
    }

    public void onFacebookButtonClick(View v) {
        String[] facebookPermissions = getResources().getStringArray(R.array.facebook_permission_array);
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(facebookPermissions));
    }

    public void onSignInButtonClick(View v) {

        String email = binding.emailEdit.getText().toString();
        String password = binding.passwordEdit.getText().toString();

        loginViewModel.onLogin(email, password);
    }

    public void onRegisterButtonClick(View v) {

        Fragment fragment = RegisterFragment.newInstance();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_right, R.anim.exit_left, R.anim.enter_left, R.anim.exit_right);
        transaction.replace(R.id.fragment, fragment);
        transaction.addToBackStack("tag");
        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}



















