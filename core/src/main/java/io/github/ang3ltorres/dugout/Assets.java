package io.github.ang3ltorres.dugout;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets
{
	public class ResourceDraw
	{
		public Texture texture;
		public TextureRegion[] region;
	}
	public ResourceDraw level;

	public Assets()
	{

		level = new ResourceDraw();
		level.texture = new Texture("png/dirt.png");
		
		level.region = new TextureRegion[1];
		
		level.region[0] = new TextureRegion(level.texture);
		level.region[0].setRegion(0, 0, 32, 32);
	}
}
