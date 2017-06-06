package co.gostyn.karson.medijgastronomia.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;


public class InternetConnection {

	/** sprawdzenie czy jest polaczenie z siecia, internetem */
	public static boolean checkConnection(@NonNull Context context) {
		return  ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
	}
}
