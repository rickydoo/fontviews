package net.rickydoo.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

import net.rickydoo.fontviews.FontManager;
import net.rickydoo.fontviews.widgets.R;

/**
 * Created by Ricky DOO on Nov. 26, 2015.
 */
public class FontRadioButton extends RadioButton {
	private static final String TAG = FontRadioButton.class.getSimpleName();

	public FontRadioButton(Context context) {
		this(context, null, R.attr.radioButtonStyle);
	}

	public FontRadioButton(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.radioButtonStyle);
	}

	public FontRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		if (isInEditMode()) {
			return;
		}
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FontRadioButton,
				defStyleAttr, 0);
		try {
			fontName = a.getString(R.styleable.FontRadioButton_fontAssets);
		} finally {
			a.recycle();
		}
		Typeface typeface = FontManager.INSTANCE.getTypeface(fontName);
		setTypeface(typeface);
	}

	private String fontName;
	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
		Typeface typeface = FontManager.INSTANCE.getTypeface(fontName);
		setTypeface(typeface);
		invalidate();
	}
}
