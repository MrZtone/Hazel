package fiketMafian.Hazel;

import android.animation.ObjectAnimator;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.EditText;
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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.AppBarLayout;
import android.content.Context;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    public static float searchBarHeight=0f;

    public static final int BUY=0;
    public static final int SELL=1;

    public static final int UP=0;
    public static final int DOWN=1;
    private static boolean searchBarElevated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final AppBarLayout appbar = (AppBarLayout) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        Drawable[] layers = new Drawable[2];
        layers[1] = getDrawable(R.drawable.circle);

        ViewTreeObserver vto = getWindow().getDecorView().getRootView().getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                searchBarHeight=findViewById(R.id.search_container).getY();
                SearchView searchbar = (SearchView) findViewById(R.id.searchView);
                int id = searchbar.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
                EditText editText = (EditText) searchbar.findViewById(id);
                editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!searchBarElevated)
                        {
                            MoveSearchBar(UP);
                        }
                    }
                });

                getWindow().getDecorView().getRootView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


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

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        toolbar.setBackgroundColor(getResources().getColor(R.color.buyPrimary));
        appbar.setBackgroundColor(getResources().getColor(R.color.buyPrimary));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == BUY) {
                    toolbar.setBackgroundColor(getResources().getColor(R.color.buyPrimary));
                    appbar.setBackgroundColor(getResources().getColor(R.color.buyPrimary));
                    mViewPager.setCurrentItem(tab.getPosition());
                    ActivateSell(false);
                } else { //SELL
                    toolbar.setBackgroundColor(getResources().getColor(R.color.sellPrimary));
                    appbar.setBackgroundColor(getResources().getColor(R.color.sellPrimary));
                    mViewPager.setCurrentItem(tab.getPosition());
                    MoveSearchBar(DOWN);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
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
        submenuru.add(Menu.NONE,View.generateViewId(),Menu.NONE,"Erik").setIcon(new LayerDrawable(layers));
        layers[0]=getDrawable(R.drawable.sven);
        submenuru.add(Menu.NONE, View.generateViewId(), Menu.NONE, "Sven").setIcon(new LayerDrawable(layers));
        submenuru = drawerMenu.addSubMenu(Menu.NONE, View.generateViewId(), 1, "Pågående Försäljningar");
        layers[0]=getDrawable(R.drawable.karim);
        submenuru.add(Menu.NONE, View.generateViewId(), Menu.NONE, "Karim").setIcon(new LayerDrawable(layers));
        layers[0]=getDrawable(R.drawable.ola);
        submenuru.add(Menu.NONE, View.generateViewId(), Menu.NONE, "Ola").setIcon(new LayerDrawable(layers));
        submenuru = drawerMenu.addSubMenu(Menu.NONE, View.generateViewId(), 2, "Letar köpare");
        submenuru.add(Menu.NONE, View.generateViewId(), Menu.NONE, "TNA005").setIcon(R.drawable.pending);
        submenuru.add(Menu.NONE, View.generateViewId(), Menu.NONE, "TND012").setIcon(R.drawable.pending);
        submenuru = drawerMenu.addSubMenu(Menu.NONE, View.generateViewId(), 3, "Letar försäljare");
        submenuru.add(Menu.NONE, View.generateViewId(), Menu.NONE, "TND002").setIcon(R.drawable.pending);
        submenuru = drawerMenu.addSubMenu(Menu.NONE, View.generateViewId(), 4, "Senaste affärerna");
        submenuru.add(Menu.NONE, View.generateViewId(), Menu.NONE, "someonea").setIcon(R.drawable.sell);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(searchBarElevated)
            {
                MoveSearchBar(DOWN);
            }
            else
            {
                super.onBackPressed();
            }
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

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d("onnewintent", "onnewintent");
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

            if(tabLayout.getSelectedTabPosition()==MainActivity.BUY)
            {
                System.out.println("buy");
                MoveSearchBar(UP);
                setCard("Title" , "Author", "TNX000", "Course");
                setCard("Title" , "Author", "TNX000", "Course");
                setCard("Title" , "Author", "TNX000", "Course");
                setCard("Title" , "Author", "TNX000", "Course");
                setCard("Title" , "Author", "TNX000", "Course");
                setCard("Title" , "Author", "TNX000", "Course");
            } else //SELL
            {
                ActivateSell(true);
                System.out.println("sell");
            }
        }
    }

    private void setCard(String title, String author, String courseCode, String course)
    {
        // add Image
        ViewGroup searchResults = (ViewGroup) findViewById(R.id.search_results);
        LayoutInflater lf = getLayoutInflater();
        View card = lf.inflate(R.layout.book_card_template, searchResults, false);
        TextView Title = (TextView) card.findViewById(R.id.title_buy);
        Title.setText(title);
        TextView Author = (TextView) card.findViewById(R.id.author_buy);
        Author.setText(author);
        TextView CouresCode = (TextView) card.findViewById(R.id.coursecode_buy);
        CouresCode.setText(courseCode);
        TextView Course = (TextView) card.findViewById(R.id.course_buy);
        Course.setText(course);
        searchResults.addView(card);
    }
    private void MoveSearchBar(int Direction)
    {
        View searchContainer = findViewById(R.id.search_container);
        View searchBackground = findViewById(R.id.search_background);
        float y=searchContainer.getY();
        float alpha =0f;
        ViewGroup searchResults = (ViewGroup) findViewById(R.id.search_results);
        searchResults.removeAllViews();
        if(Direction==UP)
        {
            y=-50f;
            searchBarElevated=true;
        }
        else if(Direction==DOWN)
        {
            alpha=1f;
            y=searchBarHeight;
            searchBarElevated=false;
            SearchView s = (SearchView) findViewById(R.id.searchView);
            s.setQuery("",false);

        }
        ObjectAnimator animY = ObjectAnimator.ofFloat(searchContainer, "y", y);
        animY.setDuration(500);
        ObjectAnimator animAlpha = ObjectAnimator.ofFloat(searchBackground, "alpha", alpha);
        animAlpha.setDuration(500);
        animY.start();
        animAlpha.start();

    }

    void ActivateSell(boolean Activate)
    {

        findViewById(R.id.title_sell).setEnabled(Activate);
        findViewById(R.id.author_sell).setEnabled(Activate);
        findViewById(R.id.release_sell).setEnabled(Activate);
        findViewById(R.id.bookrate).setEnabled(Activate);
        findViewById(R.id.button).setEnabled(Activate);
        RatingBar rate = (RatingBar) findViewById(R.id.ratingBar);
        rate.setIsIndicator(!Activate);
        if(Activate)
        {
            Resources r = getResources();
            LinearLayout bookGallery = (LinearLayout) findViewById(R.id.bookgallery);
            LayoutInflater lf = getLayoutInflater();
            Drawable[] layers = new Drawable[2];
            layers[1] = r.getDrawable(R.drawable.bookselected);
            layers[0]=r.getDrawable(R.drawable.book);

            ImageView book1 = (ImageView) lf.inflate(R.layout.gallery_template, bookGallery, false);
            book1.setImageDrawable(r.getDrawable(R.drawable.book));
            book1.setId(View.generateViewId());
            bookGallery.addView(book1);

            ImageView book2= (ImageView) lf.inflate(R.layout.gallery_template, bookGallery, false);
            book2.setImageDrawable(r.getDrawable(R.drawable.book));
            book2.setId(View.generateViewId());
            bookGallery.addView(book2);

            ImageView book3= (ImageView) lf.inflate(R.layout.gallery_template, bookGallery, false);
            book3.setImageDrawable(new LayerDrawable(layers));
            book3.setId(View.generateViewId());
            bookGallery.addView(book3);
        }
        else
        {
            ViewGroup bookGallery = (ViewGroup) findViewById(R.id.bookgallery);
            bookGallery.removeAllViews();
            SearchView s = (SearchView) findViewById(R.id.searchView2);
            s.setQuery("", false);
            rate.setRating(0f);
        }
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
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case MainActivity.BUY:
                    return "KÖP";
                case MainActivity.SELL:
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
            if(getArguments().getInt(ARG_SECTION_NUMBER)==MainActivity.BUY)
            {
                rootView = inflater.inflate(R.layout.buy, container, false);

                // Get the SearchView and set the searchable configuration
                SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
                SearchView searchView = (SearchView) rootView.findViewById(R.id.searchView);

                ComponentName component = new ComponentName(getContext(), MainActivity.class);
                searchView.setSearchableInfo(searchManager.getSearchableInfo(component));
                searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
            }
            else
            {
                rootView = inflater.inflate(R.layout.sell, container, false);

                // Get the SearchView and set the searchable configuration
                SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
                SearchView searchView = (SearchView) rootView.findViewById(R.id.searchView2);

                ComponentName component = new ComponentName(getContext(), MainActivity.class);
                searchView.setSearchableInfo(searchManager.getSearchableInfo(component));
                searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

            }
            return rootView;
        }
    }


}