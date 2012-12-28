package de.philipphock.android.androidBasics.ext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class BlobView extends SurfaceView implements SurfaceHolder.Callback, OnTouchListener, Runnable{

	public SparseArray<Point> activePointers ;
	
	private static final int RADX=60;
	private static final int RADY=90;
	
	
	private static final Paint blobPaint = new Paint();
	private static final Paint eraserPaint = new Paint();
	static{
		blobPaint.setStyle(Paint.Style.FILL);
		blobPaint.setStrokeWidth(1);
		blobPaint.setColor(Color.WHITE);
		
		eraserPaint.setStyle(Paint.Style.FILL);
		eraserPaint.setColor(Color.BLACK);
	}
	private volatile boolean running = true;

	public BlobView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		getHolder().addCallback(this);
		setOnTouchListener(this);
		activePointers = new SparseArray<Point>();
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		running=true;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		running=false;
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		synchronized (activePointers) {
			
		
			Log.d("touch", event.toString());
			switch (event.getAction()){
				case MotionEvent.ACTION_DOWN:
					activePointers.put(0, new Point((int) event.getX(),(int)event.getY()));
				break;
				
				case MotionEvent.ACTION_UP:
					activePointers.removeAt(0);
				break;
				
				case MotionEvent.ACTION_MOVE:
					Point p = activePointers.get(0);
					if (p==null)break;
					p.x=(int)event.getX();
					p.y=(int)event.getY();
					
					
				break;
				
			}
		}
		return true;
	}

	@Override
	public void run() {
		Log.d("thread","starting loop");
		while(running){
			if (getHolder().getSurface().isValid()){
				Canvas canvas = getHolder().lockCanvas();
				if (canvas == null){
					running=false;
					break;
				}
				//canvas.drawRect(0,0,10,10,blobPaint);
				canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), eraserPaint);
				Point p = activePointers.get(0);
				if (p!=null){
					canvas.drawOval(new RectF(p.x-RADX,p.y-RADY,p.x+RADX,p.y+RADY),blobPaint);
				}
				
				getHolder().unlockCanvasAndPost(canvas);
			}
		}
		Log.d("thread","stopping loop");
	}

}

