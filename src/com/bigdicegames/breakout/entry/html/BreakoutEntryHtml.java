package com.bigdicegames.breakout.entry.html;

import com.bigdicegames.breakout.core.BreakoutGame;

import forplay.core.ForPlay;
import forplay.html.HtmlAssetManager;
import forplay.html.HtmlGame;
import forplay.html.HtmlPlatform;

public class BreakoutEntryHtml extends HtmlGame {
	@Override
	public void start() {
		HtmlAssetManager assets = HtmlPlatform.register().assetManager();
		assets.setPathPrefix("breakout/");
		ForPlay.run(new BreakoutGame());
	}
}
