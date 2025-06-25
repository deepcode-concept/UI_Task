package com.example.taskui2.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.taskui2.Adapter.ImageAdapter
import com.example.taskui2.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    val imageList = listOf(
        R.drawable.ordera,
        R.drawable.orderb,
        R.drawable.orderc,
        R.drawable.orderd,
        R.drawable.ordera
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)


        val viewPager = view.findViewById<ViewPager2>(R.id.imageSlider)
        val dotContainer = view.findViewById<LinearLayout>(R.id.dotContainer)

        val adapter = ImageAdapter(imageList)
        viewPager.adapter = adapter


        dotContainer.post {
            setupDots(dotContainer, requireContext(), imageList.size, 0) // 0 = first image selected
        }

// Slide hone par dot update hote rahenge
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                setupDots(dotContainer, requireActivity(), imageList.size, position)
            }
        })




        return view
    }

    private fun setupDots(dotContainer: LinearLayout, context: Context, count: Int, selectedPosition: Int) {
        dotContainer.removeAllViews()

        val dotSize = context.resources.getDimensionPixelSize(R.dimen.dot_size)
        val dotMargin = context.resources.getDimensionPixelSize(R.dimen.dot_margin)

        for (i in 0 until count) {
            val dot = ImageView(context)
            val drawableRes = if (i == selectedPosition) R.drawable.ic_selected_dot else R.drawable.ic_unselected
            dot.setImageResource(drawableRes)

            val params = LinearLayout.LayoutParams(dotSize, dotSize)
            params.marginStart = dotMargin
            params.marginEnd = dotMargin
            dot.layoutParams = params

            dotContainer.addView(dot)
        }
    }


}