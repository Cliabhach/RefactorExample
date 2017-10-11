package com.fuzz.refactorexample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.fuzz.thermal.Affinity;
import com.fuzz.thermal.ColdManager;
import com.fuzz.thermal.HeatService;
import com.fuzz.thermal.TerribleFactory;
import com.fuzz.thermal.exposed.WarmBuild;
import com.fuzz.thermal.model.CoolWidget;
import com.fuzz.thermal.model.ProbablyUsedToBeAPOJO;
import com.fuzz.thermal.model.Temperature;
import com.fuzz.thermal.model.WarmWidget;
import com.fuzz.thermal.model.WeirdThing;
import com.jakewharton.threetenabp.AndroidThreeTen;

import static com.fuzz.thermal.model.ProbablyUsedToBeAPOJO.EMPTY;

/**
 * The application entry point to and consumer of the 'thermal' module.
 */
public class EntryActivity extends AppCompatActivity {

    private TextView seasonText, seasonTemp;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            tempService = ((ColdManager) service);
            seasonTemp.setText(getString(R.string.temperature_label, tempService.getTemperature().name()));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            tempService = null;
        }
    };

    private boolean isBound = false;

    @Nullable
    private ColdManager tempService;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String text = null;
            int id = 0;
            switch (item.getItemId()) {
                case R.id.navigation_spring:
                    text = getWarmString(TerribleFactory.MaKEnew(EntryActivity.this, null));
                    id = R.string.title_spring;
                    break;
                case R.id.navigation_summer:
                    id = R.string.title_summer;
                    break;
                case R.id.navigation_autumn:
                    text = getCoolString(TerribleFactory.makenEw());
                    id = R.string.title_autumn;
                    break;
                case R.id.navigation_winter:
                    id = R.string.title_winter;
                    break;
            }
            if (text != null) {
                seasonText.setText(text);
            } else if (id != 0) {
                seasonText.setText(id);
            } else {
                return false;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidThreeTen.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        seasonText = findViewById(R.id.message);
        seasonTemp = findViewById(R.id.season_temp);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        isBound = bindService(
                new Intent(this, HeatService.class),
                connection,
                BIND_AUTO_CREATE | BIND_NOT_FOREGROUND
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(connection);
            tempService = null;
        }
    }

    @Affinity(Temperature.WARM)
    protected String getWarmString(@Nullable WeirdThing thing) {
        if (thing != null) {
            if (thing instanceof ProbablyUsedToBeAPOJO && thing != EMPTY) {
                return ((ProbablyUsedToBeAPOJO) thing).getName();
            } else if (thing instanceof WarmWidget) {
                return ((WarmWidget) thing).getId();
            } else if (thing instanceof WarmBuild) {
                return "" + ((WarmBuild) thing).getFaveColor();
            }
        }
        return null;
    }

    /**
     * The Old Codebase has some weird ideas about how to get the name of a color....
     *
     * @param thing any old thing
     * @return the thing's cool color, or null if we can't figure out how to retrieve one
     */
    @Affinity(Temperature.COOL)
    protected String getCoolString(@Nullable WeirdThing thing) {
        if (thing != null) {
            if (thing instanceof ProbablyUsedToBeAPOJO && thing != EMPTY) {
                return ((ProbablyUsedToBeAPOJO) thing).getColor();
            } else if (thing instanceof CoolWidget) {
                return ((CoolWidget) thing).getId();
            }
        }
        return null;
    }

}
