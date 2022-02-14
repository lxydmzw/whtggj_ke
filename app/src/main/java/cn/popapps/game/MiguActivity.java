package cn.popapps.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import cn.popapps.game.xxx.R;

public class MiguActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.migu_dialog);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        Intent intent = new Intent();
        setResult(200, intent);
        finish();
        return true;
    }
}
