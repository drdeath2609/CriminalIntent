package com.drdeath.android.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class CrimeListActivity extends SingleFragmentActivity 
    implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return com.drdeath.android.criminalintent.R.layout.activity_masterdetail;
    }

    public void onCrimeSelected(Crime crime) {
        if (findViewById(com.drdeath.android.criminalintent.R.id.detailFragmentContainer) == null) {
            // start an instance of CrimePagerActivity
            Intent i = new Intent(this, CrimePagerActivity.class);
            i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
            startActivityForResult(i, 0);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            Fragment oldDetail = fm.findFragmentById(com.drdeath.android.criminalintent.R.id.detailFragmentContainer);
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());

            if (oldDetail != null) {
                ft.remove(oldDetail);
            } 

            ft.add(com.drdeath.android.criminalintent.R.id.detailFragmentContainer, newDetail);
            ft.commit();
        }
    }

    public void onCrimeUpdated(Crime crime) {
        FragmentManager fm = getSupportFragmentManager();
        CrimeListFragment listFragment = (CrimeListFragment)
                fm.findFragmentById(com.drdeath.android.criminalintent.R.id.fragmentContainer);
        listFragment.updateUI();
    }
}
