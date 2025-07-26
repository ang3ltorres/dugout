package io.github.ang3ltorres.dugout.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Timer;

import io.github.ang3ltorres.dugout.Assets;

public class Level
{
	public int width;
	public int height;
	public int row;
	public short[] levelData;
	public TaskPush taskPush;

	public final short blockDefault = (short) (((1 & 0xFF) << 8) | (1 & 0xFF) << 0);
	public final short blockEmpty   = (short) (((0 & 0xFF) << 8) | (0 & 0xFF) << 0);

	public Level()
	{
		width = 16;
		height = 16;
		row = 0;

		levelData = new short[width * height];

		for (int i = 0; i < width * height; i++)
			levelData[i] = blockDefault;

		taskPush = new TaskPush();
		Timer.schedule(taskPush, 1, 1.0f);
		// taskPush.cancel();
	}

	public int getBlock(int x, int y)
	{
		return ((y + row) % height) * width + x;
	}

	public void draw(Batch batch)
	{

		for (int x = 0; x < width; x++)
		for (int y = 0; y < height; y++)
		{
			short packed = levelData[getBlock(x, y)];
			byte block   = (byte) ((packed >> 8) & 0xFF);
			byte variant = (byte) ((packed >> 0) & 0xFF);

			if (block == 1)
				batch.draw(Assets.level.region[0], x * 32, y * 32);
		}
	}

	public class TaskPush extends Task
	{
		@Override
		public void run()
		{
			System.out.println("** Pushing **");

			for (int x = 0; x < width; x++)
				levelData[getBlock(x, height)] = (Math.random() > 0.5f) ? blockDefault : blockEmpty;

			row++;
		}
	}
}
