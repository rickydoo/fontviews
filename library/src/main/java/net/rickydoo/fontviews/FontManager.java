package net.rickydoo.fontviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.SparseArray;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Ricky DOO on Nov. 26, 2015.
 */
public enum FontManager {
	INSTANCE;

	private Context mContext;
	private SparseArray<Typeface> mTypefaces = new SparseArray<>(16);
	private HashMap<String, Typeface> mapTypefaces = new HashMap<>(16);

	public static void init(Context mContext) {
		INSTANCE.mContext = mContext;
	}

	public Typeface getTypeface(int resource) {
		Typeface tf = mTypefaces.get(resource);
		if (tf != null) {
			return tf;
		}

		tf = getTypefaceRes(resource);
		mTypefaces.put(resource, tf);
		return tf;
	}

	public Typeface getTypeface(String fontName) {
		Typeface tf = mapTypefaces.get(fontName);
		if (tf != null) {
			return tf;
		}

		tf = getTypefaceAsset(fontName);
		mapTypefaces.put(fontName, tf);
		return tf;
	}

	private Typeface getTypefaceRes(int resource) {
		Typeface tf = null;
		InputStream is;
		try {
			is = mContext.getResources().openRawResource(resource);
		} catch (Resources.NotFoundException e) {
			return Typeface.DEFAULT;
		}

		String outPath = mContext.getCacheDir() + "/tmp.raw";

		try {
			byte[] buffer = new byte[is.available()];
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outPath));

			int l = 0;
			while ((l = is.read(buffer)) > 0)
				bos.write(buffer, 0, l);

			bos.close();

			tf = Typeface.createFromFile(outPath);

			// clean up
			new File(outPath).delete();
		} catch (IOException e) {
			return Typeface.DEFAULT;
		}

		return tf;
	}


	private Typeface getTypefaceAsset(String fontName) {
		Typeface tf = null;
		InputStream is;
		try {
			is = mContext.getAssets().open("fonts/" + fontName);
		} catch (IOException e) {
			return Typeface.DEFAULT;
		}

		String outPath = mContext.getCacheDir() + "/tmp.raw";

		try {
			byte[] buffer = new byte[is.available()];
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outPath));

			int l = 0;
			while ((l = is.read(buffer)) > 0)
				bos.write(buffer, 0, l);

			bos.close();

			tf = Typeface.createFromFile(outPath);

			// clean up
			new File(outPath).delete();
		} catch (IOException e) {
			return Typeface.DEFAULT;
		}

		return tf;
	}
}
