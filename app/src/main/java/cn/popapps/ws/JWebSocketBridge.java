package cn.popapps.ws;

import android.util.Log;
import android.widget.Toast;

import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

import cn.popapps.game.JSBridge;
import layaair.game.browser.ConchJNI;

public class JWebSocketBridge {
    public final static String TAG = "JWebSocketBridge";
    public static JWebSocketClient client = null;
    public static void connect(String url) {
        Log.e(TAG, "connect::" + url);
        if(client == null)
        {
//            JSBridge.toast("connect");
            URI uri = URI.create(url);
            client = new JWebSocketClient(uri)
            {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.e(TAG, "onOpen");
                    JWebSocketBridge.send2js("onOpen", "");
                }

                @Override
                public void onMessage(String message) {
                    Log.e(TAG, "onMessage::" + message);
                    JWebSocketBridge.send2js("onMessage", message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.e(TAG, "onClose");
                    JWebSocketBridge.send2js("onClose", "");
                }

                @Override
                public void onError(Exception ex) {
                    Log.e(TAG, "onError");
                    JWebSocketBridge.send2js("onError", "");
                }
            };
            try {
                client.setConnectionLostTimeout(100);
                client.connectBlocking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
        {
//            JSBridge.toast("reconnect");
        }
    }

    public static void send(String msg) {
        if(client != null)
        {
//            JSBridge.toast(client.isOpen() + "");
//            if(client.getReadyState())
            client.send(msg);
        }
    }

    public static void close() {
        if(client != null)
        {
            try {
                client.closeBlocking();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            client = null;
        }
    }

    public static void send2js(String tag, String value) {
//        Log.e(TAG, "send2js=" + tag);
//        Toast.makeText(JSBridge.mMainActivity, tag, Toast.LENGTH_SHORT).show();
//        JSBridge.toast(tag);


        StringBuilder cmd = new StringBuilder("window.natvieCallJs(");
        JSONObject data = new JSONObject();
        try{
            data.put("tag",tag);
            data.put("data",value);
        }catch (JSONException e){
            e.printStackTrace();
        }
        cmd.append(data);
        cmd.append(')');
        ConchJNI.RunJS(cmd.toString());

        if(tag == "onError" || tag == "onClose")
        {
            client = null;
        }
    }
}
