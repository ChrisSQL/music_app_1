/*
  Area52 - Chris Maher
 */

package com.area52.techno.activities;

import android.Manifest;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.appthemeengine.customizers.ATEActivityThemeCustomizer;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.area52.techno.FacebookActivityFirebase;
import com.area52.techno.MyAccountActivity;
import com.area52.techno.dj.DJList;
import com.area52.techno.dj.MainActivityDJ;
import com.area52.techno.dj.MyDJActivityBranch;
import com.area52.techno.djs.DJs;
import com.area52.techno.festivals.FestivalsActivity;
import com.area52.techno.fragments.EventsFragmentNew;
import com.area52.techno.fragments.HomeFragmentDJ;
import com.area52.techno.models.User;
import com.area52.techno.users.MainActivityUser;
import com.area52.techno.youtube.YouTubeActivityTechnoSets;
import com.facebook.login.LoginManager;
import com.google.android.gms.cast.framework.media.widget.ExpandedControllerActivity;
import com.area52.techno.MusicPlayer;
import com.area52.techno.R;
import com.area52.techno.fragments.AlbumDetailFragment;
import com.area52.techno.fragments.ArtistDetailFragment;
import com.area52.techno.fragments.FoldersFragment;
import com.area52.techno.fragments.MainFragment;
import com.area52.techno.fragments.PlaylistFragment;
import com.area52.techno.fragments.QueueFragment;
import com.area52.techno.permissions.Nammu;
import com.area52.techno.permissions.PermissionCallback;
import com.area52.techno.slidinguppanel.SlidingUpPanelLayout;
import com.area52.techno.subfragments.LyricsFragment;
import com.area52.techno.utils.Constants;
import com.area52.techno.utils.NavigationUtils;
import com.area52.techno.utils.TimberUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.client.util.DateTime;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;
import java.time.Instant;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;

public class MainActivity extends BaseActivity implements ATEActivityThemeCustomizer, EventsFragmentNew.OnFragmentInteractionListener {

    private SlidingUpPanelLayout panelLayout;
    private NavigationView navigationView;
    private TextView songtitle, songartist;
    private ImageView albumart;
    private String action, dj;
    private Map<String, Runnable> navigationMap = new HashMap<String, Runnable>();
    private Handler navDrawerRunnable = new Handler();
    private Runnable runnable;
    private DrawerLayout mDrawerLayout;
    private boolean isDarkTheme;
    private static final String TAG = "Firebase123";
    private FirebaseAuth mAuth;
    FirebaseUser user;
    Branch branch;
    // [START declare_auth]

    private Runnable navigateDJ = new Runnable() {
        public void run() {
            //    navigationView.getMenu().findItem(R.id.nav_library).setChecked(true);
            Fragment fragment = new HomeFragmentDJ();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment).commitAllowingStateLoss();

        }
    };

    private Runnable navigateLibrary = new Runnable() {
        public void run() {
        //    navigationView.getMenu().findItem(R.id.nav_library).setChecked(true);
            Fragment fragment = new MainFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment).commitAllowingStateLoss();

        }
    };

