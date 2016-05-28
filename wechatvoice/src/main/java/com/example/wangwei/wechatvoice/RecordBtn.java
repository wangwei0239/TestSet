package com.example.wangwei.wechatvoice;

import java.security.KeyStore.PrivateKeyEntry;


import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class RecordBtn extends Button implements AudioManager.AudioStateListener {

	private static final int STATE_NORMAL = 1;
	private static final int STATE_RECORDING = 2;
	private static final int STATE_WANT_TO_CANCEL = 3;
	private static final int DISTANCE_Y_CANCEL = 10;
	private int mCurState = 1;
	private boolean isRecording = false;

	private DialogManager mDialogManager;

	private AudioManager mAudioManager;
	
	private float mTime;
	//是否触发longclick
	private boolean mReady;

	public RecordBtn(Context context) {
		this(context, null);
	}

	public RecordBtn(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RecordBtn(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mDialogManager = new DialogManager(getContext());

		String dir = Environment.getExternalStorageDirectory()+"/audio";
		mAudioManager = AudioManager.getInstance(dir);
		mAudioManager.setOnAudioStateListener(this);

		setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				mReady = true;
				mAudioManager.prepareAudio();
				return false;
			}
		});
	}
	
	/**
	 * 录音完成后的回调
	 * @author Administrator
	 *
	 */
	public interface AudioFinishRecorderListener
	{
		void onFinish(float seconds, String filePath);
	}
	
	private AudioFinishRecorderListener mListener;
	
	public void setAudioFinishRecorderListener(AudioFinishRecorderListener listener)
	{
		mListener = listener;
	}

	private Runnable mGetVoiceLevelRunnable = new Runnable() {
		
		@Override
		public void run() {
			while(isRecording)
			{
				try {
					Thread.sleep(100);
					mTime += 0.1f;
					mHandler.sendEmptyMessage(MSM_VOICE_CHANGED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	private static final int MSM_AUDIO_PREPARED = 0x110;
	private static final int MSM_VOICE_CHANGED = 0x111;
	private static final int MSM_DIALOG_DISMISS = 0x112;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSM_AUDIO_PREPARED:
				mDialogManager.showRecordingDialog();
				isRecording = true;
				new Thread(mGetVoiceLevelRunnable).start();
				break;
				
			case MSM_VOICE_CHANGED:
				mDialogManager.updateVoiceLevel(mAudioManager.getVoiceLevel(7));
				break;
				
			case MSM_DIALOG_DISMISS:
				mDialogManager.dismissDialog();
				break;
			default:
				break;
			}
		};
	};

	@Override
	public void wellPrepared() {
		mHandler.sendEmptyMessage(MSM_AUDIO_PREPARED);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			changeState(STATE_RECORDING);
			break;
		case MotionEvent.ACTION_MOVE:

			if (isRecording)

				if (wantToCancel(x, y)) {
					changeState(STATE_WANT_TO_CANCEL);
				} else {
					changeState(STATE_RECORDING);
				}

			break;
		case MotionEvent.ACTION_UP:
			
			if(!mReady){
				reset();
				return super.onTouchEvent(event);
			}
			
			if(!isRecording||mTime<0.6f)
			{
				mDialogManager.tooShort();
				mAudioManager.cancel();
				mHandler.sendEmptyMessageDelayed(MSM_DIALOG_DISMISS,1300);
			}else if (mCurState == STATE_RECORDING) {
				// release
				mDialogManager.dismissDialog();
				mAudioManager.release();
				// callback
				if(mListener != null)
				{
					mListener.onFinish(mTime, mAudioManager.getCurrentFilePath());
				}
				
			} else if (mCurState == STATE_WANT_TO_CANCEL) {
				mDialogManager.dismissDialog();
				mAudioManager.cancel();
			}
			
			reset();

			break;
		}

		return super.onTouchEvent(event);
	}

	private void reset() {
		System.out.println("reset");
		isRecording = false;
		mTime = 0;
		mReady = false;
		changeState(STATE_NORMAL);
	}

	private boolean wantToCancel(int x, int y) {
		System.out.println("width:" + getWidth() + " x:" + x);
		if (x < 0 || x > getWidth()) {
			return true;
		}
		if (y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL) {
			return true;
		}
		return false;
	}

	private void changeState(int stateRecording) {
		if (mCurState != stateRecording) {
			mCurState = stateRecording;
			switch (stateRecording) {
			case STATE_NORMAL:
				setBackgroundResource(R.drawable.btn_bg);
				setText(R.string.str_recorder_normal);

				break;
			case STATE_RECORDING:
				setBackgroundResource(R.drawable.btn_bg_recording);
				setText(R.string.str_recorder_recording);
				if (isRecording) {
					mDialogManager.recording();
				}
				break;
			case STATE_WANT_TO_CANCEL:
				setBackgroundResource(R.drawable.btn_bg_recording);
				setText(R.string.str_recorder_want_cancel);
				mDialogManager.wantToCancel();
				break;

			default:
				break;
			}
		}
	}

}
