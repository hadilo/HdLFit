package com.hadilo.hdlfit.utils.widget.spinner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import com.google.android.material.textfield.TextInputLayout;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by hadilo on 9/28/17.
 */

public class SpinnerTextInputLayout extends TextInputLayout {

    private static final String TAG = "SpinnerClickToTextIn";

    NDSpinner spinner;
    AlertDialog.Builder popup;

    ArrayAdapter arrayAdapter;
    OnItemSelectedListener onItemSelectedListener;

    public SpinnerTextInputLayout(Context context) {
        super(context);
        initSpinner();
        initPopup();
    }

    public SpinnerTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSpinner();
        initPopup();
    }

    public SpinnerTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSpinner();
        initPopup();
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public ClickToSelectEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//
//        mHint = getHint();
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setFocusable(false);
        setClickable(false);
        setLongClickable(false);
        getEditText().setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        getEditText().setCursorVisible(false);
        getEditText().setFocusable(false);
//        getEditText().setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_drop_down_black_24dp), null);
    }

    public void setItems(ArrayAdapter arrayAdapter) {

        this.arrayAdapter = arrayAdapter; //new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        this.arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(this.arrayAdapter);

        configureOnClickListener();

    }

    private int mode;
    public static final int MODE_DROPDOWN = 0x0;
    public static final int MODE_POPUP = 0x1;

    public void setMode(int mode){
        this.mode = mode;
    }

    private void configureOnClickListener() {

        getEditText().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setValidate(true);
                switch (mode)
                {
                    case MODE_DROPDOWN:
                        spinner.performClick();
                        break;

                    case MODE_POPUP:
                        /*if(mListableItems.length != 0)*/ popup.create().show();
                        break;
                }
            }
        });

        switch (mode){
            case MODE_DROPDOWN:
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (onItemSelectedListener != null && isValidate()) {
                            onItemSelectedListener.onItemSelectedListener(arrayAdapter.getItem(i), i);
                            setValidate(false);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        if (onItemSelectedListener != null) {
                            onItemSelectedListener.onItemSelectedListener(null, -1);
                        }
                    }
                });
                break;
            case MODE_POPUP:
                popup.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        if (onItemSelectedListener != null) {
                            onItemSelectedListener.onItemSelectedListener(arrayAdapter.getItem(selectedIndex), selectedIndex);
                        }
                    }
                });
                break;
        }
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelectedListener(Object item, int selectedIndex);
    }

    private boolean validate;
    public void setValidate(boolean validate){
        this.validate = validate;
    }

    public boolean isValidate(){
        return validate;
    }

    public void setSelection(int position){
        spinner.setSelection(position, true);
    }

    private void initSpinner(){
        spinner = new NDSpinner(getContext());
        spinner.setLayoutParams(new LayoutParams(0, 0));

        //https://stackoverflow.com/a/11710448
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
//                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    spinner.setDropDownWidth(getMeasuredWidth());
                }
            });
        }
        addView(spinner);
    }

    private void initPopup(){
        popup = new AlertDialog.Builder(getContext());
        popup.setPositiveButton("", null);
    }
}
