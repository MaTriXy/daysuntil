package com.gpacini.daysuntil

import android.app.Application
import android.os.Build
import com.crashlytics.android.Crashlytics
import com.gpacini.daysuntil.data.ImageHelper
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by gavinpacini on 10/10/15.
 */
class DaysUntilApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Setup ImageLoader
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this))

        //Set path to store event images
        val appPath = this.filesDir.absolutePath
        ImageHelper.getInstance().init("${appPath}/images")

        //Setup Realm
        val config = RealmConfiguration.Builder(this).build()
        Realm.setDefaultConfiguration(config)

        //Setup Crashlytics
        if(!BuildConfig.DEBUG) {
            Fabric.with(this, Crashlytics())
        }
    }
}