package com.deltastudio.ran.deltalibrary.domain.interactor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class UseCase {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected UseCase() {
    }

    protected abstract Observable buildUseCaseObservable(Object... parameter);

    @SuppressWarnings("unchecked")
    public void execute(Subscriber UseCaseSubscriber, Object... parameter) {
        Subscription subscription = this.buildUseCaseObservable(parameter)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(UseCaseSubscriber);

        compositeSubscription.add(subscription);
    }

    public void unsubscribe() {
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

}

