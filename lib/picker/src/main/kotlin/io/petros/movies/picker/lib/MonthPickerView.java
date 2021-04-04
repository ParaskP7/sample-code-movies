package io.petros.movies.picker.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.ListView;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.petros.movies.lib.picker.R;

@SuppressWarnings("unused")
public class MonthPickerView extends ListView {

    // constants
    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_NUM_DAYS = 4;
    private static final int DEFAULT_NUM_ROWS = 3;
    private static final int MAX_NUM_ROWS = 3;
    private static final int DAY_SEPARATOR_WIDTH = 1;
    // days to display
    private final int _numDays = DEFAULT_NUM_DAYS;
    // layout padding
    private final int _padding;
    private final int _rowHeight;
    // month
    private final String[] _monthNames;
    private final int _monthTextSize;
    private final int _monthHeaderSize;
    private final int _monthSelectedCircleSize;
    private int _numCells = _numDays;
    private int _numRows = DEFAULT_NUM_ROWS;
    private int _width;
    // paints
    private Paint _monthNumberPaint;
    private Paint _monthNumberDisabledPaint;
    private Paint _monthNumberSelectedPaint;
    private int _monthBgSelectedColor;
    private int _monthFontColorNormal;
    private int _monthFontColorSelected;
    private int _monthFontColorDisabled;
    private int _maxMonth, _minMonth;
    private int _selectedMonth = -1;
    // listener
    private OnMonthClickListener _onMonthClickListener;

    public MonthPickerView(@NonNull Context context) {
        this(context, null);
    }

