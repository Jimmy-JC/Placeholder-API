package jimmy.placeholder_api.dialogs

import android.widget.ImageView
import com.squareup.picasso.Picasso
import jimmy.placeholder_api.R

class ImageDialog(var url: String) : BaseDialog() {

    override fun setContentView() {
        dialog.setContentView(R.layout.imageview_dialog_layout)
        val imageView = dialog.findViewById<ImageView>(R.id.image_view)
        Picasso.get().load(url).placeholder(R.drawable.big_placeholder).into(imageView)
    }

}