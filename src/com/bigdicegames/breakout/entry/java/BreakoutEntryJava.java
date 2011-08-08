package com.bigdicegames.breakout.entry.java;

import com.bigdicegames.breakout.core.BreakoutGame;

import forplay.core.ForPlay;
import forplay.java.JavaAssetManager;
import forplay.java.JavaPlatform;

public class BreakoutEntryJava {
	public static void main(String[] args) {
		JavaAssetManager assets = JavaPlatform.register().assetManager();
		assets.setPathPrefix("src/com/bigdicegames/breakout/resources");
		ForPlay.run(new BreakoutGame());
	}
}
