package fiketMafian.Hazel;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.AppBarLayout;
import android.os.Bundle;
import fiketMafian.Hazel.RoundImage;
import 	android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        Drawable[] layers = new Drawable[2];
        layers[1] = getDrawable(R.drawable.circle);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.buyPrimary));
                    appbar.setBackgroundColor(getResources().getColor(R.color.buyPrimary));
                    mViewPager.setCurrentItem(tab.getPosition());
                } else {
                    //getApplication().setTheme(R.style.sellTheme);
                    toolbar.setBackgroundColor(getResources().getColor(R.color.sellPrimary));
                    appbar.setBackgroundColor(getResources().getColor(R.color.sellPrimary));
                    mViewPager.setCurrentItem(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //profil
        ImageView profile = (ImageView) findViewById(R.id.imageView);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.ola);
        RoundImage roundedImage = new RoundImage(bm);
        profile.setImageDrawable(roundedImage);



        //drawer

        Menu drawerMenu= navigationView.getMenu();
        SubMenu submenuru;

        submenuru = drawerMenu.addSubMenu(Menu.NONE, 0, 0, "Pågående köp");
        layers[0]=getDrawable(R.drawable.erik);
        submenuru.add(Menu.NONE,1,Menu.NONE,"Erik").setIcon(new LayerDrawable(layers));
        layers[0]=getDrawable(R.drawable.sven);
        submenuru.add(Menu.NONE, 2, Menu.NONE, "Sven").setIcon(new LayerDrawable(layers));
        submenuru = drawerMenu.addSubMenu(Menu.NONE, 10, 1, "Pågående Försäljningar");
        layers[0]=getDrawable(R.drawable.karim);
        submenuru.add(Menu.NONE, 11, Menu.NONE, "Karim").setIcon(new LayerDrawable(layers));
        layers[0]=getDrawable(R.drawable.ola);
        submenuru.add(Menu.NONE, 12, Menu.NONE, "Ola").setIcon(new LayerDrawable(layers));
        submenuru = drawerMenu.addSubMenu(Menu.NONE, 20, 2, "Letar köpare");
        submenuru.add(Menu.NONE, 21, Menu.NONE, "TNA005").setIcon(R.drawable.pending);
        submenuru.add(Menu.NONE, 22, Menu.NONE, "TND012").setIcon(R.drawable.pending);
        submenuru = drawerMenu.addSubMenu(Menu.NONE, 30, 3, "Letar försäljare");
        submenuru.add(Menu.NONE, 31, Menu.NONE, "TND002").setIcon(R.drawable.pending);
        submenuru = drawerMenu.addSubMenu(Menu.NONE, 40, 4, "Senaste affärerna");
        submenuru.add(Menu.NONE, 41, Menu.NONE, "someonea").setIcon(R.drawable.sell);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        /*
        if (id == R.id.ongoing_pur1) {
            // Handle the camera action
        } else if (id == R.id.ongoing_pur2) {

        } else if (id == R.id.ongoing_pur3) {

        } else if (id == R.id.ongoing_sell1) {

        } else if (id == R.id.ongoing_sell2) {

        } else if (id == R.id.search_pur1) {

        }
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "KÖP";
                case 1:
                    return "SÄLJ";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView;
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
            {
                rootView = inflater.inflate(R.layout.buy, container, false);
            }
            else
            {
                rootView = inflater.inflate(R.layout.sell, container, false);
            }
            return rootView;
        }
    }


}