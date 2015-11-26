package net.rickydoo.fontviews;

import android.app.Application;

/**
 * Created by Cuong DO on Nov. 26, 2015.
 */
public class FontApplication extends Application {
	private static final String TAG = FontApplication.class.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();
		FontManager.init(this);
	}
}
