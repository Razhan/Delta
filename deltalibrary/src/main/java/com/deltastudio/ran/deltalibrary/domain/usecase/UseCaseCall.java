package com.deltastudio.ran.deltalibrary.domain.usecase;

import com.deltastudio.ran.deltalibrary.domain.usecase.callback.OnErrorCallback;
import com.deltastudio.ran.deltalibrary.domain.usecase.callback.OnSuccessCallback;

import java.lang.reflect.Method;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class UseCaseCall {

    private String useCaseName;
    private Object[] args;
    private OnSuccessCallback onSuccessCallback;
    private OnErrorCallback onErrorCallback;
    private CompositeSubscription compositeSubscription;

    public UseCaseCall() {
        compositeSubscription = new CompositeSubscription();
    }

    public UseCaseCall useCaseName(String name) {
        useCaseName = name;
        return this;
    }

    public UseCaseCall args(Object... args) {
        this.args = args;
        return this;
    }

    public UseCaseCall onSuccess(OnSuccessCallback onSuccessCallback) {
        if (onSuccessCallback == null) {
            throw new IllegalArgumentException(
                    "OnSuccessCallback is null. You can not invoke it with" + " null callback.");
        }

        this.onSuccessCallback = onSuccessCallback;
        return this;
    }

    public UseCaseCall onError(OnErrorCallback errorCallback) {
        if (errorCallback == null) {
            throw new IllegalArgumentException(
                    "The onErrorCallback used is null, you can't use a null instance as onError callback.");
        }

        this.onErrorCallback = errorCallback;
        return this;
    }

    protected Observable buildUseCaseObservable() {
        Method methodToInvoke = UseCaseFilter.filter(this, useCaseName, args);

        try {
            return (Observable) methodToInvoke.invoke(this, args);
        } catch (Exception e) {
            return Observable.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public void execute() {
        Subscription subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccessCallback, onErrorCallback);

        compositeSubscription.add(subscription);
    }

    public void unsubscribe() {
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

}
