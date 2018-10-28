package com.example.kurodai0715.myasynctask;

import android.os.AsyncTask;
import android.util.Log;

public class BackgroundTask {
    /**
     * Listener.
     */
    public interface Listener {

        /**
         * 進捗状態通知.
         *
         * @param i iの現在値
         */
        void onProgress(final Integer i);

    }

    public static class MyAsyncTask extends AsyncTask<Object,Integer,Object> {
        // リスナー
        Listener mListener;

        // コンストラクタ
        MyAsyncTask(final Listener listener){
            mListener = listener;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            for (int i = 0; i < 10; i++) {
                String message;
                try {
                    message = "sleep executed " + (i+1) + " times.";
                    Thread.sleep(1000);
                    Log.d("myasynctask", message);
                } catch (InterruptedException e) {
                    Log.d("myasynctask", "interrupted");
                }

                Log.d("myasynctask", "i = " + String.valueOf(i));
                publishProgress(i+1);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer[] values) {
            super.onProgressUpdate(values);
            Log.d("myasynctask", "values = " + String.valueOf(values[0]));
            if(mListener != null){
                mListener.onProgress(values[0]);
            }
        }
    }

}
