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
			levelData[i] = (Math.random() > 0.8f) ? blockEmpty : blockEmpty;

		taskPush = new TaskPush();
		Timer.schedule(taskPush, 1, pushSpeed);
		// taskPush.cancel();
  }

	public static int getBlock(int x, int y)
	{
		return ((y + row) % height) * width + x;
	}

	public static boolean collision(Rectangle rect)
	{
		// TODO
		//* Only take possible overlapping blocks
		//* When x=32,y=32,w=32,h=32, Still cheking for 4 blocks
		//* Instead of just one

		int startX = Math.floorDiv((int) rect.x, 32);
		int endX = startX + (int) Math.ceil(rect.width / 32);

		int startY = Math.floorDiv((int) rect.y, 32);
		int endY = startY + (int) Math.ceil(rect.height / 32);

		// System.out.println(String.format("X:(%d - %d) Y:(%d - %d)", startX, endX, startY, endY));

		for (int x = startX; x <= endX; x++)
		for (int y = startY; y <= endY; y++)
		{
			short packed = levelData[getBlock(x, y)];
			byte block   = (byte) ((packed >> 8) & 0xFF);

			if (block == 1)
				return true;
		}

		return false;
	}

	public static void draw(Batch batch)
	{
		for (int x = 0; x < width; x++)
		for (int y = 0; y < height; y++)
		{
			short packed = levelData[getBlock(x, y)];
			byte block   = (byte) ((packed >> 8) & 0xFF);
			// byte variant = (byte) ((packed >> 0) & 0xFF);

			if (block == 1)
				batch.draw(Assets.level.region[0], x * 32, y * 32);
		}
	}

	public static class TaskPush extends Task
	{
		@Override
		public void run()
		{

			for (int x = 0; x < width; x++)
				levelData[getBlock(x, height)] = (Math.random() > 0.8f) ? blockDefault : blockEmpty;

			row++;
		}
	}
}
