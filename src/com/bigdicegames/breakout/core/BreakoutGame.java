package com.bigdicegames.breakout.core;

import static forplay.core.ForPlay.graphics;
import static forplay.core.ForPlay.mouse;
import forplay.core.AssetWatcher;
import forplay.core.Game;
import forplay.core.Mouse;
import forplay.core.SurfaceLayer;
import static forplay.core.ForPlay.log;

public class BreakoutGame implements Game, Mouse.Listener {

	private SurfaceLayer surfaceLayer;
	private Paddle paddle;
	private AssetWatcher watcher;
	private boolean mouseDown;
	private Ball ball;
	private BrickManager brickManager;
	private boolean inPlay;

	@Override
	public void init() {
	    graphics().setSize(800, 600);
	    surfaceLayer = graphics().createSurfaceLayer(graphics().width(), graphics().height());
		graphics().rootLayer().add(surfaceLayer);
		
        watcher = new AssetWatcher(new AssetWatcher.Listener() {
		@Override
          public void error(Throwable e) {
            log().error(e.getMessage());
          }

          @Override
          public void done() {
            log().info("all assets loaded");
            startGame();
          }
        });
        paddle = new Paddle();
        paddle.loadAssets(watcher);
        ball = new Ball();
        ball.loadAssets(watcher);
        brickManager = new BrickManager();
        Brick.loadAssets(watcher);
        watcher.start();
        
	    mouse().setListener(this);
	}

	protected void startGame() {
		brickManager.setup();
		ball.reset();
		inPlay = true;
	}

	@Override
	public void update(float deltaMilliseconds) {
		float deltaSeconds = deltaMilliseconds / 1000.0f;
		
		if (inPlay) {
			ball.update(deltaSeconds, paddle, brickManager);
			if (!ball.isInBounds() || brickManager.isDone()) {
				brickManager.clear();
				inPlay = false;
			}
		}
	}

	@Override
	public void paint(float alpha) {
    	surfaceLayer.surface().clear();
    	surfaceLayer.surface().setFillColor(0xff444444);
    	surfaceLayer.surface().fillRect(0, 0, graphics().width(), graphics().height());
        paddle.draw(surfaceLayer.surface());
        ball.draw(surfaceLayer.surface());
        brickManager.draw(surfaceLayer.surface());
	}

	@Override
	public int updateRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onMouseDown(float x, float y, int button) {
	}

	@Override
	public void onMouseUp(float x, float y, int button) {
		if (!inPlay) {
			startGame();
		}
	}

	@Override
	public void onMouseMove(float x, float y) {
		paddle.setPositionX(x);
	}

	@Override
	public void onMouseWheelScroll(float velocity) {
		// TODO Auto-generated method stub
		
	}

}
