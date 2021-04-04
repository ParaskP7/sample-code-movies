package io.petros.movies.picker.lib;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import java.util.Calendar;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.petros.movies.lib.picker.R;
import timber.log.Timber;

@SuppressLint("SyntheticAccessor")
@SuppressWarnings("unused")
public class PickerDialog extends AlertDialog implements OnClickListener, OnDateChangedListener {
    @NonNull private final PickerView _picker;
    @Nullable private final OnDateSetListener _callBack;
    @Nullable private final View view;

    /**
     * @param context     The context the dialog is to run in.
     * @param callBack    How the parent is notified that the date is set.
     * @param year        The initial year of the dialog.
     * @param monthOfYear The initial month of the dialog.
     */
    private PickerDialog(Context context,
                         OnDateSetListener callBack,
                         int year,
                         int monthOfYear) {
        this(context, 0, callBack, year, monthOfYear);
    }


    /**
     * @param context     The context the dialog is to run in.
     * @param theme       the theme to apply to this dialog
     * @param callBack    How the parent is notified that the date is set.
     * @param year        The initial year of the dialog.
     * @param monthOfYear The initial month of the dialog.
     */
    @SuppressLint("InflateParams")
    private PickerDialog(Context context,
                         int theme,
                         @Nullable OnDateSetListener callBack,
                         int year,
                         int monthOfYear) {
        super(context, theme);
        _callBack = callBack;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.picker_dialog, null);

        setView(view);
        _picker = (PickerView) view.findViewById(R.id.picker);
        _picker.setOnDateListener(() -> {
            tryNotifyDateSet();
            dismiss();
        });
        _picker.setOnCancelListener(this::dismiss);

