package io.github.ang3ltorres.dugout;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets
{
  private Assets() { }

  public static class ResourceDraw
  {
    public Texture texture;
    public TextureRegion[] region;
  }

  public static ResourceDraw level;

  public static TextureRegion loadTexture(Texture texture, int x, int y, int width, int height)
  {
    TextureRegion textureRegion = new TextureRegion(texture);
    textureRegion.setRegion(x, y, width, height);
    textureRegion.flip(false, true);
    return textureRegion;
  }

  static
  {
    level = new ResourceDraw();
    level.texture = new Texture("png/dirt.png");

    level.region = new TextureRegion[2];
    level.region[0] = loadTexture(level.texture, 0, 0, 32, 32);
    level.region[1] = loadTexture(level.texture, 32, 0, 32, 32);
  }
}