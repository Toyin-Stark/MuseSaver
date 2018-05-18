package ng.canon.musesaver

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*
import ng.canon.musesaver.Tabs.Muse
import ng.canon.musesaver.Tabs.PhotoBucket
import ng.canon.musesaver.Tabs.ViewPagerAdapter


class MainActivity : AppCompatActivity() {
    private val tabIcons = intArrayOf(R.drawable.ic_home,R.drawable.ic_save)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loadNavigation()

    }


    fun loadNavigation(){


        val home = AHBottomNavigationItem(R.string.home_tab,   R.drawable.ic_instagram,android.R.color.tertiary_text_light)
        val save = AHBottomNavigationItem(R.string.save_tab,   R.drawable.ic_file,android.R.color.tertiary_text_light)

        bottomNavigation.addItem(home)
        bottomNavigation.addItem(save)

        bottomNavigation.defaultBackgroundColor = ContextCompat.getColor(applicationContext, android.R.color.white)
        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottomNavigation.isForceTint = true
        bottomNavigation.accentColor = ContextCompat.getColor(applicationContext,R.color.colorPrimary)

        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottomNavigation.isForceTint = true


        val faces = Muse()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frames, faces)
        transaction.addToBackStack(null)
        transaction.commit()


        bottomNavigation.setOnTabSelectedListener(object:AHBottomNavigation.OnTabSelectedListener{
            override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {


                if (position == 0){

                    val homes = Muse()
                    val hometransaction = supportFragmentManager.beginTransaction()
                    hometransaction.replace(R.id.frames, homes)
                    hometransaction.addToBackStack(null)
                    hometransaction.commit()

                }


                if (position == 1){

                    val photopale = PhotoBucket()
                    val phototransaction = supportFragmentManager.beginTransaction()
                    phototransaction.replace(R.id.frames, photopale)
                    phototransaction.addToBackStack(null)
                    phototransaction.commit()

                }





                return true
            }


        })

    }

}
