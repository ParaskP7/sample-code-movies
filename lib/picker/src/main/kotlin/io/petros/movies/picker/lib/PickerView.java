package io.petros.movies.picker.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.petros.movies.lib.picker.R;
import timber.log.Timber;

@SuppressLint("SyntheticAccessor")
@SuppressWarnings({"unused", "deprecation", "CommentedOutCode"})
public class PickerView extends FrameLayout {

    static int _minYear = 1900;
    static int _maxYear = Calendar.getInstance().get(Calendar.YEAR);
    private final String[] _monthNames;
    @NonNull YearPickerView _yearView;
    @NonNull ListView _monthList;
    @NonNull PickerAdapter _pickerAdapter;
    @NonNull TextView _month;
    @NonNull TextView _year;
    @NonNull TextView _title;
    @NonNull Context _context;
    int _headerFontColorSelected;
    int _headerFontColorNormal;
    boolean _showMonthOnly;
    int _selectedMonth;
    int _selectedYear;
    @Nullable PickerDialog.OnYearChangedListener _onYearChanged;
    @Nullable PickerDialog.OnMonthChangedListener _onMonthChanged;
    OnDateSet _onDateSet;
    OnCancel _onCancel;

    /*private static final int[] ATTRS_TEXT_COLOR = new int[] {
            com.android.internal.R.attr.textColor};
    private static final int[] ATTRS_DISABLED_ALPHA = new int[] {
            com.android.internal.R.attr.disabledAlpha};*/
    PickerDialog.OnConfigChangeListener configChangeListener;

    public PickerView(@NonNull Context context) {
        this(context, null);
        _context = context;
    }

