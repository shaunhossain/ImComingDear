package com.shaunhossain.imcomingdear.data.view;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.databinding.FragmentRegisterBinding;
import com.shaunhossain.imcomingdear.data.viewmodel.LoginViewModel;

/**
 * Created by adriaboschsaez on 18/02/2018.
 */

public class RegisterFragment extends Fragment {

    public static Fragment newInstance() {

        Fragment registerFragment = new RegisterFragment();
        return registerFragment;
    }

    private LoginViewModel loginViewModel;

    private FragmentRegisterBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.setHandler(this);

        return binding.getRoot();
    }

    private void initializeUI(View view) {

        Button signInButton = view.findViewById(R.id.login_button);

        signInButton.setOnClickListener((v) -> {

            Intent intent;
            intent = new Intent(getActivity(), MainActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivity(intent);
            getActivity().finish();
        });
    }

    public void onLoginButtonClick(View v) {

        String email = binding.emailEdit.getText().toString();
        String password = binding.passwordEdit.getText().toString();
        String confirmPassword = binding.confirmEdit.getText().toString();

        loginViewModel.onLogin(email, password, confirmPassword);
    }


}
