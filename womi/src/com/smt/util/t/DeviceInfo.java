package com.smt.util.t;


import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DeviceInfo
{
    public static String getImei(Context context)
    {
        try
        {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String id = tm.getDeviceId();
            if (id != null)
            {
                return tm.getDeviceId();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "A000002CBD64E7";
    }
    
    public static String getMacWifi(Context context)
    {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String s = info.getMacAddress();
        if (s != null)
        {
            return s;
        }
        return "";
    }
    
    public static String getMacBluetooth(Context context)
    {
        BluetoothAdapter bAdapt = BluetoothAdapter.getDefaultAdapter();
        if (bAdapt != null)
        {
            if (bAdapt.isEnabled())
            {
                return bAdapt.getAddress();
            }
        }
        return "";
    }
    
    public static String getImsi(Context context)
    {
        try
        {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String imsi = tm.getSubscriberId();
            if (imsi == null)
            {
                imsi = "";
            }
            return imsi;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
    
    public static float getDensity(Context context)
    {
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        return dm.density;
    }
    
    public static String getModel()
    {
        return android.os.Build.MODEL;
    }
    
    public static int getScreenWidth(Context context)
    {
        WindowManager mWindowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return mWindowManager.getDefaultDisplay().getWidth();
    }
    
    public static int getScreenHeight(Context context)
    {
        WindowManager mWindowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return mWindowManager.getDefaultDisplay().getHeight();
    }
    
    public static boolean isSDAva()
    {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)
                || Environment.getExternalStorageDirectory().exists())
        {
            
            return true;
        }
        else
        {
            return false;
        }
    }
}
