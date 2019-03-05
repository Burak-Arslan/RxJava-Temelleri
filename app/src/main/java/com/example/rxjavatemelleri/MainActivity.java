package com.example.rxjavatemelleri;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Button btnHelloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        Events();
    }

    private void Events() {
        try {

            btnHelloWorld.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("CheckResult")
                @Override
                public void onClick(View v) {
                    try {
                        ArrayList animals = new ArrayList();
                        animals.add("J");
                        animals.add("A");
                        animals.add("V");
                        animals.add("A");

//                        Observable = Verinin yayınlandığı yer
//                        Observer = Verinin dinlendiği yer

                        Observable.just(animals)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new Observer<ArrayList>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {
                                        Toast.makeText(getApplicationContext(), "onSubscribe", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onNext(ArrayList arrayList) {
                                        Toast.makeText(getApplicationContext(), "onNext", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onComplete() {
                                        Toast.makeText(getApplicationContext(), "onComplete", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void Init() {
        try {
            btnHelloWorld = findViewById(R.id.btnHelloWorld);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
