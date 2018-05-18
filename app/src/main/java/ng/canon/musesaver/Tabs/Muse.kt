package ng.canon.musesaver.Tabs


import android.content.*
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.github.angads25.toggle.LabeledSwitch
import com.github.angads25.toggle.interfaces.OnToggledListener
import kotlinx.android.synthetic.main.muse.*
import kotlinx.android.synthetic.main.muse.view.*
import ng.canon.musesaver.Church.Bayek
import ng.canon.musesaver.R

class Muse : Fragment() {
    private var isSvcRunning = false
    var switch: LabeledSwitch? = null
    var logo: ImageView? = null
    var toolz: Toolbar? = null
    var facer:Button? = null
    var curt: ImageView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.muse, container, false)

        switch = v.switches
        logo = v.logo_view
        facer = v.facer
        curt = v.curtains
        switch!!.setOnToggledListener(object: OnToggledListener {
            override fun onSwitched(labeledSwitch: LabeledSwitch?, isOn: Boolean) {

                val box = PreferenceManager.getDefaultSharedPreferences(activity)

                if (isOn){

                    facer!!.visibility = View.VISIBLE
                    logo!!.setImageResource(R.drawable.ic_alive)
                    curt!!.setImageResource(R.drawable.ic_x)
                    box.edit().putBoolean("locked", true).apply()
                    Genesis()

                }else{

                    facer!!.visibility = View.GONE
                    logo!!.setImageResource(R.drawable.ic_dead)
                    curt!!.setImageResource(R.drawable.ic_dull)

                    box.edit().putBoolean("locked", false).apply()
                    Revelation()


                }
            }


        })



        facer!!.setOnClickListener {

            callInstagram(activity!!.applicationContext,"com.zhiliaoapp.musically")

        }


        v.tuber.setOnClickListener {

            watchYoutubeVideo("-HKS43LuQZY")

        }


        return v
    }


    fun Genesis(){

        val intu = Intent(activity!!.applicationContext, Bayek::class.java)
        ContextCompat.startForegroundService(activity!!.applicationContext,intu)
    }



    fun Revelation(){

        val intu = Intent(activity!!.applicationContext, Bayek::class.java)
        activity!!.stopService(intu)
    }


    override fun onResume() {
        val manager = LocalBroadcastManager.getInstance(activity!!.applicationContext)
        manager.registerReceiver(mReceiver, IntentFilter(Bayek.ACTION_PONG))
        // the service will respond to this broadcast only if it's running
        manager.sendBroadcast(Intent(Bayek.ACTION_PING))
        super.onResume()
    }

    override fun onStop() {
        LocalBroadcastManager.getInstance(activity!!.applicationContext).unregisterReceiver(mReceiver);
        super.onStop()
    }


    protected var mReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // here you receive the response from the service
            if (intent.action == Bayek.ACTION_PONG) {
                isSvcRunning = true
                watchTower()
                logo!!.setImageResource(R.drawable.ic_alive)
                facer!!.visibility = View.VISIBLE
                curt!!.setImageResource(R.drawable.ic_x)



            }
        }
    }



    fun watchTower(){

        switch!!.isOn = isSvcRunning

    }



    private fun callInstagram(context: Context, packageN: String) {
        val apppackage = packageN
        try {
            val i = context.packageManager.getLaunchIntentForPackage(apppackage)
            context.startActivity(i)
            activity!!.finish()
        } catch (e: Exception) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageN)))
        }

    }


    fun watchYoutubeVideo(id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id))
        val webIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id))
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }

    }

}
