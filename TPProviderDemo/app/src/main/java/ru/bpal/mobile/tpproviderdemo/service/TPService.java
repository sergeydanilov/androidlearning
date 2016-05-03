package ru.bpal.mobile.tpproviderdemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import ru.bpal.mobile.tpproviderdemo.MobileConstant;

/**
 *
 */
public class TPService extends IntentService {
    public static final String TAG = MobileConstant.TAG_PREFIX + TPService.class.getSimpleName();

    public static final int REQUEST_INVALID = -1;

    public static final String METHOD_EXTRA = "ru.bpal.mobile.service.METHOD_EXTRA";
    public static final String RESOURCE_TYPE_EXTRA = "ru.bpal.mobile.service.RESOURCE_TYPE_EXTRA";
    public static final String SERVICE_CALLBACK = "ru.bpal.mobile.service.SERVICE_CALLBACK";

    public static final String ORIGINAL_INTENT_EXTRA = "ru.bpal.mobile.service.ORIGINAL_INTENT_EXTRA";

    public static final String METHOD_GET = "GET";

    public static final int RESOURCE_TYPE_METAINFO = 1;

    private Intent mOriginalRequestIntent;
    private ResultReceiver mCallback;


    public TPService() {
        super("TPService");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "BPalService is started");
        //Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent requestIntent) {
        mOriginalRequestIntent = requestIntent;

        String method = requestIntent.getStringExtra(METHOD_EXTRA);
        int resourceType = requestIntent.getIntExtra(RESOURCE_TYPE_EXTRA, -1);
        mCallback = requestIntent.getParcelableExtra(SERVICE_CALLBACK);

        Log.d(TAG, "method = " + method);
        Log.d(TAG, "resourceType = " + resourceType);

        switch (resourceType) {
            case RESOURCE_TYPE_METAINFO:
                if (method.equalsIgnoreCase(METHOD_GET)) {
                    MetaInfoProcessor processor = new MetaInfoProcessor(getApplicationContext());
                    processor.getMetaInfo(makeProcessorCallback());
                } else {
                    mCallback.send(REQUEST_INVALID, getOriginalIntentBundle());
                }
                break;

            default:
                mCallback.send(REQUEST_INVALID, getOriginalIntentBundle());
                break;
        }
    }

    private TPProcessorCallback makeProcessorCallback() {
        TPProcessorCallback callback = new TPProcessorCallback() {

            @Override
            public void send(int resultCode) {
                if (mCallback != null) {
                    mCallback.send(resultCode, getOriginalIntentBundle());
                }
            }
        };
        return callback;
    }

    protected Bundle getOriginalIntentBundle() {
        Bundle originalRequest = new Bundle();
        originalRequest.putParcelable(ORIGINAL_INTENT_EXTRA, mOriginalRequestIntent);
        return originalRequest;
    }
}
