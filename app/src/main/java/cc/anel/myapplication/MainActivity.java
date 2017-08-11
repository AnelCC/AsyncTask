package cc.anel.myapplication;

import android.app.LoaderManager;
import android.content.Loader;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<SharedPreferences> {


    private static final String KEY = "prefs";
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.prefs);
        getLoaderManager().initLoader(0, null, this);
    }


    @Override
    public Loader<SharedPreferences> onCreateLoader(int id, Bundle args) {
        return (new SharedPreferencesLoader(this));
    }

    @Override
    public void onLoadFinished(Loader<SharedPreferences> loader, SharedPreferences data) {
        int value = data.getInt(KEY, 0);
        value += 1;
        textView.setText(String.valueOf(value));
        // update value
        SharedPreferences.Editor editor = data.edit();
        editor.putInt(KEY, value);
        SharedPreferencesLoader.persist(editor);
    }

    @Override
    public void onLoaderReset(Loader<SharedPreferences> loader) {

    }
}
