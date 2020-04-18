package jimmy.placeholder_api.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window

abstract class BaseDialog {

    internal lateinit var dialog: Dialog

    fun show(context: Context) {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView()
        dialog.show()
    }

    abstract fun setContentView()

    fun dismiss() {
        dialog.dismiss()
    }

}