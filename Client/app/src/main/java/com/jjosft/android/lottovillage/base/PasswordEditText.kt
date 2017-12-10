package com.jjosft.android.lottovillage.base

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.AppCompatEditText
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.jjosft.android.lottovillage.R


/**
 * Created by JJSOFT-DESKTOP on 2017-08-15.
 */
class PasswordEditText : AppCompatEditText, TextWatcher, View.OnTouchListener, View.OnFocusChangeListener {
    private var mTransparentDrawable: Drawable? = null
    private var mOnFocusChangeListener: OnFocusChangeListener? = null
    private var mOnTouchListener: OnTouchListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun setOnFocusChangeListener(onFocusChangeListener: OnFocusChangeListener) {
        this.mOnFocusChangeListener = onFocusChangeListener
    }

    override fun setOnTouchListener(onTouchListener: OnTouchListener) {
        this.mOnTouchListener = onTouchListener
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        val tempDrawable = ContextCompat.getDrawable(context, R.drawable.ic_visibility_24dp)
        mTransparentDrawable = DrawableCompat.wrap(tempDrawable)
        DrawableCompat.setTintList(mTransparentDrawable!!, hintTextColors)
        mTransparentDrawable!!.setBounds(0, 0, mTransparentDrawable!!.intrinsicWidth, mTransparentDrawable!!.intrinsicHeight)

        setClearIconVisible(false)

        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        addTextChangedListener(this)
    }

    override fun onFocusChange(view: View, isFocus: Boolean) {
        if (isFocus) {
            setClearIconVisible(text.isNotEmpty())
        } else {
            setClearIconVisible(false)
        }

        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener!!.onFocusChange(view, isFocus)
        }
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        val x = motionEvent.x.toInt()
        if (mTransparentDrawable!!.isVisible && x > width - paddingRight - mTransparentDrawable!!.intrinsicWidth) {
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (inputType == InputType.TYPE_CLASS_TEXT) {
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                } else {
                    inputType = InputType.TYPE_CLASS_TEXT
                }
                //typeface = Typekit.createFromAsset(context, CUSTOM_FONT)
            }
            return true
        }

        return mOnTouchListener != null && mOnTouchListener!!.onTouch(view, motionEvent)
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        if (isFocused) {
            setClearIconVisible(charSequence.isNotEmpty())
        }
    }

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun afterTextChanged(editable: Editable) {

    }

    private fun setClearIconVisible(isVisible: Boolean) {
        mTransparentDrawable!!.setVisible(isVisible, false)

        val leftDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.ic_password_24dp))
        leftDrawable.setVisible(true,false)
        DrawableCompat.setTintList(leftDrawable, hintTextColors)
        leftDrawable.setBounds(0, 0, leftDrawable.intrinsicWidth, leftDrawable.intrinsicHeight)

        setCompoundDrawables(leftDrawable, null, if (isVisible) mTransparentDrawable else null, null)
    }
}
