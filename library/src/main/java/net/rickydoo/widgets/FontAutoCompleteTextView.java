package net.rickydoo.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import net.rickydoo.fontviews.FontManager;
import net.rickydoo.fontviews.widgets.R;

/**
 * Created by Ricky DOO on Nov. 26, 2015.
 */
public class FontAutoCompleteTextView extends AutoCompleteTextView {
	private static final String TAG = FontAutoCompleteTextView.class.getSimpleName();

	public FontAutoCompleteTextView(Context context) {
		this(context, null, R.attr.autoCompleteTextViewStyle);
	}

	public FontAutoCompleteTextView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.autoCompleteTextViewStyle);
	}

	public FontAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		if (isInEditMode()) {
			return;
		}
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FontAutoCompleteTextView,
				defStyleAttr, 0);
		try {
			fontName = a.getString(R.styleable.FontAutoCompleteTextView_fontAssets);
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
