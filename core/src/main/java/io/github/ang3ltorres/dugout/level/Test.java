package io.github.ang3ltorres.dugout.level;

import com.badlogic.gdx.graphics.g2d.Batch;

import io.github.ang3ltorres.dugout.Main;

public class Test extends Level
{
	public Test()
	{
		name = "Test Level";
		width = 8;
		height = 8;

		levelData = new int[width * height];

		byte block = 1;
		byte data2 = (byte) 255;
		byte data3 = (byte) 255;
		byte data4 = (byte) 128;

		for (int i = 0; i < width * height; i++) {

			if (i % 2 == 0)
				levelData[i] =	((block & 0xFF) << 24) |
												((data2 & 0xFF) << 16) |
									  		((data3 & 0xFF) << 8)  |
										  	((data4 & 0xFF));
		}

		// int packed = levelData[i];
		// byte block = (byte) ((packed >> 24) & 0xFF);
		// byte data2 = (byte) ((packed >> 16) & 0xFF);
		// byte data3 = (byte) ((packed >> 8)  & 0xFF);
		// byte data4 = (byte) (packed & 0xFF);
	}

	@Override
	public void draw(Batch batch)
	{
		for (int x = 0; x < width; x++)
		for (int y = 0; y < height; y++)
		{
			int xPos = x * 32;
			int yPos = y * 32;

			int packed = levelData[y * width + x];
			byte block = (byte) ((packed >> 24) & 0xFF);
			byte data2 = (byte) ((packed >> 16) & 0xFF);
			byte data3 = (byte) ((packed >> 8)  & 0xFF);
			byte data4 = (byte) (packed & 0xFF);

			if (block == 1)
				batch.draw(Main.assets.level.region[0], xPos, yPos);
		}
	}
}
