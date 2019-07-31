package com.codephillip.fcmhelloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logFirebaseToken();
    }

    private void logFirebaseToken() {
        //todo remove this on launch. Used to get device token for testing
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        Log.d(TAG, "onComplete: Firebase token capture started");
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        String token = task.getResult().getToken();
                        String msg = MainActivity.this.getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                    }
                });
    }
}
