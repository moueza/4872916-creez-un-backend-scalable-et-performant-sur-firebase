package com.openclassrooms.ref4872916;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.firebase.ui.auth.AuthUI;
import com.openclassrooms.firebaseOC.ref4872916.R;

import java.util.Collections;
import java.util.List;

/**
 * Base Activity class that allow to manage all the common code for the activities
 * @param <T> Should be the type of the viewBinding of your activity see more <a href="https://developer.android.com/topic/libraries/view-binding"> here </a>
 */
abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {

    abstract T getViewBinding();
    protected T binding;
    private static final int RC_SIGN_IN = 123;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       initBinding();
        setupListeners();

    }

    /**
     * Initialise the binding object and the layout of the activity
     */
    private void initBinding(){
        binding = getViewBinding();
        View view = binding.getRoot();
        setContentView(view);
    }


    private void setupListeners(){
        // Login Button
        binding.loginButton.setOnClickListener(view -> {
            startSignInActivity();
        });
    }

    private void startSignInActivity(){

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers =
                Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());

        // Launch the activity
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                        .setLogo(R.drawable.ic_logo_auth)
                        .build(),
                RC_SIGN_IN);
    }

}
