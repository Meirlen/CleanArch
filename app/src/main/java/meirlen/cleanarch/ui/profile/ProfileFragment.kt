package meirlen.cleanarch.ui.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.custom_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import meirlen.cleanarch.R
import meirlen.cleanarch.utill.ext.loadImage

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.custom_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //avaImageView.loadImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaEdNsDBtTU4ZWlJPnbEbdPQ-vxt-xiqxVS-_-oJ9Q9QE6tDk_")
        image_circle.showImage()
    }
}