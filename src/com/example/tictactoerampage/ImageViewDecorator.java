package com.example.tictactoerampage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageViewDecorator extends ImageView {
	private ImageView imageView;
	public ImageViewDecorator(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public ImageViewDecorator(ImageView imageview) {
		super(imageview.getContext());
		this.imageView = imageview;
	}

	private final Integer UNKNOW_CONST = 28998; 
	
	public Integer getIndex() {
		return this.getId() - UNKNOW_CONST;
	}
	
	public void setImageDrawable(Drawable dw) {
		this.imageView.setImageDrawable(dw);
	}
	
	public void setEnabled(boolean en) {
		this.imageView.setEnabled(en);
	}
	
	public boolean isEnabled() {
		return this.imageView.isEnabled();
	}
}
