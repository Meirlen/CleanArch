package meirlen.cleanarch.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import meirlen.cleanarch.R
import meirlen.cleanarch.utill.ext.toast

class HomeFragment : Fragment() {



    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.board_list_fragment, container, false)

    }


}