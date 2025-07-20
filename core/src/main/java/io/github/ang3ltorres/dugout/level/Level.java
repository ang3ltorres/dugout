package io.github.ang3ltorres.dugout.level;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Level
{
	public String name;
	public int width;
	public int height;
	public int[] levelData;
	
	public abstract void draw(Batch batch);
};
