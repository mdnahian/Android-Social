package activities;


import com.aqurytech.pinetree.R;
import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by Md Islam on 5/25/2016.
 */
public class WelcomeActivity extends com.stephentuso.welcome.ui.WelcomeActivity {

    @Override
    protected WelcomeScreenConfiguration configuration() {

        return new WelcomeScreenBuilder(this)
                .theme(R.style.CustomWelcomeScreenTheme)
                .defaultTitleTypefacePath("Montserrat-Bold.ttf")
                .defaultHeaderTypefacePath("Montserrat-Bold.ttf")
                .titlePage(R.drawable.photo, "Hello. Welcome to Pinetree.", R.color.colorAccent)
                .basicPage(R.drawable.photo, "What is it?", "Post images and videos from places for others to find.", R.color.colorPrimary)
                .basicPage(R.drawable.photo, "Just Another Social App?", "Unlike other social apps, Pinetree ranks all users by the number of meaningful contributions.", R.color.colorPrimaryDark)
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();

    }


}
