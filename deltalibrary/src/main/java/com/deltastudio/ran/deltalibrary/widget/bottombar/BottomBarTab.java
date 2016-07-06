package com.deltastudio.ran.deltalibrary.widget.bottombar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatDrawableManager;

public class BottomBarTab {

    protected int iconResource;
    protected Drawable icon;
    protected int titleResource;
    protected String title;
    protected int color;

    protected int id = -1;

    protected Drawable getIcon(Context context) {
        if (this.iconResource != 0) {
            return AppCompatDrawableManager.get().getDrawable(context, iconResource);
        } else {
            return this.icon;
        }
    }

    protected String getTitle(Context context) {
        if (this.titleResource != 0) {
            return context.getString(this.titleResource);
        } else {
            return this.title;
        }
    }

    public BottomBarTab(@DrawableRes int iconResource, @NonNull String title) {
        this.iconResource = iconResource;
        this.title = title;
    }

    public BottomBarTab(Drawable icon, @NonNull String title) {
        this.icon = icon;
        this.title = title;
    }

    public BottomBarTab(Drawable icon, @StringRes int titleResource) {
        this.icon = icon;
        this.titleResource = titleResource;
    }

    public BottomBarTab(@DrawableRes int iconResource, @StringRes int titleResource) {
        this.iconResource = iconResource;
        this.titleResource = titleResource;
    }
}
