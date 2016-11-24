package com.coderulez.senai.leansurvey.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import java.security.Permission;

/**
 * Created by SENAI on 05/11/2016.
 */

public class Conexao {
    public static Boolean temConexao(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static Boolean temPermissao(Context context) {
        int internet = ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET);

        if (internet == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }
}
