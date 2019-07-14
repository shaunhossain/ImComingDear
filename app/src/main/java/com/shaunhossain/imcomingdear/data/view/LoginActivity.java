package com.shaunhossain.imcomingdear.data.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.Toast;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.viewmodel.LoginViewModel;

import javax.inject.Inject;

import jp.wasabeef.blurry.Blurry;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

public class LoginActivity extends BaseActivity {

    private Toast toast;

    @Inject
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Scope scope = Toothpick.openScopes(getApplication(), this);
        scope.installModules(new Module() {{
            bind(LoginViewModel.class);
        }});
        super.onCreate(savedInstanceState);

        Toothpick.inject(this, scope);

        setContentView(R.layout.activity_login);

        ImageView imageView = findViewById(R.id.mageview_background);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.login_background_image);

        Blurry.with(this).radius(5).sampling(5).color(Color.argb(128, 255, 0, 128)).from(bitmap).into(imageView);

        loginViewModel.getInformationEvent().observe(this, (Integer resourceId) ->
                showToast(resourceId));

        Fragment fragment = LoginFragment.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();

        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void showToast(int resourceId){
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, getString(resourceId), Toast.LENGTH_SHORT);
        toast.show();
    }

    public void openMainActivity() {

        Intent intent;
        intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

}
