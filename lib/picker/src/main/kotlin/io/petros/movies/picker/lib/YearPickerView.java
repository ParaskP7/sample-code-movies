package io.petros.movies.picker.lib;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.petros.movies.lib.picker.R;

@SuppressWarnings("unused")
public class YearPickerView extends ListView {

    final YearAdapter _adapter;
    final int _viewSize;
    final int _childSize;
    OnYearSelectedListener _onYearSelectedListener;
    HashMap<String, Integer> _colors;

    public YearPickerView(@NonNull Context context) {
        this(context, null);
    }

    public YearPickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.style.AppTheme);
        super.setSelector(android.R.color.transparent);
    }

    public YearPickerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final LayoutParams frame = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(frame);
        final Resources res = context.getResources();
        _viewSize = res.getDimensionPixelOffset(R.dimen.picker_view_animator_height);
        _childSize = res.getDimensionPixelOffset(R.dimen.picker_year_label_height);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int year = _adapter.getYearForPosition(position);
                _adapter.setSelection(year);
                if (_onYearSelectedListener != null) {
                    _onYearSelectedListener.onYearChanged(YearPickerView.this, year);
                }
            }
        });
        _adapter = new YearAdapter(getContext());
        setAdapter(_adapter);
    }

    public void setOnYearSelectedListener(@NonNull OnYearSelectedListener listener) {
        _onYearSelectedListener = listener;
    }

    /**
     * Sets the currently selected year. Jumps immediately to the new year.
     *
     * @param year the target year
     */
    public void setYear(final int year) {
        _adapter.setSelection(year);
        post(() -> {
            final int position = _adapter.getPositionForYear(year);
            if (position >= 0 /*&& position < getCount()*/) {
                setSelectionCentered(position);
            }
        });
    }

    public void setSelectionCentered(int position) {
        final int offset = _viewSize / 2 - _childSize / 2;
        setSelectionFromTop(position, offset);
    }

    public void setRange(int min, int max) {
        _adapter.setRange(min, max);
    }

    public void setColors(@NonNull HashMap<String, Integer> colors) {
        this._colors = colors;
    }

    public int getFirstPositionOffset() {
        final View firstChild = getChildAt(0);
        if (firstChild == null) {
            return 0;
        }
        return firstChild.getTop();
    }

    protected void setMinYear(int minYear) {
        _adapter.setMinYear(minYear);
    }

    protected void setMaxYear(int maxYear) {
        _adapter.setMaxYear(maxYear);
    }

    protected void setActivatedYear(int activatedYear) {
        _adapter.setActivatedYear(activatedYear);
    }

    /**
     * The callback used to indicate the user changed the year.
     */
    public interface OnYearSelectedListener {
        /**
         * Called upon a year change.
         *
         * @param view The view associated with this listener.
         * @param year The year that was set.
         */
        void onYearChanged(@NonNull YearPickerView view, int year);
    }

    private class YearAdapter extends BaseAdapter {
        private final int ITEM_LAYOUT = R.layout.year_label_text_view;
        private final LayoutInflater __inflater;
        private int __activatedYear;
        private int __minYear, __maxYear, __count;

        public YearAdapter(Context context) {
            __inflater = LayoutInflater.from(context);
        }

        public void setRange(int min, int max) {
            int count = max - min + 1;
            if (__minYear != min || __maxYear != max || __count != count) {
                __minYear = min;
                __maxYear = max;
                __count = count;
                notifyDataSetInvalidated();
            }
        }

        public void setSelection(int year) {
            if (__activatedYear != year) {
                __activatedYear = year;
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return __count;
        }

        @Override
        public Integer getItem(int position) {
            return getYearForPosition(position);
        }

        @Override
        public long getItemId(int position) {
            return getYearForPosition(position);
        }

        public int getPositionForYear(int year) {
            return year - __minYear;
        }

        public int getYearForPosition(int position) {
            return __minYear + position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        @SuppressWarnings("ConstantConditions")
        public View getView(int position, View convertView, ViewGroup parent) {
            final TextView v;
            final boolean hasNewView = convertView == null;
            if (hasNewView) {
                v = (TextView) __inflater.inflate(ITEM_LAYOUT, parent, false);
            } else {
                v = (TextView) convertView;
            }
            final int year = getYearForPosition(position);
            final boolean activated = __activatedYear == year;

            if (hasNewView || v.getTag() != null || v.getTag().equals(activated)) {
                if (activated) {
                    if (_colors.containsKey("pickerBgSelectedColor")) {
                        v.setTextColor(_colors.get("pickerBgSelectedColor"));
                    }
                    v.setTextSize(25);
                } else {
                    if (_colors.containsKey("pickerFontColorNormal")) {
                        v.setTextColor(_colors.get("pickerFontColorNormal"));
                    }
                    v.setTextSize(20);
                }
                v.setTag(activated);

            }
            v.setText(String.valueOf(year));
            return v;
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(int position) {
            return true;
        }

        protected void setMaxYear(int maxYear) {
            __maxYear = maxYear;
            __count = __maxYear - __minYear + 1;
            notifyDataSetInvalidated();
        }

        protected void setMinYear(int minYear) {
            __minYear = minYear;
            __count = __maxYear - __minYear + 1;
            notifyDataSetInvalidated();
        }

        protected void setActivatedYear(int activatedYear) {
            if (activatedYear >= __minYear && activatedYear <= __maxYear) {
                __activatedYear = activatedYear;
                setYear(activatedYear);
            } else {
                throw new IllegalArgumentException("activated date is not in range");
            }
        }

    }

}