    public MonthPickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.style.PickerViewStyle);
    }

    public MonthPickerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        _monthNames = new DateFormatSymbols(Locale.getDefault()).getShortMonths();

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        _monthTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                16, displayMetrics);
        _monthHeaderSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                16, displayMetrics);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            _monthSelectedCircleSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    43, displayMetrics);
        } else {
            _monthSelectedCircleSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    43, displayMetrics);
        }

        int _rowHeightKey = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                250, displayMetrics);
        _rowHeight = (_rowHeightKey - _monthHeaderSize) / MAX_NUM_ROWS;

        _padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                16, displayMetrics);
    }

    /**
     * Sets up the text and style properties for painting.
     */
    private void initView() {
        _monthNumberSelectedPaint = new Paint();
        _monthNumberSelectedPaint.setAntiAlias(true);
        if (_monthBgSelectedColor != 0)
            _monthNumberSelectedPaint.setColor(_monthBgSelectedColor);
        // _monthNumberSelectedPaint.setAlpha(200);
        _monthNumberSelectedPaint.setTextAlign(Paint.Align.CENTER);
        _monthNumberSelectedPaint.setStyle(Paint.Style.FILL);
        _monthNumberSelectedPaint.setFakeBoldText(true);

        _monthNumberPaint = new Paint();
        _monthNumberPaint.setAntiAlias(true);
        if (_monthFontColorNormal != 0)
            _monthNumberPaint.setColor(_monthFontColorNormal);
        _monthNumberPaint.setTextSize(_monthTextSize);
        _monthNumberPaint.setTextAlign(Paint.Align.CENTER);
        _monthNumberPaint.setStyle(Paint.Style.FILL);
        _monthNumberPaint.setFakeBoldText(false);

        _monthNumberDisabledPaint = new Paint();
        _monthNumberDisabledPaint.setAntiAlias(true);
        if (_monthFontColorDisabled != 0)
            _monthNumberDisabledPaint.setColor(_monthFontColorDisabled);
        _monthNumberDisabledPaint.setTextSize(_monthTextSize);
        _monthNumberDisabledPaint.setTextAlign(Paint.Align.CENTER);
        _monthNumberDisabledPaint.setStyle(Paint.Style.FILL);
        _monthNumberDisabledPaint.setFakeBoldText(false);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        drawDays(canvas);
    }

    /**
     * Draws the month days.
     */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    private void drawDays(@NonNull Canvas canvas) {
        int y = (((_rowHeight + _monthTextSize) / 2) - DAY_SEPARATOR_WIDTH) + _monthHeaderSize;
        int dayWidthHalf = (_width - _padding * 2) / (_numDays * 2);
        int j = 0;
        for (int month = 0; month < _monthNames.length; month++) {
            int x = (2 * j + 1) * dayWidthHalf + _padding;
            if (_selectedMonth == month) {
                canvas.drawCircle(x, y - (_monthTextSize / 3), _monthSelectedCircleSize, _monthNumberSelectedPaint);
                if (_monthFontColorSelected != 0)
                    _monthNumberPaint.setColor(_monthFontColorSelected);
            } else {
                if (_monthFontColorNormal != 0)
                    _monthNumberPaint.setColor(_monthFontColorNormal);
            }

            final Paint paint = (month < _minMonth || month > _maxMonth) ?
                    _monthNumberDisabledPaint : _monthNumberPaint;
            canvas.drawText(_monthNames[month], x, y, paint);
            j++;
            if (j == _numDays) {
                j = 0;
                y += _rowHeight;
            }
        }
    }

    /**
     * Calculates the day that the given x position is in, accounting for week
     * number. Returns the day or -1 if the position wasn't in a day.
     *
     * @param x The x position of the touch event
     * @return The day number, or -1 if the position wasn't in a day
     */
    private int getMonthFromLocation(float x, float y) {
        int dayStart = _padding;
        if (x < dayStart || x > _width - _padding) {
            return -1;
        }
        // Selection is (x - start) / (pixels/day) == (x -s) * day / pixels
        int row = (int) (y - _monthHeaderSize) / _rowHeight;
        int column = (int) ((x - dayStart) * _numDays / (_width - dayStart - _padding));
        int day = column + 1;
        day += row * _numDays;
        if (day < 0 || day > _numCells) {
            return -1;
        }
        // position - 1 to match with Calender.JANUARY and Calender.DECEMBER
        return day - 1;
    }

    /**
     * Called when the user clicks on a day. Handles callbacks to the
     * {@link OnMonthClickListener} if one is set.
     *
     * @param day The day that was clicked
     */
    private void onDayClick(int day) {
        if (_onMonthClickListener != null) {
            _onMonthClickListener.onMonthClick(this, day);
        }
    }

    @SuppressWarnings("ConstantConditions")
    protected void setColors(@NonNull HashMap<String, Integer> colors) {
        if (colors.containsKey("pickerBgSelectedColor"))
            _monthBgSelectedColor = colors.get("pickerBgSelectedColor");
        if (colors.containsKey("pickerFontColorNormal"))
            _monthFontColorNormal = colors.get("pickerFontColorNormal");
        if (colors.containsKey("pickerFontColorSelected"))
            _monthFontColorSelected = colors.get("pickerFontColorSelected");
        if (colors.containsKey("pickerFontColorDisabled"))
            _monthFontColorDisabled = colors.get("pickerFontColorDisabled");
        initView();
    }

    public void setOnMonthClickListener(@NonNull OnMonthClickListener listener) {
        _onMonthClickListener = listener;
    }

    void setMonthParams(int selectedMonth, int minMonth, int maxMonth) {
        _selectedMonth = selectedMonth;
        this._minMonth = minMonth;
        this._maxMonth = maxMonth;
        _numCells = 12;

    }

    public void reuse() {
        _numRows = DEFAULT_NUM_ROWS;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), _rowHeight * _numRows
                + (_monthHeaderSize * 2));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        _width = w;
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int day = getMonthFromLocation(event.getX(), event.getY());
            if (day >= 0) {
                onDayClick(day);
            }
        }
        return true;
    }

    /**
     * Handles callbacks when the user clicks on a time object.
     */
    public interface OnMonthClickListener {
        void onMonthClick(@NonNull MonthPickerView view, int month);
    }

}