    public PickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        _context = context;
    }

    public PickerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        _context = context;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.picker_view, this);
        _monthNames = new DateFormatSymbols(Locale.getDefault()).getShortMonths();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PickerView,
                defStyleAttr, 0);

        // getting default values based on the user's theme.

        /*
            headerBgColor = android.R.attr.colorAccent;
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(headerBgColor, outValue, true);
            int color = outValue.data;

        // OR
        TypedValue typedValue = new TypedValue();

        TypedArray a = mContext.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorAccent, R.attr.colorPrimary });
        int color = a.getColor(0, 0);

        a.recycle();

        // OR

        final TypedValue value = new TypedValue ();
        context.getTheme ().resolveAttribute (R.attr.colorAccent, value, true);
        int color = value.data
    */

        int headerBgColor = a.getColor(R.styleable.PickerView_headerBgColor, 0);
        _headerFontColorNormal = a.getColor(R.styleable.PickerView_headerFontColorNormal, 0);
        _headerFontColorSelected = a.getColor(R.styleable.PickerView_headerFontColorSelected, 0);
        int pickerBgColor = a.getColor(R.styleable.PickerView_pickerBgColor, 0);
        int pickerBgSelectedColor = a.getColor(R.styleable.PickerView_pickerBgSelectedColor, 0);
        int pickerFontColorNormal = a.getColor(R.styleable.PickerView_pickerFontColorNormal, 0);
        int pickerFontColorSelected = a.getColor(R.styleable.PickerView_pickerFontColorSelected, 0);
        int pickerFontColorDisabled = a.getColor(R.styleable.PickerView_pickerFontColorDisabled, 0);
        int headerTitleColor = a.getColor(R.styleable.PickerView_headerTitleColor, 0);
        int actionButtonColor = a.getColor(R.styleable.PickerView_dialogActionButtonColor, 0);

        if (pickerFontColorNormal == 0) {

            pickerFontColorNormal = getResources().getColor(R.color.fontBlackEnable);

           /*pickerFontColorNormal = android.R.attr.textColor;
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(pickerFontColorNormal, outValue, true);
            int color = outValue.data;
            pickerFontColorNormal = color;*/


            /*pickerFontColorNormal = context.getTheme().resolveAttribute(
                    android.R.attr.textColorPrimary, outValue, true) ? outValue.data : getResources().getColor(R.color.fontBlackEnable);*/

        }

        if (pickerFontColorSelected == 0) {
            pickerFontColorSelected = getResources().getColor(R.color.fontWhiteEnable);
        }

        if (pickerFontColorDisabled == 0) {
            pickerFontColorDisabled = getResources().getColor(R.color.fontBlackDisable);

        }
        if (_headerFontColorNormal == 0) {
            _headerFontColorNormal = getResources().getColor(R.color.fontWhiteDisable);
        }
        if (_headerFontColorSelected == 0) {
            _headerFontColorSelected = getResources().getColor(R.color.fontWhiteEnable);
        }
        if (headerTitleColor == 0) {
            headerTitleColor = getResources().getColor(R.color.fontWhiteEnable);
        }
        if (pickerBgColor == 0) {
            pickerBgColor = getResources().getColor(R.color.fontWhiteEnable);
        }

        if (headerBgColor == 0) {

            headerBgColor = android.R.attr.colorAccent;
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(headerBgColor, outValue, true);
            headerBgColor = outValue.data;
        }

        if (pickerBgSelectedColor == 0) {

            pickerBgSelectedColor = android.R.attr.colorAccent;
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(pickerBgSelectedColor, outValue, true);
            pickerBgSelectedColor = outValue.data;
        }

        HashMap<String, Integer> map = new HashMap<>();
        if (pickerBgColor != 0)
            map.put("pickerBgColor", pickerBgColor);
        if (pickerBgSelectedColor != 0)
            map.put("pickerBgSelectedColor", pickerBgSelectedColor);
        if (pickerFontColorNormal != 0)
            map.put("pickerFontColorNormal", pickerFontColorNormal);
        if (pickerFontColorSelected != 0)
            map.put("pickerFontColorSelected", pickerFontColorSelected);
        if (pickerFontColorDisabled != 0)
            map.put("pickerFontColorDisabled", pickerFontColorDisabled);

        a.recycle();

        _monthList = (ListView) findViewById(R.id.listview);
        _yearView = (YearPickerView) findViewById(R.id.yearView);
        _month = (TextView) findViewById(R.id.month);
        _year = (TextView) findViewById(R.id.year);
        _title = (TextView) findViewById(R.id.title);
        RelativeLayout _pickerBg = (RelativeLayout) findViewById(R.id.picker_view);
        LinearLayout _header = (LinearLayout) findViewById(R.id.header);
        RelativeLayout _actionBtnLay = (RelativeLayout) findViewById(R.id.action_btn_lay);
        TextView ok = (TextView) findViewById(R.id.ok_action);
        TextView cancel = (TextView) findViewById(R.id.cancel_action);


        if (actionButtonColor != 0) {
            ok.setTextColor(actionButtonColor);
            cancel.setTextColor(actionButtonColor);
        } else {
            ok.setTextColor(headerBgColor);
            cancel.setTextColor(headerBgColor);
        }

        if (_headerFontColorSelected != 0)
            _month.setTextColor(_headerFontColorSelected);
        if (_headerFontColorNormal != 0)
            _year.setTextColor(_headerFontColorNormal);
        if (headerTitleColor != 0)
            _title.setTextColor(headerTitleColor);
        if (headerBgColor != 0)
            _header.setBackgroundColor(headerBgColor);
        if (pickerBgColor != 0)
            _pickerBg.setBackgroundColor(pickerBgColor);
        if (pickerBgColor != 0)
            _actionBtnLay.setBackgroundColor(pickerBgColor);

        ok.setOnClickListener(view -> _onDateSet.onDateSet());
        cancel.setOnClickListener(view -> _onCancel.onCancel());
        _pickerAdapter = new PickerAdapter(context);
        _pickerAdapter.setColors(map);
        _pickerAdapter.setOnDaySelectedListener((view, selectedMonth) -> {
            Timber.d("PickerViewStyle selected month = %s", selectedMonth);
            PickerView.this._selectedMonth = selectedMonth;
            _month.setText(_monthNames[selectedMonth]);
            if (!_showMonthOnly) {
                _monthList.setVisibility(View.GONE);
                _yearView.setVisibility(View.VISIBLE);
                _month.setTextColor(_headerFontColorNormal);
                _year.setTextColor(_headerFontColorSelected);
            }
            if (_onMonthChanged != null) {
                _onMonthChanged.onMonthChanged(selectedMonth);
            }
        });
        _monthList.setAdapter(_pickerAdapter);

        _yearView.setRange(_minYear, _maxYear);
        _yearView.setColors(map);
        _yearView.setYear(Calendar.getInstance().get(Calendar.YEAR));
        _yearView.setOnYearSelectedListener((view, selectedYear) -> {
            Timber.d("selected year = %s", selectedYear);
            PickerView.this._selectedYear = selectedYear;
            _year.setText(String.valueOf(selectedYear));
            _year.setTextColor(_headerFontColorSelected);
            _month.setTextColor(_headerFontColorNormal);
            if (_onYearChanged != null) {
                _onYearChanged.onYearChanged(selectedYear);
            }
        });
        _month.setOnClickListener(view -> {
            if (_monthList.getVisibility() == GONE) {
                _yearView.setVisibility(GONE);
                _monthList.setVisibility(VISIBLE);
                _year.setTextColor(_headerFontColorNormal);
                _month.setTextColor(_headerFontColorSelected);
            }
        });
        _year.setOnClickListener(view -> {
            if (_yearView.getVisibility() == GONE) {
                _monthList.setVisibility(GONE);
                _yearView.setVisibility(VISIBLE);
                _year.setTextColor(_headerFontColorSelected);
                _month.setTextColor(_headerFontColorNormal);
            }
        });
    }

    protected void init(int year, int month) {
        _selectedYear = year;
        _selectedMonth = month;
    }

    protected void setMaxMonth(int maxMonth) {
        if (maxMonth <= Calendar.DECEMBER && maxMonth >= Calendar.JANUARY) {
            _pickerAdapter.setMaxMonth(maxMonth);
        } else {
            throw new IllegalArgumentException("Month out of range please send months between " +
                    "Calendar.JANUARY, Calendar.DECEMBER");
        }
    }

    protected void setMinMonth(int minMonth) {
        if (minMonth >= Calendar.JANUARY && minMonth <= Calendar.DECEMBER) {
            _pickerAdapter.setMinMonth(minMonth);
        } else {
            throw new IllegalArgumentException("Month out of range please send months between" +
                    " Calendar.JANUARY, Calendar.DECEMBER");
        }
    }

    protected void setMinYear(int minYear) {
        _yearView.setMinYear(minYear);
    }

    protected void setMaxYear(int maxYear) {
        _yearView.setMaxYear(maxYear);
    }

    protected void showMonthOnly() {
        _showMonthOnly = true;
        _year.setVisibility(GONE);
    }

    protected void showYearOnly() {
        _monthList.setVisibility(View.GONE);
        _yearView.setVisibility(VISIBLE);

        _month.setVisibility(GONE);
        _year.setTextColor(_headerFontColorSelected);
    }

    protected void setActivatedMonth(int activatedMonth) {
        if (activatedMonth >= Calendar.JANUARY && activatedMonth <= Calendar.DECEMBER) {
            _pickerAdapter.setActivatedMonth(activatedMonth);
            _month.setText(_monthNames[activatedMonth]);
        } else {
            throw new IllegalArgumentException("Month out of range please send months between Calendar.JANUARY, Calendar.DECEMBER");
        }

    }

    protected void setActivatedYear(int activatedYear) {
        _yearView.setActivatedYear(activatedYear);
        _year.setText(String.valueOf(activatedYear));
    }

    protected void setMonthRange(int minMonth, int maxMonth) {
        if (minMonth < maxMonth) {
            setMinMonth(minMonth);
            setMaxYear(maxMonth);
        } else {
            throw new IllegalArgumentException("maximum month is less then minimum month");
        }
    }

    protected void setYearRange(int minYear, int maxYear) {
        if (minYear < maxYear) {
            setMinYear(minYear);
            setMaxYear(maxYear);
        } else {
            throw new IllegalArgumentException("maximum year is less then minimum year");
        }
    }

    protected void setMonthYearRange(int minMonth, int maxMonth, int minYear, int maxYear) {
        setMonthRange(minMonth, maxMonth);
        setYearRange(minYear, maxYear);
    }

    protected void setTitle(@Nullable String dialogTitle) {
        if (dialogTitle != null && dialogTitle.trim().length() > 0) {
            _title.setText(dialogTitle);
            _title.setVisibility(VISIBLE);
        } else {
            _title.setVisibility(GONE);
        }
    }

    protected int getMonth() {
        return _selectedMonth;
    }

    protected int getYear() {
        return _selectedYear;
    }

    protected void setOnMonthChangedListener(@Nullable PickerDialog.OnMonthChangedListener onMonthChangedListener) {
        if (onMonthChangedListener != null) {
            this._onMonthChanged = onMonthChangedListener;
        }
    }

    protected void setOnYearChangedListener(@Nullable PickerDialog.OnYearChangedListener onYearChangedListener) {
        if (onYearChangedListener != null) {
            this._onYearChanged = onYearChangedListener;
        }
    }

    public void setOnDateListener(@NonNull OnDateSet onDateSet) {
        this._onDateSet = onDateSet;
    }

    public void setOnCancelListener(@NonNull OnCancel onCancel) {
        this._onCancel = onCancel;
    }

    protected void setOnConfigurationChanged(@NonNull PickerDialog.OnConfigChangeListener configChangeListener) {
        this.configChangeListener = configChangeListener;
    }

    @Override
    protected void onConfigurationChanged(@NonNull Configuration newConfig) {
        configChangeListener.onConfigChange();
        super.onConfigurationChanged(newConfig);
    }

    public interface OnDateSet {
        void onDateSet();
    }

    public interface OnCancel {
        void onCancel();
    }

}
