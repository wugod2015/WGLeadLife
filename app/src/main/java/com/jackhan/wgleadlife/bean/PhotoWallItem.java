package com.jackhan.wgleadlife.bean;

import android.graphics.Color;

public class PhotoWallItem {

	public PhotoWallItem(String url, String title) {
		super();
		this.url = url;
		this.title = title;
	}

	private String url;
	private String title;

	private int height;
	private int width;

	private int textColor;

	public int getTextColor() {
		if (textColor <= 0)
			return Color.WHITE;
		else
			return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isSizeNull() {
		return height == 0 || width == 0;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
}
