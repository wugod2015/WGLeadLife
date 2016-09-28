package com.jackhan.wgleadlife.utils;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

public class PaletteUtils {
	public static int getColorByPalette(Bitmap bitmap, int defaultColor) {
		Palette palette = Palette.from(bitmap).generate();
		Palette.Swatch swatch = palette.getVibrantSwatch();
		if (swatch == null)
			return defaultColor;
		return swatch.getRgb();

	}
}
