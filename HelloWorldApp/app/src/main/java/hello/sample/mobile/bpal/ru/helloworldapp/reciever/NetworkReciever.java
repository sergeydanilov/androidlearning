package hello.sample.mobile.bpal.ru.helloworldapp.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Topexpert on 13.05.2016.
 */
public class NetworkReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Message.", Toast.LENGTH_LONG).show();
    }
}
