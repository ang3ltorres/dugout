package io.github.ang3ltorres.dugout.level;

import com.badlogic.gdx.graphics.g2d.Batch;

import io.github.ang3ltorres.dugout.Main;

import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Timer;

import io.github.ang3ltorres.dugout.Assets;

public class Level
{
	public int width;
	public int height;
	public short[] levelData;
	public TaskPush taskPush;

	int firstLine = 34;

	public Level()
	{
		width = 16;
		height = 8;

		levelData = new short[width * height];

		byte block = 1;
		byte variant = (byte) 1;

		short def   = (short) (((block & 0xFF) << 8) | (variant & 0xFF) << 0);
		short empty = (short) (((0     & 0xFF) << 8) | (0 & 0xFF)       << 0);

		for (int i = 0; i < width * height; i++)
			levelData[i] = def;

		levelData[0 * width + 0] = empty;
		levelData[0 * width + 1] = empty;
		levelData[0 * width + 2] = empty;
		levelData[0 * width + 3] = empty;
		levelData[0 * width + 4] = empty;
		levelData[0 * width + 5] = empty;
		levelData[0 * width + 6] = empty;
		levelData[0 * width + 7] = empty;

		levelData[1 * width + 2] = empty;
		levelData[1 * width + 3] = empty;
		levelData[1 * width + 4] = empty;
		levelData[1 * width + 5] = empty;

		taskPush = new TaskPush();
		Timer.schedule(taskPush, 1, 0.2f);
		// taskPush.cancel();
	}

	public void draw(Batch batch)
	{

		for (int x = 0; x < width; x++)
		for (int y = 0; y < height; y++)
		{
			short packed = levelData[((firstLine + y) % height) * width + x];
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
			firstLine++;
		}
	}
}
