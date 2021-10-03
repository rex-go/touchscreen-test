package com.rex.touchscreentest.view;

import android.content.Context;
import android.view.View;

public class CellView extends View {
    public int row;
    public int column;

    public CellView(Context context) {
        super(context);
    }

    public CellView(Context context, int row, int column) {
        super(context);
        this.row = row;
        this.column = column;
    }
}
