package laz.mateo.notcom.com.subhunter;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

public class SubHunter extends Activity {
    int numberHorizontalPixels;
    int numberVerticalPixels;
    int blockSize;
    int gridWidth = 40;
    int gridHeight;
    float horizontalTouched = -100;
    float verticalTouched = -100;
    int subHorizontalPosition;
    int subVerticalPosition;
    boolean hit = false;
    int shotsTaken;
    int distanceFromSub;
    boolean debugging = true;

    ImageView gameView;
    Bitmap blankBitmap;
    Canvas canvas;
    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        // Initialize our size based variables
        // based on the screen resolution
        numberHorizontalPixels = size.x;
        numberVerticalPixels = size.y;
        blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / blockSize;

        blankBitmap = Bitmap.createBitmap(numberHorizontalPixels, numberVerticalPixels, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(blankBitmap);
        gameView = new ImageView(this);
        paint = new Paint();

        Log.d("Debugging", "In onCreate");
        newGame();
        draw();
    }

    /*        This code will execute when a new
               game needs to be started. It will
               happen when the app is first started
             and after the player wins a game.
             */
    protected void newGame() {
        Log.d("Debugging", "In newGame");
        Random random = new Random();
        subHorizontalPosition = random.nextInt(gridWidth);
        subVerticalPosition = random.nextInt(gridHeight);
        shotsTaken = 0;
    }

    /*        Here we will do all the drawing.
            The grid lines, the HUD and
                    the touch indicator
                    */

    protected void draw() {
        gameView.setImageBitmap(blankBitmap);
        // Wipe the screen with a whitecolor
        canvas.drawColor(Color.argb(255, 255, 255, 255));

        // Change the paint color to black
        paint.setColor(Color.argb(255, 0, 0, 0));
        // Draw the vertical lines of the grid
        canvas.drawLine(blockSize, 0, blockSize, numberVerticalPixels - 1, paint);
        // Draw the horizontal lines of the grid
        canvas.drawLine(0, blockSize, numberHorizontalPixels - 1, blockSize, paint);

        // Re-size the text appropriate for the
        // score and distance text
        paint.setTextSize(blockSize * 2);
        paint.setColor(Color.argb(255, 0, 0, 255));
        canvas.drawText("Shots Taken: " + shotsTaken + "  Distance: " + distanceFromSub,
                blockSize,
                blockSize * 1.75f,
                paint);

        Log.d("Debugging", "In draw");
        printDebuggingText();
    }

     /*        This part of the code will
             handle detecting that the player
                     has tapped the screen
                     */

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d("Debugging", "In onTouchEvent");
        takeShot();
        return true;
    }

     /*        The code here will execute when
             the player taps the screen. It will
             calculate the distance from the sub'
             and decide a hit or miss
              */

    protected void takeShot() {
        Log.d("Debugging", "In takeShot");
        draw();
    }

    // This code says "BOOM!"

    protected void boom() {

    }


    // This code prints the debugging text

    protected void printDebuggingText() {
        paint.setTextSize(blockSize);
        canvas.drawText("numberHorizontalPixels = " + numberHorizontalPixels, 50, blockSize * 3, paint);
        canvas.drawText("numberVerticalPixels = " + numberVerticalPixels, 50, blockSize * 4, paint);
        canvas.drawText("blockSize = " + blockSize, 50, blockSize * 5, paint);
        canvas.drawText("gridWidth = " + gridWidth, 50, blockSize * 6, paint);
        canvas.drawText("gridHeight = " + gridHeight, 50, blockSize * 7, paint);
        canvas.drawText("horizontalTouched = " + horizontalTouched, 50, blockSize * 8, paint);
        canvas.drawText("verticalTouched = " + verticalTouched, 50, blockSize * 9, paint);
        canvas.drawText("subHorizontalPosition = " + subHorizontalPosition, 50, blockSize * 10, paint);
        canvas.drawText("subVerticalPosition = " +subVerticalPosition, 50,blockSize * 11, paint);
        canvas.drawText("hit = " + hit,50, blockSize * 12, paint);
        canvas.drawText("shotsTaken = " +shotsTaken,50, blockSize * 13, paint);
        canvas.drawText("debugging = " + debugging,50, blockSize * 12, paint);

        Log.d("numberHorizontalPixels", "" + numberHorizontalPixels);
        Log.d("numberVerticalPixels", "" + numberVerticalPixels);
        Log.d("blockSize", "" + blockSize);
        Log.d("gridWidth", "" + gridWidth);
        Log.d("gridHeight", "" + gridHeight);
        Log.d("horizontalTouched", "" + horizontalTouched);
        Log.d("verticalTouched", "" + verticalTouched);
        Log.d("subHorizontalPosition", "" + subHorizontalPosition);
        Log.d("subVerticalPosition", "" + subVerticalPosition);
        Log.d("hit", "" + hit);
        Log.d("shotsTaken", "" + shotsTaken);
        Log.d("debugging", "" + debugging);
        Log.d("distanceFromSub", "" + distanceFromSub);
    }
}
