package com.bigdicegames.breakout.core;

import java.util.ArrayList;

import forplay.core.Surface;

public class BrickManager {
	private ArrayList<Brick> bricks;
	public BrickManager() {
		bricks = new ArrayList<Brick>();
	}
	
	public void setup() {
		bricks.add(new Brick(50, 100));
		bricks.add(new Brick(150, 100));
		bricks.add(new Brick(250, 100));
		bricks.add(new Brick(350, 100));
		bricks.add(new Brick(450, 100));
		bricks.add(new Brick(550, 100));
		bricks.add(new Brick(650, 100));
		bricks.add(new Brick(750, 100));
	}
	
	public void clear() {
		bricks.clear();
	}
	
	public boolean isDone() {
		return (bricks.size() == 0);
	}
	
	public void draw(Surface surface) {
		for(Brick b:bricks) {
			b.draw(surface);
		}
	}
	
	public boolean collide(float x, float y) {
		for (Brick b:bricks) {
			if (b.inside(x, y)) {
				bricks.remove(b);
				return true;
			}
		}
		return false;
	}
}
