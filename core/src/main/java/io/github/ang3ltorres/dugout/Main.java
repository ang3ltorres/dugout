package io.github.ang3ltorres.dugout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter
{
  private SpriteBatch batch;

  private Texture imageTexture;
  private TextureRegion image;

  private OrthographicCamera mainCamera;
  private OrthographicCamera bufferCamera;

  private FrameBuffer frameBuffer;
  private TextureRegion bufferRegion;

  @Override
  public void create()
  {
    batch = new SpriteBatch();

    imageTexture = new Texture("libgdx.png");
    imageTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    image = new TextureRegion(imageTexture);
    image.flip(false, true);

    frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, 800, 480, false);
    bufferRegion = new TextureRegion(frameBuffer.getColorBufferTexture());
    bufferRegion.flip(false, true);

    mainCamera = new OrthographicCamera(1280, 720);
    mainCamera.setToOrtho(true);

    bufferCamera = new OrthographicCamera(800, 480);
    bufferCamera.setToOrtho(false);
  }

  @Override
  public void render()
  {
    frameBuffer.begin();
    ScreenUtils.clear(0f, 0f, 0f, 1f);
    batch.setProjectionMatrix(bufferCamera.combined);
    batch.begin();
    batch.draw(image, 0, 0);
    batch.end();
    frameBuffer.end();

    ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    batch.setProjectionMatrix(mainCamera.combined);
    batch.begin();
    batch.draw(bufferRegion, 0, 0);
    batch.end();
  }

  @Override
  public void dispose()
  {
    batch.dispose();
    imageTexture.dispose();
    frameBuffer.dispose();
  }
}
