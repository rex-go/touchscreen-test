package com.rex.touchscreentest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Size;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rex.touchscreentest.R;
import com.rex.touchscreentest.databinding.ActivityTouchscreenBinding;
import com.rex.touchscreentest.listener.OnTouchScreenListener;
import com.rex.touchscreentest.viewmodel.TouchscreenViewModel;

public class TouchscreenActivity extends AppCompatActivity implements OnTouchScreenListener {
    private static final String CELL_COLOR = "#00FFFF";

    private ActivityTouchscreenBinding binding;
    private View [][] cells;
    private int cellWidth;
    private int cellColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_touchscreen);
        TouchscreenViewModel touchscreenViewModel = new TouchscreenViewModel(this);
        binding.setTouchscreenViewModel(touchscreenViewModel);
        binding.setLifecycleOwner(this);
        cellColor = Color.parseColor(CELL_COLOR);

        fillGridLayout();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE ||
                ev.getAction() == MotionEvent.ACTION_DOWN) {
            binding.getTouchscreenViewModel().touchScreen(
                    (int)ev.getY()/cellWidth, (int)ev.getX()/cellWidth);

        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void checkCellOK(int indexY, int indexX) {
        try {
            cells[indexY][indexX].setBackgroundColor(cellColor);
        } catch (IndexOutOfBoundsException ignore) {
        }
    }

    @Override
    public void checkOver() {
        Toast.makeText(TouchscreenActivity.this,
                R.string.toast_test_passed, Toast.LENGTH_SHORT).show();
        TouchscreenActivity.this.finish();
    }

    private void fillGridLayout() {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        cellWidth = binding.getTouchscreenViewModel().getCellWidth(point.x, point.y);
        int rowCount = point.y/cellWidth;
        int columnCount = point.x/cellWidth;
        cells = new View[rowCount][columnCount];

        binding.getTouchscreenViewModel().setCoordinateSize(rowCount, columnCount);
        binding.getTouchscreenViewModel().getCoordinateSize().observe(this, new Observer<Size>() {
            @Override
            public void onChanged(Size size) {
                addCells(size);
            }
        });
    }

    private void addCells(Size size) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.width = cellWidth;
        layoutParams.height = cellWidth;
        binding.gridLayout.setRowCount(size.getWidth());
        binding.gridLayout.setColumnCount(size.getHeight());
        for (int i = 0; i < size.getWidth(); i++) {
            for (int j = 0; j < size.getHeight(); j++) {
                cells[i][j] = new View(getBaseContext());
                cells[i][j].setLayoutParams(layoutParams);
                cells[i][j].setBackgroundResource(R.drawable.shape_cell);
                binding.gridLayout.addView(cells[i][j]);
            }
        }
    }
}
