package com.deltastudio.ran.deltalibrary.presentation.delegate;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class MosbySavedState extends View.BaseSavedState {

    public static final Creator<MosbySavedState> CREATOR =
            new Creator<MosbySavedState>() {
                public MosbySavedState createFromParcel(Parcel in) {
                    return new MosbySavedState(in);
                }

                public MosbySavedState[] newArray(int size) {
                    return new MosbySavedState[size];
                }
            };

    private int mosbyViewId = 0;

    public MosbySavedState(Parcelable superState) {
        super(superState);
    }

    protected MosbySavedState(Parcel in) {
        super(in);
        this.mosbyViewId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(mosbyViewId);
    }

    public int getMosbyViewId() {
        return mosbyViewId;
    }

    public void setMosbyViewId(int mosbyViewId) {
        this.mosbyViewId = mosbyViewId;
    }
}
