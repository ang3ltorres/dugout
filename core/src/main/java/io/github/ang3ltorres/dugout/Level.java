package io.github.ang3ltorres.dugout;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Timer;

public class Level
{
	private Level() { }

	public static int width;
	public static int height;
	public static int row;
	public static short[] levelData;
	public static TaskPush taskPush;

	public static float rowOffset = 0.0f;
	public static float pushSpeed = 1.0f;

	public static final short blockDefault = (short) (((1 & 0xFF) << 8) | (1 & 0xFF) << 0);
	public static final short blockEmpty   = (short) (((0 & 0xFF) << 8) | (0 & 0xFF) << 0);

  static
  {
		width = 16;
		height = 16;
		row = 0;

		levelData = new short[width * height];

		for (int i = 0; i < width * height; i++)
			levelData[i] = (Math.random() > 0.5f) ? blockDefault : blockEmpty;

		taskPush = new TaskPush();
		Timer.schedule(taskPush, 1, pushSpeed);
		// taskPush.cancel();
  }

	public static int getBlock(int x, int y)
	{
		return ((y + row) % height) * width + x;
	}

	public static boolean isColliding(Rectangle rect)
	{
		return false;
	}

	public static void draw(Batch batch)
	{
		rowOffset += Main.delta * (1.0f / 0.2f);
		if (rowOffset > 1.0f)
			rowOffset = 1.0f;

		for (int x = 0; x < width; x++)
		for (int y = 0; y < height; y++)
		{
			short packed = levelData[getBlock(x, y)];
			byte block   = (byte) ((packed >> 8) & 0xFF);
			byte variant = (byte) ((packed >> 0) & 0xFF);

			if (block == 1)
				batch.draw(Assets.level.region[0], x * 32, (y - rowOffset) * 32);
		}
	}

	public static class TaskPush extends Task
	{
		@Override
		public void run() {
			// System.out.println("** Pushing **");

			for (int x = 0; x < width; x++)
				levelData[getBlock(x, height)] = (Math.random() > 0.5f) ? blockDefault : blockEmpty;

			row++;
			rowOffset = 0.0f;
		}
	}
}