        // to show dialog bigger view in landscape mode we are increasing the
        // height and width of the dialog. If we do that android don't dismiss the dialog after
        // rotation and try to render landscape UI in portrait mode which is not correct.
        // so dismissing the dialog on each time when orientation changes.
        _picker.setOnConfigurationChanged(this::dismiss);
        _picker.init(year, monthOfYear);
    }

    @Override
    public void show() {
        if (view != null) {
            if (this.getContext().getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_LANDSCAPE) {
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                if (getWindow() != null) {
                    lp.copyFrom(getWindow().getAttributes());
                    lp.width = (int) (this.getContext().getResources().getDisplayMetrics().widthPixels * 0.94);
                    lp.height = (int) (this.getContext().getResources().getDisplayMetrics().heightPixels * 0.94);
                    // show the dialog as per super implementation
                    super.show();
                    // now dialog attached to window so apply the size
                    getWindow().setLayout(lp.width, lp.height);
                }

                return;
            } else {
                dismiss();
            }
        }
        super.show();
    }

    @Override
    public void onClick(@NonNull DialogInterface dialog, int which) {
        tryNotifyDateSet();
    }

    @Override
    public void onDateChanged(@NonNull DatePicker view, int year, int month, int day) {
        _picker.init(year, month);
    }

    /**
     * Gets the {@link DatePicker} contained in this dialog.
     *
     * @return The calendar view.
     */
    @NonNull
    public PickerView getDatePicker() {
        return _picker;
    }

    void tryNotifyDateSet() {
        if (_callBack != null) {
            _picker.clearFocus();
            _callBack.onDateSet(_picker.getMonth(), _picker.getYear());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setMinMonth(int minMonth) {
        _picker.setMinMonth(minMonth);
    }

    private void setMaxMonth(int maxMonth) {
        _picker.setMaxMonth(maxMonth);
    }

    private void setMinYear(int minYear) {
        _picker.setMinYear(minYear);
    }

    private void setMaxYear(int maxYear) {
        _picker.setMaxYear(maxYear);
    }

    private void setActivatedMonth(int activatedMonth) {
        _picker.setActivatedMonth(activatedMonth);
    }

    private void setActivatedYear(int activatedMonth) {
        _picker.setActivatedYear(activatedMonth);
    }

    private void setPickerTitle(String title) {
        _picker.setTitle(title);
    }

    private void showMonthOnly() {
        _picker.showMonthOnly();
    }

    private void showYearOnly() {
        _picker.showYearOnly();
    }

    private void setOnMonthChangedListener(OnMonthChangedListener onMonthChangedListener) {
        if (onMonthChangedListener != null) {
            _picker.setOnMonthChangedListener(onMonthChangedListener);
        }
    }

    private void setOnYearChangedListener(OnYearChangedListener onYearChangedListener) {
        if (onYearChangedListener != null) {
            _picker.setOnYearChangedListener(onYearChangedListener);
        }
    }

    /**
     * The callback used to indicate the user is done selecting month.
     */
    public interface OnDateSetListener {
        /**
         * @param selectedMonth The month that was set (0-11) for compatibility with {@link Calendar}.
         * @param selectedYear  The year that was set.
         */
        void onDateSet(int selectedMonth, int selectedYear);
    }

    /**
     * The callback used to indicate the user click on month
     */
    public interface OnMonthChangedListener {
        /**
         * @param selectedMonth The month that was set (0-11) for compatibility
         *                      with {@link Calendar}.
         */
        void onMonthChanged(int selectedMonth);
    }

    /**
     * The callback used to indicate the user click on year.
     */
    public interface OnYearChangedListener {
        /**
         * Called upon a year change.
         *
         * @param year The year that was set.
         */
        void onYearChanged(int year);
    }

    public interface OnConfigChangeListener {
        void onConfigChange();
    }

    public static class Builder {

        private final Context _context;
        private final OnDateSetListener _callBack;
        private int _activatedMonth, _activatedYear;
        private int _minMonth = Calendar.JANUARY, _maxMonth = Calendar.DECEMBER;
        private int _minYear, _maxYear;
        private boolean monthOnly, yearOnly;
        private String title = null;
        private OnYearChangedListener _onYearChanged;
        private OnMonthChangedListener _onMonthChanged;

        /**
         * Build a Dialog with month and year with given context.
         *
         * @param context  Context: the parent context
         * @param callBack PickerDialog.OnDateSetListener: the listener to call
         *                 when the user sets the date
         * @param year     the initially selected year
         * @param month    the initially selected month (0-11 for compatibility with
         *                 {@link Calendar}Calender.MONTH or Calendar.JANUARY, Calendar.FEBRUARY etc)
         */

        public Builder(@Nullable Context context,
                       int year,
                       @IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER) int month,
                       @NonNull OnDateSetListener callBack) {
            if (month >= Calendar.JANUARY && month <= Calendar.DECEMBER) {
                this._activatedMonth = month;
            } else {
                throw new IllegalArgumentException("Month range should be between 0 " +
                        "(Calender.JANUARY) to 11 (Calendar.DECEMBER)");
            }

            if (year >= 1) {
                this._activatedYear = year;
            } else {
                throw new IllegalArgumentException("Selected year should be > 1");
            }

            this._context = context;
            this._callBack = callBack;

            if (year > PickerView._minYear) {
                _minYear = PickerView._minYear;
            } else {
                _minYear = year;
                PickerView._minYear = year;
            }

            if (year > PickerView._maxYear) {
                _maxYear = year;
                PickerView._maxYear = year;
            } else {
                _maxYear = PickerView._maxYear;
            }
        }

        /**
         * Minimum enable month in picker (0-11 for compatibility with Calender.MONTH or
         * Calendar.JANUARY, Calendar.FEBRUARY etc).
         */
        @NonNull
        public Builder setMinMonth(@IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER)
                                           int minMonth) {
            if (minMonth >= Calendar.JANUARY && minMonth <= Calendar.DECEMBER) {
                this._minMonth = minMonth;
                return this;
            } else {
                throw new IllegalArgumentException("Month range should be between 0 " +
                        "(Calender.JANUARY) to 11 (Calendar.DECEMBER)");
            }
        }

        /**
         * Maximum enabled month in picker (0-11 for compatibility with Calender.MONTH or
         * Calendar.JANUARY, Calendar.FEBRUARY etc).
         */
        @NonNull
        public Builder setMaxMonth(@IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER)
                                           int maxMonth) {
            /* if (maxMonth >= Calendar.JANUARY && maxMonth <= Calendar.DECEMBER) {*/
            this._maxMonth = maxMonth;
            return this;
            /*} else {
                throw new IllegalArgumentException("Month range should be between 0 " +
                        "(Calender.JANUARY) to 11 (Calendar.DECEMBER)");
            }*/
        }

        /**
         * Starting year in the picker.
         */
        @NonNull
        public Builder setMinYear(int minYear) {
            this._minYear = minYear;
            return this;
        }

        /**
         * Ending year in the picker.
         */
        @NonNull
        public Builder setMaxYear(int maxYear) {
            this._maxYear = maxYear;
            return this;
        }

        /**
         * Initially selected month (0-11 for compatibility with Calender.MONTH or
         * Calendar.JANUARY, Calendar.FEBRUARY etc).
         */
        @NonNull
        public Builder setActivatedMonth(@IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER)
                                                 int activatedMonth) {
            this._activatedMonth = activatedMonth;
            return this;
        }

        /**
         * Initially selected year (0-11 for compatibility with Calender.MONTH or
         * Calendar.JANUARY, Calendar.FEBRUARY etc).
         */
        @NonNull
        public Builder setActivatedYear(int activatedYear) {
            this._activatedYear = activatedYear;
            return this;
        }

        /**
         * Minimum and Maximum enable month in picker (0-11 for compatibility with Calender.MONTH or
         * Calendar.JANUARY, Calendar.FEBRUARY etc).
         *
         * @param minMonth minimum enabled month.
         * @param maxMonth maximum enabled month.
         * @return Builder
         */
        @NonNull
        public Builder setMonthRange(@IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER)
                                             int minMonth,
                                     @IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER)
                                             int maxMonth) {
            if (minMonth >= Calendar.JANUARY && minMonth <= Calendar.DECEMBER &&
                    maxMonth >= Calendar.JANUARY && maxMonth <= Calendar.DECEMBER) {
                this._minMonth = minMonth;
                this._maxMonth = maxMonth;
                return this;
            } else {
                throw new IllegalArgumentException("Month range should be between 0 " +
                        "(Calender.JANUARY) to 11 (Calendar.DECEMBER)");
            }
        }

        /**
         * Starting and ending year show in picker
         *
         * @param minYear starting year
         * @param maxYear ending year
         */
        @NonNull
        public Builder setYearRange(int minYear, int maxYear) {
            if (minYear <= maxYear) {
                this._minYear = minYear;
                this._maxYear = maxYear;
                return this;
            } else {
                throw new IllegalArgumentException("Minimum year should be less then Maximum year");
            }

        }

        /**
         * Set the Minimum, maximum enabled months and starting , ending years.
         *
         * @param minMonth minimum enabled month in picker
         * @param maxMonth maximum enabled month in picker
         * @param minYear  starting year
         * @param maxYear  ending year
         */
        @NonNull
        public Builder setMonthAndYearRange(@IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER)
                                                    int minMonth,
                                            @IntRange(from = Calendar.JANUARY, to = Calendar.DECEMBER)
                                                    int maxMonth,
                                            int minYear, int maxYear) {
            if (minMonth >= Calendar.JANUARY && minMonth <= Calendar.DECEMBER &&
                    maxMonth >= Calendar.JANUARY && maxMonth <= Calendar.DECEMBER) {
                this._minMonth = minMonth;
                this._maxMonth = maxMonth;

            } else {
                throw new IllegalArgumentException("Month range should be between 0 " +
                        "(Calender.JANUARY) to 11 (Calendar.DECEMBER)");
            }

            if (minYear <= maxYear) {
                this._minYear = minYear;
                this._maxYear = maxYear;
            } else {
                throw new IllegalArgumentException("Minimum year should be less then Maximum year");
            }
            return this;
        }

        /**
         * User can select month only. Year won't be shown to user once user select the month.
         *
         * @return Builder
         */
        @NonNull
        public Builder showMonthOnly() {

            if (yearOnly) {
                Timber.e("yearOnly also set to true before. Now setting yearOnly to false monthOnly to true");
            }
            this.yearOnly = false;
            this.monthOnly = true;
            return this;
        }

        /**
         * User can select year only. Month won't be shown to user once user select the month.
         *
         * @return Builder
         */
        @NonNull
        public Builder showYearOnly() {
            if (monthOnly) {
                Timber.e("monthOnly also set to true before. Now setting monthOnly to false and yearOnly to true");
            }
            this.monthOnly = false;
            this.yearOnly = true;
            return this;
        }

        /**
         * Set the title to the picker.
         */
        @NonNull
        public Builder setTitle(@NonNull String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the callback that will be called when user click on any month.
         */
        @NonNull
        public Builder setOnMonthChangedListener(@NonNull OnMonthChangedListener onMonthChangedListener) {
            this._onMonthChanged = onMonthChangedListener;
            return this;
        }

        /**
         * Sets the callback that will be called when the user select any year.
         */
        @NonNull
        public Builder setOnYearChangedListener(@NonNull OnYearChangedListener onYearChangedListener) {
            this._onYearChanged = onYearChangedListener;
            return this;
        }

        @NonNull
        public PickerDialog build() {

            if (_minMonth > _maxMonth) {
                throw new IllegalArgumentException("Minimum month should always " +
                        "smaller then maximum month.");
            }

            if (_minYear > _maxYear) {
                throw new IllegalArgumentException("Minimum year should always " +
                        "smaller then maximum year.");
            }

            if (_activatedMonth < _minMonth || _activatedMonth > _maxMonth) {
                throw new IllegalArgumentException("Activated month should always " +
                        "in between Minimum and maximum month.");
            }

            if (_activatedYear < _minYear || _activatedYear > _maxYear) {
                throw new IllegalArgumentException("Activated year should always " +
                        "in between Minimum year and maximum year.");
            }


            PickerDialog pickerDialog = new PickerDialog(_context, _callBack, _activatedYear,
                    _activatedMonth);
            if (monthOnly) {
                pickerDialog.showMonthOnly();
                _minYear = 0;
                _maxYear = 0;
                _activatedYear = 0;
            } else if (yearOnly) {
                pickerDialog.showYearOnly();
                _minMonth = 0;
                _maxMonth = 0;
                _activatedMonth = 0;
            }
            pickerDialog.setMinMonth(_minMonth);
            pickerDialog.setMaxMonth(_maxMonth);
            pickerDialog.setMinYear(_minYear);
            pickerDialog.setMaxYear(_maxYear);
            pickerDialog.setActivatedMonth(_activatedMonth);
            pickerDialog.setActivatedYear(_activatedYear);

            if (_onMonthChanged != null) {
                pickerDialog.setOnMonthChangedListener(_onMonthChanged);
            }

            if (_onYearChanged != null) {
                pickerDialog.setOnYearChangedListener(_onYearChanged);
            }

            if (title != null) {
                pickerDialog.setPickerTitle(title.trim());
            }
            return pickerDialog;
        }
    }

}
