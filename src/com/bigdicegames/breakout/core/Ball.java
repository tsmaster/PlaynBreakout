package com.bigdicegames.breakout.core;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;
import forplay.core.AssetWatcher;
import forplay.core.Image;
import forplay.core.ResourceCallback;
import forplay.core.Surface;

public class Ball {
	private Image ballImage;
	private int centerX;
	private int centerY;
	
	private float positionX;
	private float positionY;
	
	private float velocityX;
	private float velocityY;
	
	private boolean inBounds;
	
	public Ball() {
		reset();
	}
	
	public void reset() {
		positionX = 400.0f;
		positionY = 300.0f;
		
		velocityX = 100.0f;
		velocityY = 100.0f;
		inBounds = true;
	}

	public void loadAssets(AssetWatcher watcher) {
		ballImage = assetManager().getImage("images/ball.png");
		watcher.add(ballImage);
		ballImage.addCallback(new ResourceCallback<Image>() {
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
		surface.drawImage(ballImage, positionX - centerX, positionY - centerY);
	}

	public void update(float deltaSeconds, Paddle paddle, BrickManager brickManager) {
		positionX += velocityX * deltaSeconds;
		positionY += velocityY * deltaSeconds;
		
		if (positionY > paddle.getPositionY()) {
			if (Math.abs(positionX - paddle.getPositionX()) < paddle.getWidth() / 2) {
				positionY = paddle.getPositionY();
				velocityY *= -1;
			} else {
				inBounds = false;
			}
		}
		
		if (positionY < 0) {
			velocityY *= -1;
			positionY = 0;
		}
		if (positionX < 0) {
			velocityX *= -1;
			positionX = 0;
		}
		if (positionX > graphics().width()) {
			velocityX *= -1;
			positionX = graphics().width();
		}
		
		if (brickManager.collide(positionX, positionY)) {
			velocityY *= -1;
		}
	}
	
	public boolean isInBounds() {
		return inBounds;
	}
}
