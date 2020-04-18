package jimmy.placeholder_api.dialogs

import jimmy.placeholder_api.R

object ProgressDialog : BaseDialog() {

    override fun setContentView() {
        dialog.setContentView(R.layout.progressbar_dialog_layout)
        dialog.setCanceledOnTouchOutside(false)
    }
}