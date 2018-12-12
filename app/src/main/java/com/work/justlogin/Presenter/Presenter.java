package com.work.justlogin.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.work.justlogin.View.LoginActivity;
import com.work.justlogin.model.Response1;
import com.work.justlogin.model.login;
import com.work.justlogin.network.RetrofitAPIInterface;
import com.work.justlogin.network.RetrofitHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {


    private final Pattern atLeastOneNumberPattern = Pattern.compile(".*[0-9].*");
    private final Pattern validEmailAddressRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 2);


    //Creating object for our interface
    @NonNull
    private RetrofitAPIInterface retrofitAPIInterface;

    private Context context;
    private login mlogin;
    private MyView myView;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private LoginActivity.UserLoginTask mAuthTask = null;

    public Presenter(String email, String password, Context context, MyView myView) {
        //Init Retrofit Interface
        retrofitAPIInterface = new RetrofitHelper().getRetrofitAPIInterface();
        mlogin = new login();
        mlogin.setEmail(email);
        mlogin.setPassword(password);
        this.context = context;
        this.myView = myView;
    }

    public boolean isEmailValid(String email) {
        Matcher matcher = this.validEmailAddressRegex.matcher((CharSequence) email);
        return matcher.find();
    }

    public boolean isPasswordValid(String password) {
        if (password != null) {
            if (password.length() < 8) {
                return false;
            } else if (password.length() > 90) {
                return false;
            } else {
                return this.atLeastOneNumberPattern.matcher((CharSequence) password).find();
            }

        }
        return password.length() > 4;
    }

    public boolean PostToApi() {
        final boolean[] success = {false};
        Call<Response1> call = retrofitAPIInterface.insertUser();
        call.enqueue(new Callback<Response1>() {
            @Override
            public void onResponse(Call<Response1> call, Response<Response1> response) {
                myView.updateUi(response.body().getSuccess());
                success[0] = false;
            }

            @Override
            public void onFailure(Call<Response1> call, Throwable t) {
                myView.updateUi(success[0]);
            }
        });

        return success[0];
    }

    public interface MyView {

        void showProgress(final boolean show);

        void updateUi(boolean Status);

    }
}


