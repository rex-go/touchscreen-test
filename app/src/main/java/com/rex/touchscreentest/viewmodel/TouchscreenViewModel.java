package com.rex.touchscreentest.viewmodel;

import android.util.Size;

import com.rex.touchscreentest.Utils;
import com.rex.touchscreentest.listener.OnTouchScreenListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TouchscreenViewModel extends ViewModel {
    private static final int MIN_SQUARE_SIZE = 100;

    private MutableLiveData<Size> size = new MutableLiveData<>();
    private boolean[][] flags;
    private int cellCount;
    private int counter;

    private OnTouchScreenListener onTouchScreenListener;

    public TouchscreenViewModel(@NonNull OnTouchScreenListener onTouchScreenListener) {
        this.counter = 0;
        this.flags = new boolean[1][1];
        this.onTouchScreenListener = onTouchScreenListener;
    }

    public void setCoordinateSize(int rowCount, int columnCount) {
        this.size.setValue(new Size(rowCount, columnCount));
        this.flags = new boolean[rowCount][columnCount];
        this.cellCount = rowCount * columnCount;
    }

    public int getCellWidth(int width, int height) {
        int gcd = Utils.getGCD(width, height);
        if (gcd > MIN_SQUARE_SIZE) {
            for (int i = MIN_SQUARE_SIZE+1; i <= gcd; i++) {
                if (gcd%i == 0)
                    return i;
            }
        }
        return MIN_SQUARE_SIZE;
    }

    public MutableLiveData<Size> getCoordinateSize() {
        return this.size;
    }

    public void touchScreen(int indexY, int indexX) {
        try {
            if (!flags[indexY][indexX]) {
                flags[indexY][indexX] = true;
                ++counter;
                onTouchScreenListener.checkCellOK(indexY, indexX);
            }

            if (counter == cellCount)
                onTouchScreenListener.checkOver();
        } catch (IndexOutOfBoundsException ignore) {
        }
    }
}
