package ir.ehsan.asmrtodolist

import android.app.Application
import com.google.firebase.FirebaseApp


// This starts when app starts and extends application class
class ScannerFlowApp : Application() {
    //Launching point of the app
    // we initiate firebase here
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}