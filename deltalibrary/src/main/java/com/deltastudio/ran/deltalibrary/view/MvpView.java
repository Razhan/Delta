package com.deltastudio.ran.deltalibrary.view;

public interface MvpView {

    void showMessage(String msg);

    String getErrorMessage(Throwable e);
}
