package io.github.ang3ltorres.dugout.lwjgl3;

import com.badlogic.gdx.Version;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3NativesLoader;

import io.github.ang3ltorres.dugout.Main;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
  public static void main(String[] args)
  {
    String osName = System.getProperty("os.name").toLowerCase();

    if (osName.contains("windows"))
    {
      String programData = System.getenv("ProgramData");
      
      if (programData == null)
        programData = "C:\\Temp\\";

      String prevTmpDir = System.getProperty("java.io.tmpdir", programData);
      String prevUser = System.getProperty("user.name", "libGDX_User");
      System.setProperty("java.io.tmpdir", programData + "/libGDX-temp");
      System.setProperty("user.name", ("User_" + prevUser.hashCode() + "_GDX" + Version.VERSION).replace('.', '_'));
      Lwjgl3NativesLoader.load();
      System.setProperty("java.io.tmpdir", prevTmpDir);
      System.setProperty("user.name", prevUser);
    }

    createApplication();
  }

  private static Lwjgl3Application createApplication()
  {
    return new Lwjgl3Application(new Main(), getDefaultConfiguration());
  }

  private static Lwjgl3ApplicationConfiguration getDefaultConfiguration()
  {
    Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
    configuration.setTitle("dugout");
    configuration.useVsync(false);
    configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
    configuration.setWindowedMode(1280, 720);
    //configuration.setDecorated(false);
    configuration.setBackBufferConfig(8, 8, 8, 8, 16, 2, 0);
    configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
    return configuration;
  }
}