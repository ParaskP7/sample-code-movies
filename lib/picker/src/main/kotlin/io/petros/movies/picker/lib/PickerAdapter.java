package io.petros.movies.picker.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.Calendar;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import io.petros.movies.lib.picker.R;
import timber.log.Timber;

@SuppressLint("SyntheticAccessor")
public class PickerAdapter extends BaseAdapter {

    private final Context _context;
    private int _minMonth;
    private int _maxMonth;
    private int _activatedMonth;
    private HashMap<String, Integer> _colors;
    private OnDaySelectedListener mOnDaySelectedListener;
    private final MonthPickerView.OnMonthClickListener mOnDayClickListener = new MonthPickerView.OnMonthClickListener() {
        @Override
        public void onMonthClick(@NonNull MonthPickerView view, int day) {
            Timber.d("onDayClick %s", day);
            if (isCalendarInRange(day)) {
                Timber.d("day not null && Calender in range %s", day);
                setSelectedMonth(day);
                if (mOnDaySelectedListener != null) {
                    mOnDaySelectedListener.onDaySelected(PickerAdapter.this, day);
                }
            }
        }
    };

    public PickerAdapter(@NonNull Context context) {
        this._context = context;
        setRange();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    @Nullable
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup viewGroup) {
        final MonthPickerView v;
        if (convertView != null) {
            v = (MonthPickerView) convertView;
        } else {
            v = new MonthPickerView(_context);
            v.setColors(_colors);

            // Set up the new view
            final AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
            v.setLayoutParams(params);
            v.setClickable(true);
            v.setOnMonthClickListener(mOnDayClickListener);
        }
        // If we're running on Honeycomb or newer, then we can use the Theme's
        // selectableItemBackground to ensure that the View has a pressed state
        v.setBackground(AppCompatResources.getDrawable(_context, R.drawable.month_ripplr));
        v.setMonthParams(_activatedMonth, _minMonth, _maxMonth);
        v.reuse();
        v.invalidate();
        return v;
    }

    boolean isCalendarInRange(int value) {
        return value >= _minMonth && value <= _maxMonth;
    }

    /**
     * Updates the selected day and related parameters.
     *
     * @param month The day to highlight
     */
    public void setSelectedMonth(int month) {
        Timber.d("setSelectedMonth : %s", month);
        _activatedMonth = month;
        notifyDataSetChanged();
    }

    /* set min and max date and years*/
    public void setRange() {

        _minMonth = Calendar.JANUARY;
        _maxMonth = Calendar.DECEMBER;
        _activatedMonth = Calendar.AUGUST;
        notifyDataSetInvalidated();
    }

    /**
     * Sets the listener to call when the user selects a day.
     *
     * @param listener The listener to call.
     */
    public void setOnDaySelectedListener(@NonNull OnDaySelectedListener listener) {
        mOnDaySelectedListener = listener;
    }

    void setMaxMonth(int maxMonth) {
        if (maxMonth <= Calendar.DECEMBER && maxMonth >= Calendar.JANUARY) {
            _maxMonth = maxMonth;
        } else {
            throw new IllegalArgumentException("Month out of range please send months between Calendar.JANUARY, Calendar.DECEMBER");
        }
    }

    void setMinMonth(int minMonth) {
        if (minMonth >= Calendar.JANUARY && minMonth <= Calendar.DECEMBER) {
            _minMonth = minMonth;
        } else {
            throw new IllegalArgumentException("Month out of range please send months between Calendar.JANUARY, Calendar.DECEMBER");
        }
    }

    void setActivatedMonth(int activatedMonth) {
        if (activatedMonth >= Calendar.JANUARY && activatedMonth <= Calendar.DECEMBER) {
            _activatedMonth = activatedMonth;
        } else {
            throw new IllegalArgumentException("Month out of range please send months between Calendar.JANUARY, Calendar.DECEMBER");
        }
    }

    void setColors(HashMap<String, Integer> map) {
        _colors = map;
    }

    public interface OnDaySelectedListener {
        void onDaySelected(@NonNull PickerAdapter view, int month);
    }

}
