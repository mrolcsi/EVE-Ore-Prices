package hu.mrolcsi.android.eveoreprices;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import hu.mrolcsi.android.eveoreprices.fragments.PriceFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Matusinka Roland
 * Date: 2014.11.02.
 * Time: 14:14
 */

public class MainActivity extends Activity {

    public static final int FRAGMENT_HIGHSEC = 0;
    public static final int FRAGMENT_LOWSEC = 1;
    public static final int FRAGMENT_NULLSEC = 2;

    public static final String PREF_SELECTED_SEC = "prefSelectedSec";
    public static final String PREF_SELECTED_STATION = "prefSelectedStation";

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerSystemsList;
    private ListView mDrawerStationsList;
    private ActionBarDrawerToggle mDrawerToggle;
    private SystemNavigationAdapter systemsAdapter;
    private StationNavigationAdapter stationsAdapter;
    private SharedPreferences sharedPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        initViews();

        refreshFragment();
    }

    private void initViews() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerSystemsList = (ListView) findViewById(R.id.lvSystems);
        mDrawerStationsList = (ListView) findViewById(R.id.lvStations);

        systemsAdapter = new SystemNavigationAdapter(this);
        systemsAdapter.setSelectedItem(sharedPrefs.getInt(PREF_SELECTED_SEC, FRAGMENT_HIGHSEC));
        mDrawerSystemsList.setAdapter(systemsAdapter);
        mDrawerSystemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                systemsAdapter.setSelectedItem(i);
                systemsAdapter.notifyDataSetChanged();
                sharedPrefs.edit().putInt(PREF_SELECTED_SEC, i).apply();
                refreshFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        stationsAdapter = new StationNavigationAdapter(this);
        stationsAdapter.setSelectedItem(sharedPrefs.getInt(PREF_SELECTED_STATION, 0));
        mDrawerStationsList.setAdapter(stationsAdapter);
        mDrawerStationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                stationsAdapter.setSelectedItem(i);
                stationsAdapter.notifyDataSetChanged();
                sharedPrefs.edit().putInt(PREF_SELECTED_STATION, i).apply();
                refreshFragment();
                mDrawerLayout.closeDrawers();
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //getActionBar().setTitle(mDrawerSystemsList.getSelectedItem().toString());
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(getTitle());
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void refreshFragment() {
        PriceFragment fragment = new PriceFragment();
        Bundle args = new Bundle();
        args.putInt(PriceFragment.ARG_SECURITY_LEVEL, systemsAdapter.getSelectedItem());
        args.putSerializable(PriceFragment.ARG_STATION, stationsAdapter.getItem(stationsAdapter.getSelectedItem()));
        fragment.setArguments(args);

        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
}