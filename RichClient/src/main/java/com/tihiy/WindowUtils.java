package com.tihiy;

import com.google.common.base.Preconditions;

import java.awt.*;

public class WindowUtils {
  private WindowUtils() {
    throw new AssertionError();
  }

  /**
   * Return a <code>Dimension</code> whose size is defined not in terms of
   * pixels, but in terms of a given percent of the screen's width and height.
   * <p/>
   * <p/>
   * Use to set the preferred size of a component to a certain percentage of
   * the screen.
   *
   * @param percentWidth  percentage width of the screen, in range
   *                      <code>1..100</code>.
   * @param percentHeight percentage height of the screen, in range
   *                      <code>1..100</code>.
   */
  public static Dimension getDimensionFromPercent(int percentWidth, int percentHeight) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return calcDimensionFromPercent(screenSize, percentWidth, percentHeight);
  }

  /**
   * Pack the window, center it on the screen, and set the window visible.
   *
   * @param window the window to center and show.
   */
  public static void centerOnScreenAndSetVisible(Window window) {
    centerOnScreen(window);
    window.setVisible(true);
  }

  /**
   * Take the window and center it on the screen.
   * <p/>
   * This works around a bug in setLocationRelativeTo(...): it currently does
   * not take multiple monitors into accounts on all operating systems.
   *
   * @param window the window to center
   */
  public static void centerOnScreen(Window window) {
    Preconditions.checkNotNull(window, "window cannot be null");
    // This works around a bug in setLocationRelativeTo(...): it currently
    // does not take multiple monitors into accounts on all operating
    // systems.
    try {
      // Note that if this is running on a JVM prior to 1.4, then an
      // exception will be thrown and we will fall back to
      // setLocationRelativeTo(...).
      Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

      Dimension windowSize = window.getSize();
      int x = screenBounds.x + ((screenBounds.width - windowSize.width) / 2);
      int y = screenBounds.y + ((screenBounds.height - windowSize.height) / 2);
      window.setLocation(x, y);
    }
    catch (Throwable t) {
      window.setLocationRelativeTo(window);
    }
  }

  private static Dimension calcDimensionFromPercent(Dimension dimension, int percentWidth, int percentHeight) {
    int width = dimension.width * percentWidth / 100;
    int height = dimension.height * percentHeight / 100;
    return new Dimension(width, height);
  }
}