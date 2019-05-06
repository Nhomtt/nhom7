package com.phongbm.smartclock;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	public static final String TAG = "MainActivity";

	private static final int UPDATE_CURRET_TIME = 1;
	private static final int RUN_SCHEDULE_TIME = 2;
	private static final int RUN_COUNT_TIME = 3;

	private RelativeLayout layoutCurrentTime, layoutScheduleTime,
			layoutCountTime;
	private Button btnCurrentTime, btnScheduleTime, btnCountTime,
			btnStartScheduleTime, btnResetScheduleTime, btnOKScheduleTime,
			btnStartCountTime, btnStopCountTime, btnLapCountTime,
			btnContinueCountTime, btnResetCountTime;
	private TextView txtAMPM, txtCurrentTime, txtSecond, txtCurrentDate,
			txtCountTime, txtCountTimePercent;
	private EditText edtHour, edtMinute, edtSecond;
	private ProgressBar progressBarScheduleTime;
	private ListView listViewTimeLap;
	private ArrayList<InforTimeLap> inforTimeLaps;
	private InforTimeLapAdapter inforTimeLapAdapter;
	private Thread threadCurrentTime, threadCountTime, threadScheduleTime;
	private boolean timeRunning = true, countingTime = false,
			schedulingTime = false;
	private int hourSchedule, minuteSchedule, secondSchedule, hourCount = 0,
			minuteCount = 0, secondCount = 0, countClickScheduleTime = 0,
			timePercent = 0, countTimePercent = 0, progressBarStatus = 0,
			indexOfItemTime = 0;
	private long miliis = 0;
	private Sound sound;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case UPDATE_CURRET_TIME:
				txtAMPM.setText(DateAndTime.getAMPM());
				txtCurrentTime.setText(DateAndTime.getTime());
				txtSecond.setText(DateAndTime.getSecond());
				txtCurrentDate.setText(DateAndTime.getDate());
				break;

			case RUN_SCHEDULE_TIME:
				edtHour.setText(hourSchedule < 10 ? "0" + hourSchedule
						: hourSchedule + "");
				edtMinute.setText(minuteSchedule < 10 ? "0" + minuteSchedule
						: minuteSchedule + "");
				edtSecond.setText(secondSchedule < 10 ? "0" + secondSchedule
						: secondSchedule + "");
				progressBarScheduleTime.setProgress(progressBarStatus);
				if (hourSchedule == 0 && minuteSchedule == 0
						&& secondSchedule == 0) {
					schedulingTime = false;
					countClickScheduleTime = 0;
					btnOKScheduleTime.setVisibility(RelativeLayout.VISIBLE);
					btnStartScheduleTime.setVisibility(RelativeLayout.GONE);
					btnResetScheduleTime.setVisibility(RelativeLayout.GONE);
					sound.playSoundLoop();
				}
				break;

			case RUN_COUNT_TIME:
				timePercent++;
				countTimePercent++;
				if (timePercent == 100) {
					timePercent = 0;
				}
				txtCountTimePercent.setText("." + timePercent);
				if (countTimePercent % 100 == 0) {
					countTimePercent = 0;
					secondCount++;
					if (secondCount == 60) {
						secondCount = 0;
						minuteCount++;
					}
					if (minuteCount == 60) {
						hourCount++;
					}
					txtCountTime.setText((hourCount < 10 ? ("0" + hourCount)
							: hourCount)
							+ ":"
							+ (minuteCount < 10 ? ("0" + minuteCount)
									: minuteCount)
							+ ":"
							+ (secondCount < 10 ? ("0" + secondCount)
									: secondCount));
				}
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initializeComponent();

		sound = new Sound(MainActivity.this);

		threadCurrentTime = new Thread(runCurrentTime);
		threadCurrentTime.start();

		inforTimeLaps = new ArrayList<InforTimeLap>();
		inforTimeLapAdapter = new InforTimeLapAdapter(MainActivity.this,
				R.layout.listview_item, inforTimeLaps);
		listViewTimeLap.setAdapter(inforTimeLapAdapter);
	}

	// private void getSizeScreen() {
	// DisplayMetrics metrics = new DisplayMetrics();
	// this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
	// widthScreen = metrics.widthPixels;
	// heightScreen = metrics.heightPixels;
	// Log.i(TAG, widthScreen + ", " + heightScreen);
	// }

	private void initializeComponent() {
		// Anh xa cac thanh phan cua tab menu
		btnCurrentTime = (Button) findViewById(R.id.btnCurrentTime);
		btnScheduleTime = (Button) findViewById(R.id.btnScheduleTime);
		btnCountTime = (Button) findViewById(R.id.btnCountTime);

		btnCurrentTime.setOnClickListener(MainActivity.this);
		btnScheduleTime.setOnClickListener(MainActivity.this);
		btnCountTime.setOnClickListener(MainActivity.this);

		// Anh xa cac thanh phan cua menu thoi gian hien tai
		layoutCurrentTime = (RelativeLayout) findViewById(R.id.layoutCurrentTime);
		txtAMPM = (TextView) findViewById(R.id.txtAMPM);
		txtCurrentTime = (TextView) findViewById(R.id.txtCurrentTime);
		txtSecond = (TextView) findViewById(R.id.txtSecond);
		txtCurrentDate = (TextView) findViewById(R.id.txtCurrentDate);

		txtCurrentTime.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/DigitalDismay.otf"));
		txtSecond.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/DigitalDismay.otf"));

		// Anh xa cac thanh phan cua menu bo hen gio
		layoutScheduleTime = (RelativeLayout) findViewById(R.id.layoutScheduleTime);
		edtHour = (EditText) findViewById(R.id.edtHour);
		edtMinute = (EditText) findViewById(R.id.edtMinute);
		edtSecond = (EditText) findViewById(R.id.edtSecond);
		btnStartScheduleTime = (Button) findViewById(R.id.btnStartScheduleTime);
		btnResetScheduleTime = (Button) findViewById(R.id.btnResetScheduleTime);
		btnOKScheduleTime = (Button) findViewById(R.id.btnOKScheduleTime);
		progressBarScheduleTime = (ProgressBar) findViewById(R.id.progressBarScheduleTime);

		edtHour.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/DigitalDismay.otf"));
		edtMinute.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/DigitalDismay.otf"));
		edtSecond.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/DigitalDismay.otf"));

		btnStartScheduleTime.setOnClickListener(MainActivity.this);
		btnResetScheduleTime.setOnClickListener(MainActivity.this);
		btnOKScheduleTime.setOnClickListener(MainActivity.this);

		// Anh xa cac thanh phan cua menu dong ho dem
		layoutCountTime = (RelativeLayout) findViewById(R.id.layoutCountTime);
		txtCountTime = (TextView) findViewById(R.id.txtCountTime);
		txtCountTimePercent = (TextView) findViewById(R.id.txtCountTimePercent);
		btnStartCountTime = (Button) findViewById(R.id.btnStartCountTime);
		btnStopCountTime = (Button) findViewById(R.id.btnStopCountTime);
		btnLapCountTime = (Button) findViewById(R.id.btnLapCountTime);
		btnContinueCountTime = (Button) findViewById(R.id.btnContinueCountTime);
		btnResetCountTime = (Button) findViewById(R.id.btnResetCountTime);
		listViewTimeLap = (ListView) findViewById(R.id.listViewTimeLap);

		txtCountTime.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/DigitalDismay.otf"));
		txtCountTimePercent.setTypeface(Typeface.createFromAsset(getAssets(),
				"fonts/DigitalDismay.otf"));

		btnStartCountTime.setOnClickListener(MainActivity.this);
		btnStopCountTime.setOnClickListener(MainActivity.this);
		btnLapCountTime.setOnClickListener(MainActivity.this);
		btnContinueCountTime.setOnClickListener(MainActivity.this);
		btnResetCountTime.setOnClickListener(MainActivity.this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		// Tab menu thoi gian hien tai
		case R.id.btnCurrentTime:
			if (!timeRunning) {
				timeRunning = true;
				threadCurrentTime = new Thread(runCurrentTime);
				threadCurrentTime.start();
			}
			stateTransitionTab(btnCurrentTime, btnScheduleTime, btnCountTime);
			stateTransitionLayout(layoutCurrentTime, layoutScheduleTime,
					layoutCountTime);
			break;

		// Tab menu bo hen gio
		case R.id.btnScheduleTime:
			timeRunning = false;
			btnScheduleTime.setBackgroundResource(R.drawable.background_tab1);
			btnScheduleTime.setTextColor(Color.BLACK);
			stateTransitionTab(btnScheduleTime, btnCurrentTime, btnCountTime);
			stateTransitionLayout(layoutScheduleTime, layoutCurrentTime,
					layoutCountTime);
			break;

		// Tab menu dong ho dem
		case R.id.btnCountTime:
			timeRunning = false;
			btnCountTime.setBackgroundResource(R.drawable.background_tab1);
			btnCountTime.setTextColor(Color.BLACK);
			stateTransitionTab(btnCountTime, btnCurrentTime, btnScheduleTime);
			stateTransitionLayout(layoutCountTime, layoutCurrentTime,
					layoutScheduleTime);
			break;

		// Button bat dau - bo hen gio
		case R.id.btnStartScheduleTime:
			countClickScheduleTime++;
			switch (countClickScheduleTime % 3) {
			case 1:
				if (!schedulingTime) {
					schedulingTime = true;
					hourSchedule = Integer.parseInt(edtHour.getText()
							.toString());
					minuteSchedule = Integer.parseInt(edtMinute.getText()
							.toString());
					secondSchedule = Integer.parseInt(edtSecond.getText()
							.toString());

					int totalSecond = hourSchedule * 3600 + minuteSchedule * 60
							+ secondSchedule;
					progressBarScheduleTime.setMax(totalSecond);
					progressBarScheduleTime.setSecondaryProgress(totalSecond);
					progressBarScheduleTime.setProgress(0);

					threadScheduleTime = new Thread(runScheduleTime);
					threadScheduleTime.start();
					changeBtnStartScheduleToStop();
					btnResetScheduleTime.setEnabled(true);
					btnResetScheduleTime
							.setBackgroundResource(R.drawable.background_button_reset);
					btnResetScheduleTime.setTextColor(Color.WHITE);
				}
				break;

			case 2:
				schedulingTime = false;
				btnStartScheduleTime
						.setBackgroundResource(R.drawable.background_button_start);
				btnStartScheduleTime.setText("Continue");
				btnStartScheduleTime.setTextColor(Color.parseColor("#2962ff"));
				break;
			case 0:
				schedulingTime = true;
				threadScheduleTime = new Thread(runScheduleTime);
				threadScheduleTime.start();
				changeBtnStartScheduleToStop();
				countClickScheduleTime++;
				break;
			}
			break;

		// Button xac lap lai - bo hen gio
		case R.id.btnResetScheduleTime:
			schedulingTime = false;
			countClickScheduleTime = 0;
			hourSchedule = 0;
			minuteSchedule = 0;
			secondSchedule = 0;
			edtHour.setText("00");
			edtMinute.setText("00");
			edtSecond.setText("00");
			changeStatusStart();
			break;

		// Button ok - bo hen gio
		case R.id.btnOKScheduleTime:
			sound.stopSound();
			changeStatusStart();
			break;

		// Button bat dau - dong ho dem
		case R.id.btnStartCountTime:
			if (!countingTime) {
				countingTime = true;
				threadCountTime = new Thread(runCountTime);
				threadCountTime.start();
				btnStartCountTime.setVisibility(RelativeLayout.GONE);
				btnStopCountTime.setVisibility(RelativeLayout.VISIBLE);
				btnLapCountTime.setVisibility(RelativeLayout.VISIBLE);
				miliis = System.currentTimeMillis();
			}
			break;

		// Button dung - dong ho dem
		case R.id.btnStopCountTime:
			countingTime = false;
			btnStopCountTime.setVisibility(RelativeLayout.GONE);
			btnLapCountTime.setVisibility(RelativeLayout.GONE);
			btnContinueCountTime.setVisibility(RelativeLayout.VISIBLE);
			btnResetCountTime.setVisibility(RelativeLayout.VISIBLE);
			break;

		// Button ghep thoi gian - dong ho dem
		case R.id.btnLapCountTime:
			indexOfItemTime++;
			String itemTime = txtCountTime.getText().toString();
			String itemPercentSecond = txtCountTimePercent.getText().toString();
			miliis = System.currentTimeMillis() - miliis;
			inforTimeLaps.add(0, new InforTimeLap(indexOfItemTime + "",
					itemTime, itemPercentSecond));
			miliis = 0;
			inforTimeLapAdapter.notifyDataSetChanged();
			break;

		// Button tiep tuc - dong ho dem
		case R.id.btnContinueCountTime:
			countingTime = true;
			threadCountTime = new Thread(runCountTime);
			threadCountTime.start();
			btnContinueCountTime.setVisibility(RelativeLayout.GONE);
			btnResetCountTime.setVisibility(RelativeLayout.GONE);
			btnStopCountTime.setVisibility(RelativeLayout.VISIBLE);
			btnLapCountTime.setVisibility(RelativeLayout.VISIBLE);
			break;

		// Button xac lap lai - dong ho dem
		case R.id.btnResetCountTime:
			countingTime = false;
			hourCount = 0;
			minuteCount = 0;
			secondCount = 0;
			timePercent = 0;
			txtCountTime.setText("00:00:00");
			txtCountTimePercent.setText(".00");
			indexOfItemTime = 0;
			inforTimeLaps.clear();
			inforTimeLapAdapter.notifyDataSetChanged();
			btnContinueCountTime.setVisibility(RelativeLayout.GONE);
			btnResetCountTime.setVisibility(RelativeLayout.GONE);
			btnStartCountTime.setVisibility(RelativeLayout.VISIBLE);
			break;
		}
	}

	private void stateTransitionTab(Button btn1, Button btn2, Button btn3) {
		btn1.setBackgroundResource(R.drawable.background_tab1);
		btn1.setTextColor(Color.WHITE);
		btn2.setBackgroundResource(R.drawable.background_tab2);
		btn2.setTextColor(Color.parseColor("#2962ff"));
		btn3.setBackgroundResource(R.drawable.background_tab2);
		btn3.setTextColor(Color.parseColor("#2962ff"));
	}

	private void stateTransitionLayout(RelativeLayout layout1,
			RelativeLayout layout2, RelativeLayout layout3) {
		layout1.setVisibility(RelativeLayout.VISIBLE);
		layout2.setVisibility(RelativeLayout.GONE);
		layout3.setVisibility(RelativeLayout.GONE);
	}

	private void changeBtnStartScheduleToStop() {
		btnStartScheduleTime
				.setBackgroundResource(R.drawable.background_button_stop);
		btnStartScheduleTime.setText("Stop");
		btnStartScheduleTime.setTextColor(Color.WHITE);
	}

	private void changeStatusStart() {
		progressBarStatus = 0;
		progressBarScheduleTime.setMax(100);
		progressBarScheduleTime.setProgress(50);
		progressBarScheduleTime.setSecondaryProgress(75);
		btnStartScheduleTime.setVisibility(RelativeLayout.VISIBLE);
		btnResetScheduleTime.setVisibility(RelativeLayout.VISIBLE);
		btnOKScheduleTime.setVisibility(RelativeLayout.GONE);
		btnStartScheduleTime
				.setBackgroundResource(R.drawable.background_button_start);
		btnStartScheduleTime.setText("Start");
		btnStartScheduleTime.setTextColor(Color.parseColor("#2962ff"));
		btnResetScheduleTime.setEnabled(false);
		btnResetScheduleTime
				.setBackgroundResource(R.drawable.background_button_reset_false);
		btnResetScheduleTime.setTextColor(getResources().getColor(
				R.color.grey_500));
	}

	private Runnable runCurrentTime = new Runnable() {
		@Override
		public void run() {
			while (timeRunning) {
				handler.sendEmptyMessage(UPDATE_CURRET_TIME);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private Runnable runScheduleTime = new Runnable() {
		@Override
		public void run() {
			while (schedulingTime) {
				if (secondSchedule > 0)
					secondSchedule--;
				if (secondSchedule == 0 && minuteSchedule > 0) {
					secondSchedule = 59;
					minuteSchedule--;
				}
				if (minuteSchedule == 0 && hourSchedule > 0) {
					minuteSchedule = 59;
					hourSchedule--;
				}
				progressBarStatus++;
				handler.sendEmptyMessage(RUN_SCHEDULE_TIME);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	private Runnable runCountTime = new Runnable() {
		@Override
		public void run() {
			while (countingTime) {
				handler.sendEmptyMessage(RUN_COUNT_TIME);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		timeRunning = false;
	};

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}