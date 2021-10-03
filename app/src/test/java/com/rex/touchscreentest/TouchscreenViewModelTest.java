package com.rex.touchscreentest;

import com.rex.touchscreentest.listener.OnTouchScreenListener;
import com.rex.touchscreentest.viewmodel.TouchscreenViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import static org.junit.Assert.*;

public class TouchscreenViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Test
    public void getCellWidth() {
        int width = 208;
        int height = 100;
        int expect = 100;

        TouchscreenViewModel touchscreenViewModel = new TouchscreenViewModel(new OnTouchScreenListener() {
            @Override
            public void checkCellOK(int indexY, int indexX) {

            }

            @Override
            public void checkOver() {

            }
        });
        int cellWidth = touchscreenViewModel.getCellWidth(width, height);

        assertEquals(expect, cellWidth);
    }

    @Test
    public void touchScreen() {
        TouchscreenViewModel touchscreenViewModel = new TouchscreenViewModel(new OnTouchScreenListener() {
            @Override
            public void checkCellOK(int indexY, int indexX) {
                assertEquals(indexY, indexY);
            }

            @Override
            public void checkOver() {

            }
        });
        touchscreenViewModel.setCoordinateSize(100, 100);
        touchscreenViewModel.touchScreen(0, 0);
    }
}