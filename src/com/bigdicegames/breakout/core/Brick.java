package com.bigdicegames.breakout.core;

import static forplay.core.ForPlay.assetManager;
import forplay.core.AssetWatcher;
import forplay.core.Image;
import forplay.core.ResourceCallback;
import forplay.core.Surface;

public class Brick {
	private static Image brickImage;
	private static int centerX;
	private static int centerY;
	
	private float positionX;
	private float positionY;
	
	public Brick(float x, float y) {
		positionX = x;
		positionY = y;
	}

	public static void loadAssets(AssetWatcher watcher) {
		brickImage = assetManager().getImage("images/brick.png");
		watcher.add(brickImage);
		brickImage.addCallback(new ResourceCallback<Image>() {
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
		surface.drawImage(brickImage, positionX - centerX, positionY - centerY);
	}
	
	public boolean inside(float x, float y) {
		return (Math.abs(positionX - x) < centerX &&
				Math.abs(positionY - y) < centerY);
	}
}
