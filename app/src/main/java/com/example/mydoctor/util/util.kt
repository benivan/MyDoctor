import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.WindowManager


fun changeColorOfTheStatusBar(activity: Activity, color: Int){
    if (Build.VERSION.SDK_INT >= 21) {
        val window =activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
        window.navigationBarColor = Color.WHITE
    }
}
