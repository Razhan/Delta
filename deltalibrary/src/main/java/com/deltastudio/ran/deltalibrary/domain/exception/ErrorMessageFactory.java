package com.deltastudio.ran.deltalibrary.domain.exception;

import android.content.Context;

public interface ErrorMessageFactory {

  String create(Context context, Exception exception);

}