//    private Runnable navigateEvents = new Runnable() {
//        public void run() {
//            navigationView.getMenu().findItem(R.id.nav_events).setChecked(true);
//            Fragment fragment = new EventsFragment();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.hide(getSupportFragmentManager().findFragmentById(R.id.fragment_container));
//            transaction.replace(R.id.fragment_container, fragment).commit();
//
//        }
//    };

    private Runnable navigatePlaylist = new Runnable() {
        public void run() {
        //    navigationView.getMenu().findItem(R.id.nav_playlists).setChecked(true);
            Fragment fragment = new PlaylistFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(getSupportFragmentManager().findFragmentById(R.id.fragment_container));
            transaction.replace(R.id.fragment_container, fragment).commit();

        }
    };

    private Runnable navigateFolder = new Runnable() {
        public void run() {
        //    navigationView.getMenu().findItem(R.id.nav_folders).setChecked(true);
            Fragment fragment = new FoldersFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(getSupportFragmentManager().findFragmentById(R.id.fragment_container));
            transaction.replace(R.id.fragment_container, fragment).commit();

        }
    };

    private Runnable navigateQueue = new Runnable() {
        public void run() {
        //    navigationView.getMenu().findItem(R.id.nav_queue).setChecked(true);
            Fragment fragment = new QueueFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(getSupportFragmentManager().findFragmentById(R.id.fragment_container));
            transaction.replace(R.id.fragment_container, fragment).commit();

        }
    };

    private Runnable navigateAlbum = new Runnable() {
        public void run() {
            long albumID = getIntent().getExtras().getLong(Constants.ALBUM_ID);
            Fragment fragment = AlbumDetailFragment.newInstance(albumID, false, null);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit();
        }
    };

    private Runnable navigateArtist = new Runnable() {
        public void run() {
            long artistID = getIntent().getExtras().getLong(Constants.ARTIST_ID);
            Fragment fragment = ArtistDetailFragment.newInstance(artistID, false, null);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit();
        }
    };

    private Runnable navigateLyrics = new Runnable() {
        public void run() {
            Fragment fragment = new LyricsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit();
        }
    };

    private Runnable navigateNowplaying = new Runnable() {
        public void run() {
            navigateLibrary.run();
            startActivity(new Intent(MainActivity.this, NowPlayingActivity.class));
        }
    };

    private final PermissionCallback permissionReadstorageCallback = new PermissionCallback() {
        @Override
        public void permissionGranted() {
            loadEverything();
        }

        @Override
        public void permissionRefused() {
            finish();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        notifications();

        // Toast.makeText(this, "MediaPlayerMain", Toast.LENGTH_SHORT).show();

        action = getIntent().getAction();
        isDarkTheme = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("dark_theme", true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If logged in log Analytics
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
//        if(user == null){
//            startActivity(new Intent(MainActivity.this, FacebookActivityFirebase.class));
//        }

    //    expandableMenu();



        navigationMap.put(Constants.NAVIGATE_LIBRARY, navigateLibrary);
        navigationMap.put(Constants.NAVIGATE_PLAYLIST, navigatePlaylist);
        navigationMap.put(Constants.NAVIGATE_QUEUE, navigateQueue);
        navigationMap.put(Constants.NAVIGATE_NOWPLAYING, navigateNowplaying);
        navigationMap.put(Constants.NAVIGATE_ALBUM, navigateAlbum);
        navigationMap.put(Constants.NAVIGATE_ARTIST, navigateArtist);
        navigationMap.put(Constants.NAVIGATE_LYRICS, navigateLyrics);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        panelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.inflateHeaderView(R.layout.nav_header);

        albumart = (ImageView) header.findViewById(R.id.album_art);
        songtitle = (TextView) header.findViewById(R.id.song_title);
        songartist = (TextView) header.findViewById(R.id.song_artist);

        setPanelSlideListeners(panelLayout);

        navDrawerRunnable.postDelayed(new Runnable() {
            @Override
            public void run() {
                setupDrawerContent(navigationView);
                setupNavigationIcons(navigationView);
            }
        }, 700);


        if (TimberUtils.isMarshmallow()) {
            checkPermissionAndThenLoad();
            //checkWritePermissions();
        } else {
            loadEverything();
        }

        addBackstackListener();

        if(Intent.ACTION_VIEW.equals(action)) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MusicPlayer.clearQueue();
                    MusicPlayer.openFile(getIntent().getData().getPath());
                    MusicPlayer.playOrPause();
                    navigateNowplaying.run();
                }
            }, 350);
        }

        if (!panelLayout.isPanelHidden() && MusicPlayer.getTrackName() == null ) {
            panelLayout.hidePanel();
        }

        if (playServicesAvailable) {

            final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.BOTTOM;

            FrameLayout contentRoot = findViewById(R.id.content_root);
            contentRoot.addView(LayoutInflater.from(this)
                    .inflate(R.layout.fragment_cast_mini_controller, null), params);

            findViewById(R.id.castMiniController).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, ExpandedControllerActivity.class));
                }
            });
        }
    }



    private void notifications() {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "1";
        String channel2 = "2";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId,
                    "Channel 1", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("This is BNT");
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            notificationManager.createNotificationChannel(notificationChannel);

            NotificationChannel notificationChannel2 = new NotificationChannel(channel2,
                    "Channel 2", NotificationManager.IMPORTANCE_MIN);

            notificationChannel.setDescription("This is bTV");
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            notificationManager.createNotificationChannel(notificationChannel2);

        }

    }

    @Override
    public void onStart() {
        super.onStart();

//        // BRANCH
//        branch = Branch.getInstance(getApplicationContext());
//        // Branch init
//        branch.initSession(new Branch.BranchReferralInitListener() {
//            @Override
//            public void onInitFinished(JSONObject referringParams, BranchError error) {
//                if (error == null) {
//                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
//                    // params will be empty if no data found
//                    // ... insert custom logic here ...
//                    dj = referringParams.optString("djReferral", "Hannah Wants");
//                    // Toast.makeText(MainActivity.this, dj, Toast.LENGTH_SHORT).show();
//
//                    if (dj.equals("none")) {
//
////                        Intent i = new Intent(MainActivity.this, MainActivity.class);
////                        i.putExtra("djName", "none");
////                        startActivity(i);
//                    }
//                    else {
//
//                        Intent i = new Intent(MainActivity.this, MyDJActivityBranch.class);
//                        i.putExtra("djName", dj);
//                        startActivity(i);
//                    }
//                    // Toast.makeText(MainActivity.this, referringParams.toString(), Toast.LENGTH_LONG).show();
//                } else {
//                    Log.i("BRANCH SDK", error.getMessage());
//                }
//            }
//        }, this.getIntent().getData(), this);

    }

    private void loadEverything() {
        Runnable navigation = navigationMap.get(action);
        if (navigation != null) {
            navigation.run();
        } else {
            navigateLibrary.run();
        }

        new initQuickControls().execute("");
    }

    private void checkPermissionAndThenLoad() {
        //check for permission
        if (Nammu.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE) && Nammu.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            loadEverything();
        } else {
            if (Nammu.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(panelLayout, "Sesh will need to read external storage to display songs on your device.",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Nammu.askForPermission(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionReadstorageCallback);
                            }
                        }).show();
            } else {
                Nammu.askForPermission(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, permissionReadstorageCallback);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (isNavigatingMain()) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                } else super.onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (panelLayout.isPanelExpanded()) {
            panelLayout.collapsePanel();
        } else if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        //    fragmentTransaction.replace(R.id.containerView,new Recommendation()).commit();
            //super.onBackPressed();
        }
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        updatePosition(menuItem);

                        return true;
                    }
                });
    }

    private void setupNavigationIcons(NavigationView navigationView) {

        if (!isDarkTheme) {

        //    navigationView.getMenu().findItem(R.id.nav_events).setIcon(R.drawable.ic_dashboard_black_24dp);
            navigationView.getMenu().findItem(R.id.users).setIcon(R.drawable.ic_dashboard_black_24dp);
            navigationView.getMenu().findItem(R.id.djs).setIcon(R.drawable.library_music);
        //    navigationView.getMenu().findItem(R.id.sets).setIcon(R.drawable.library_music);
        //    navigationView.getMenu().findItem(R.id.nav_members_sets).setIcon(R.drawable.library_music);
        //    navigationView.getMenu().findItem(R.id.nav_sets_techno).setIcon(R.drawable.video_icon_48);
        //    navigationView.getMenu().findItem(R.id.nav_festivals).setIcon(R.drawable.library_music);
        //    navigationView.getMenu().findItem(R.id.nav_soundcloud).setIcon(R.drawable.souncloud_48_black);

        //    navigationView.getMenu().findItem(R.id.nav_sets_trance).setIcon(R.drawable.video_icon_48);

            navigationView.getMenu().findItem(R.id.nav_library).setIcon(R.drawable.library_music);
            navigationView.getMenu().findItem(R.id.nav_playlists).setIcon(R.drawable.playlist_play);
            navigationView.getMenu().findItem(R.id.nav_queue).setIcon(R.drawable.music_note);
            navigationView.getMenu().findItem(R.id.nav_folders).setIcon(R.drawable.ic_folder_open_black_24dp);
        //    navigationView.getMenu().findItem(R.id.nav_nowplaying).setIcon(R.drawable.bookmark_music);
            navigationView.getMenu().findItem(R.id.nav_settings).setIcon(R.drawable.settings);
      //      navigationView.getMenu().findItem(R.id.nav_about).setIcon(R.drawable.information);
      //      navigationView.getMenu().findItem(R.id.nav_donate).setIcon(R.drawable.payment_black);
            navigationView.getMenu().findItem(R.id.nav_account).setIcon(R.drawable.information);

        } else {

        //    navigationView.getMenu().findItem(R.id.nav_events).setIcon(R.drawable.ic_dashboard_white_24dp);
            navigationView.getMenu().findItem(R.id.users).setIcon(R.drawable.ic_dashboard_white_24dp);
            navigationView.getMenu().findItem(R.id.djs).setIcon(R.drawable.library_music_white);
        //    navigationView.getMenu().findItem(R.id.sets).setIcon(R.drawable.library_music_white);

        //    navigationView.getMenu().findItem(R.id.nav_members_sets).setIcon(R.drawable.library_music_white);
        //    navigationView.getMenu().findItem(R.id.nav_sets_techno).setIcon(R.drawable.video_icon_48_white);
        //    navigationView.getMenu().findItem(R.id.nav_festivals).setIcon(R.drawable.library_music_white);
        //    navigationView.getMenu().findItem(R.id.nav_soundcloud).setIcon(R.drawable.souncloud_48_white);

        //    navigationView.getMenu().findItem(R.id.nav_sets_trance).setIcon(R.drawable.video_icon_48_white);

            navigationView.getMenu().findItem(R.id.nav_library).setIcon(R.drawable.library_music_white);
            navigationView.getMenu().findItem(R.id.nav_playlists).setIcon(R.drawable.playlist_play_white);
            navigationView.getMenu().findItem(R.id.nav_queue).setIcon(R.drawable.music_note_white);
            navigationView.getMenu().findItem(R.id.nav_folders).setIcon(R.drawable.ic_folder_open_white_24dp);
        //    navigationView.getMenu().findItem(R.id.nav_nowplaying).setIcon(R.drawable.bookmark_music_white);
            navigationView.getMenu().findItem(R.id.nav_settings).setIcon(R.drawable.settings_white);
         //   navigationView.getMenu().findItem(R.id.nav_about).setIcon(R.drawable.information_white);
         //   navigationView.getMenu().findItem(R.id.nav_donate).setIcon(R.drawable.payment_white);
            navigationView.getMenu().findItem(R.id.nav_account).setIcon(R.drawable.information_white);
        }

        try {
            if (!BillingProcessor.isIabServiceAvailable(this)) {
               // navigationView.getMenu().removeItem(R.id.nav_donate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePositionDJ() {
        runnable = null;


        runnable = navigateDJ;

        if (runnable != null) {
            mDrawerLayout.closeDrawers();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            }, 350);
        }
    }

    public void updatePosition(final MenuItem menuItem) {
        runnable = null;

        switch (menuItem.getItemId()) {

//            case R.id.nav_events:
//               //runnable = navigateEvents;
//                startActivity(new Intent(MainActivity.this, EventsActivity.class));
//                //startActivity(new Intent(MediaPlayerMain.this, FestivalsActivity.class));
//                break;
            case R.id.djs:
                startActivity(new Intent(MainActivity.this, MainActivityDJ.class));
                break;
            case R.id.users:
                startActivity(new Intent(MainActivity.this, MainActivityUser.class));
                break;
//            case R.id.sets:
//                startActivity(new Intent(MainActivity.this, MainActivitySet.class));
//                break;
//                break;
//            case R.id.nav_members_sets:
//                startActivity(new Intent(MainActivity.this, YouTubeActivityTechnoSets.class));
//                break;
//            case R.id.nav_sets_techno:
//                startActivity(new Intent(MainActivity.this, YouTubeActivityTechnoSets.class));
//                break;
//            case R.id.nav_festivals:
//                //startActivity(new Intent(MediaPlayerMain.this, Awakenings2018.class));
//                startActivity(new Intent(MainActivity.this, FestivalsActivity.class));
//                break;
//            case R.id.nav_sets_trance:
//                startActivity(new Intent(MainActivity.this, YouTubeActivityTranceSets.class));
//                break;
//            case R.id.nav_soundcloud:
//                startActivity(new Intent(MainActivity.this, SoundCloudActivity.class));
//                break;
            case R.id.nav_library:
                runnable = navigateLibrary;

                break;
            case R.id.nav_playlists:
                runnable = navigatePlaylist;

                break;
            case R.id.nav_folders:
                runnable = navigateFolder;

                break;
//            case R.id.nav_nowplaying:
//                if (getCastSession() != null) {
//                    startActivity(new Intent(MediaPlayerMain.this, ExpandedControlsActivity.class));
//                } else {
//                    NavigationUtils.navigateToNowplaying(MediaPlayerMain.this, false);
//                }
//                break;
            case R.id.nav_queue:
                runnable = navigateQueue;

                break;
            case R.id.nav_settings:
                NavigationUtils.navigateToSettings(MainActivity.this);
                break;
            case R.id.nav_account:

//                if (user.getUid() == null){
//                    Intent i = new Intent(MainActivity.this, FacebookActivityFirebase.class);
//                    startActivity(new Intent(MainActivity.this, FacebookActivityFirebase.class));
//                   break;
//                }else{

                    Intent intent = new Intent(getBaseContext(), MyAccountActivity.class);
                    intent.putExtra("userID", user.getUid());
                //    intent.putExtra("userID", "YkpEQnxoIfhuutZD6uWE9dX3klv1");
                    startActivity(intent);

//                }

                break;
            case R.id.log_out:
                signOut();
                break;
        //    case R.id.nav_donate:
        //        startActivity(new Intent(MediaPlayerMain.this, DonateActivity.class));
        //        break;
        }

        if (runnable != null) {
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            }, 350);
        }
    }

    public void signOut() {
        Toast.makeText(this, "Signing Out.", Toast.LENGTH_SHORT).show();
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        startActivity(new Intent(MainActivity.this, FacebookActivityFirebase.class));
    }

    public void setDetailsToHeader() {
        String name = MusicPlayer.getTrackName();
        String artist = MusicPlayer.getArtistName();

        if (name != null && artist != null) {
            songtitle.setText(name);
            songartist.setText(artist);
        }
        ImageLoader.getInstance().displayImage(TimberUtils.getAlbumArtUri(MusicPlayer.getCurrentAlbumId()).toString(), albumart,
                new DisplayImageOptions.Builder().cacheInMemory(true)
                        .showImageOnFail(R.drawable.ic_empty_music2)
                        .resetViewBeforeLoading(true)
                        .build());
    }

    @Override
    public void onMetaChanged() {
        super.onMetaChanged();
        setDetailsToHeader();

        if (panelLayout.isPanelHidden() && MusicPlayer.getTrackName() != null) {
            panelLayout.showPanel();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, String[] permissions, int[] grantResults) {
        Nammu.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean isNavigatingMain() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        return (currentFragment instanceof MainFragment || currentFragment instanceof QueueFragment
                || currentFragment instanceof PlaylistFragment || currentFragment instanceof FoldersFragment);
    }

    private void addBackstackListener() {
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                getSupportFragmentManager().findFragmentById(R.id.fragment_container).onResume();
            }
        });
    }

    @Override
    public int getActivityTheme() {
        return isDarkTheme ? R.style.AppThemeNormalDark : R.style.AppThemeNormalLight;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManager().findFragmentById(R.id.fragment_container).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showCastMiniController() {
        findViewById(R.id.castMiniController).setVisibility(View.VISIBLE);
        findViewById(R.id.quickcontrols_container).setVisibility(View.GONE);
        panelLayout.hidePanel();
    }

    @Override
    public void hideCastMiniController() {

        findViewById(R.id.castMiniController).setVisibility(View.GONE);
        findViewById(R.id.quickcontrols_container).setVisibility(View.VISIBLE);

        panelLayout.showPanel();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBindingDied(ComponentName name) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void share(View view) {

        Toast.makeText(this, "Share", Toast.LENGTH_LONG).show();

    }


}


