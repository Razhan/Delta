package com.deltastudio.ran.deltalibrary.domain.usecase;

import com.deltastudio.ran.deltalibrary.domain.usecase.callback.OnCompleteCallback;
import com.deltastudio.ran.deltalibrary.domain.usecase.callback.OnErrorCallback;
import com.deltastudio.ran.deltalibrary.domain.usecase.callback.OnSuccessCallback;

import java.lang.reflect.Method;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class UseCase {

    private String methodName;
    private Object[] methodArgs;

    private OnSuccessCallback onSuccessCallback;
    private OnErrorCallback onErrorCallback;
    private OnCompleteCallback onCompleteCallback;

    private CompositeSubscription compositeSubscription;

    public UseCase() {
        compositeSubscription = new CompositeSubscription();
    }

    public UseCase useCaseName(String name) {
        methodName = name;
        return this;
    }

    public UseCase args(Object... args) {
        this.methodArgs = args;
        return this;
    }

    public UseCase onSuccess(OnSuccessCallback onSuccessCallback) {
        if (onSuccessCallback == null) {
            throw new IllegalArgumentException(
                    "OnSuccessCallback is null. You can not invoke it with" + " null callback.");
        }

        this.onSuccessCallback = onSuccessCallback;
        return this;
    }

    public UseCase onComplete(OnCompleteCallback onCompleteCallback) {
//        if (onCompleteCallback == null) {
//            throw new IllegalArgumentException(
//                    "OnSuccessCallback is null. You can not invoke it with" + " null callback.");
//        }

        this.onCompleteCallback = onCompleteCallback;
        return this;
    }

    public UseCase onError(OnErrorCallback errorCallback) {
//        if (errorCallback == null) {
//            throw new IllegalArgumentException(
//                    "The onErrorCallback used is null, you can't use a null instance as onError callback.");
//        }

        this.onErrorCallback = errorCallback;
        return this;
    }

    protected Observable buildUseCaseObservable() {
        Method methodToInvoke = UseCaseFilter.filter(this, methodName, methodArgs);

        try {
            return (Observable) methodToInvoke.invoke(this, methodArgs);
        } catch (Exception e) {
            return Observable.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public void execute() {
        Subscription subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccessCallback, onErrorCallback, onCompleteCallback);

        compositeSubscription.add(subscription);
    }

    public void unsubscribe() {
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

}
