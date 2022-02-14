package cn.popapps.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import layaair.game.browser.ConchJNI;


public class JSBridge {
    public static Handler m_Handler = new Handler(Looper.getMainLooper());
    public static Activity mMainActivity = null;

    public static void hideSplash() {
        m_Handler.post(
                new Runnable() {
                    public void run() {
                        MainActivity.mSplashDialog.dismissSplash();
                    }
                });
    }

    public static void setFontColor(final String color) {
//        m_Handler.post(
//                new Runnable() {
//                    public void run() {
//                        MainActivity.mSplashDialog.setFontColor(Color.parseColor(color));
//                    }
//                });
    }

    public static void setTips(final JSONArray tips) {
//        m_Handler.post(
//                new Runnable() {
//                    public void run() {
//                        try {
//                            String[] tipsArray = new String[tips.length()];
//                            for (int i = 0; i < tips.length(); i++) {
//                                tipsArray[i] = tips.getString(i);
//                            }
//                            MainActivity.mSplashDialog.setTips(tipsArray);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
    }

    public static void bgColor(final String color) {
//        m_Handler.post(
//                new Runnable() {
//                    public void run() {
//                        MainActivity.mSplashDialog.setBackgroundColor(Color.parseColor(color));
//                    }
//                });
    }

    public static void loading(final double percent) {
//        m_Handler.post(
//                new Runnable() {
//                    public void run() {
//                        MainActivity.mSplashDialog.setPercent((int)percent);
//                    }
//                });
    }

    public static void showTextInfo(final boolean show) {
//        m_Handler.post(
//                new Runnable() {
//                    public void run() {
//                        MainActivity.mSplashDialog.showTextInfo(show);
//                    }
//                });
    }

    public static void exit() {
        m_Handler.post(
                new Runnable() {
                    public void run() {
                        mMainActivity.finish();
                        System.exit(0);
                    }
                });
    }

    public static void migu() {
        m_Handler.post(
                new Runnable() {
                    public void run() {
                        Intent intent = new Intent(mMainActivity, MiguActivity.class);
//                        intent.putExtra("url", info);
                        mMainActivity.startActivityForResult(intent, 555);
                    }
                });
    }

    public static void toast(final String msg) {
        m_Handler.post(
                new Runnable() {
                    public void run() {
                        Toast.makeText(JSBridge.mMainActivity, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
