package com.bigdicegames.breakout.core;

import static forplay.core.ForPlay.assetManager;

import forplay.core.AssetWatcher;
import forplay.core.Image;
import forplay.core.ResourceCallback;
import forplay.core.Surface;

public class Paddle {
	private Image paddleImage;
	private int centerX;
	private int centerY;
	
	private int positionX;
	private int positionY;
	
	public Paddle() {
		positionX = 400;
		positionY = 550;
	}

	public void loadAssets(AssetWatcher watcher) {
		paddleImage = assetManager().getImage("images/paddle.png");
		watcher.add(paddleImage);
		paddleImage.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image resource) {
				centerX = resource.width() / 2;
				centerY = resource.height() / 2;
			}

			@Override
			public void error(Throwable err) {
				// TODO Auto-generated method stub
			}});
	}

	public void draw(Surface surface) {
		surface.drawImage(paddleImage, positionX - centerX, positionY - centerY);
	}

	public void setPositionX(float x) {
		positionX = (int) x;
	}

	public float getPositionX() {
		return positionX;
	}
	
	public float getPositionY() {
		return positionY;
	}

	public int getWidth() {
		return paddleImage.width();
	}
}
