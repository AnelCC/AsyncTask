package cc.anel.myapplication;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

/**
 * Created by anelelizabethcervantes on 8/10/17.
 */

public class SharedPreferencesLoader extends AsyncTaskLoader implements  SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences prefs = null;

    public static void persist(final SharedPreferences.Editor editor) {
        editor.apply();
    }

    @Override
    protected Object onLoadInBackground() {
        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.registerOnSharedPreferenceChangeListener(this);
        return (prefs);
    }

    public SharedPreferencesLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        if (prefs != null) {
            deliverResult(prefs);
        }

        if (takeContentChanged() || prefs == null) {
            forceLoad();
        }
    }
}
