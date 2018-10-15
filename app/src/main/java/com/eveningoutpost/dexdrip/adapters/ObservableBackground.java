package com.eveningoutpost.dexdrip.adapters;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.View;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.Setter;

public final class ObservableBackground extends BaseObservable {
    @Nullable
    @Getter
    @Setter
    private Integer mDrawableResource;
    @Nullable
    @Getter
    @Setter
    private Integer mColorResource;
    @Nullable
    @Getter
    @Setter
    private Integer mColorValue;
    @Nullable
    @Getter
    @Setter
    private Drawable mDrawable;
    @Nullable
    @Getter
    @Setter
    private Bitmap mBitmap;

    private void reset() {
        this.mDrawableResource = null;
        this.mColorResource = null;
        this.mColorValue = null;
        this.mDrawable = null;
    }

    public final void setDrawable(@NotNull Drawable drawable) {
        this.reset();
        this.mDrawable = drawable;
        this.notifyChange();
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        this.reset();
        this.mBitmap = bitmap;
        this.notifyChange();
    }

    public final void clear() {
        this.reset();
        this.notifyChange();
    }

    @BindingAdapter(value = "background")
    public static void setBackground(@NotNull View view, @NotNull ObservableBackground observable) {
        Integer resource;
        if (observable.getMDrawableResource() != null) {
            resource = observable.getMDrawableResource();
            if (resource != null) {
                view.setBackgroundResource(resource);
            }
        } else if (observable.getMColorResource() != null) {
            resource = observable.getMColorResource();
            if (resource != null) {
                final int mcolor = ContextCompat.getColor(view.getContext(), resource);
                view.setBackgroundColor(mcolor);
            }
        } else if (observable.getMColorValue() != null) {
            final Integer colorVal = observable.getMColorValue();
            if (colorVal != null) {
                view.setBackgroundColor(colorVal);
            }
        } else if (observable.getMDrawable() != null) {
            final Drawable drawable = observable.getMDrawable();
            if (drawable != null) {
                view.setBackground(drawable);
            }
        } else if (observable.getMBitmap() != null) {
            Bitmap bitmap = observable.getMBitmap();
            if (bitmap != null) {
                view.setBackground((new BitmapDrawable(view.getContext().getResources(), bitmap)));
            }
        } else {
            view.setBackgroundResource(0);
        }
    }

    public final void setDrawableResource(@DrawableRes int drawableResource) {
        this.reset();
        this.mDrawableResource = drawableResource;
        this.notifyChange();
    }

    public final void setColorResource(@ColorRes int colorResource) {
        this.reset();
        this.mColorResource = colorResource;
        this.notifyChange();
    }

    public final void setColorValue(int colorValue) {
        this.reset();
        this.mColorValue = colorValue;
        this.notifyChange();
    }
}