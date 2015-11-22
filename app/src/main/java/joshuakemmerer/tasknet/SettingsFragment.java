package joshuakemmerer.tasknet;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by temp on 11/22/2015.
 */

public class SettingsFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
