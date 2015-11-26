package net.rickydoo.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import net.rickydoo.fontviews.FontManager;
import net.rickydoo.fontviews.widgets.R;

/**
 * Created by Ricky DOO on Nov. 26, 2015.
 */
public class FontCheckedTextView extends CheckedTextView {
	private static final String TAG = FontCheckedTextView.class.getSimpleName();

	public FontCheckedTextView(Context context) {
		this(context, null, R.attr.checkedTextViewStyle);
	}

	public FontCheckedTextView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.checkedTextViewStyle);
	}

	public FontCheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		if (isInEditMode()) {
			return;
		}
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FontCheckedTextView,
				defStyleAttr, 0);
		try {
			fontName = a.getString(R.styleable.FontCheckedTextView_fontAssets);
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
