package com.example.plastikarya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class OnboardingFragment : Fragment() {

    companion object {
        private const val ARG_ICON = "icon"
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(iconRes: Int, title: String, description: String): OnboardingFragment {
            val fragment = OnboardingFragment()
            val args = Bundle()
            args.putInt(ARG_ICON, iconRes)
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val iconView = view.findViewById<ImageView>(R.id.iconImageView)
        val titleView = view.findViewById<TextView>(R.id.titleTextView)
        val descriptionView = view.findViewById<TextView>(R.id.descriptionTextView)

        arguments?.let { args ->
            val iconRes = args.getInt(ARG_ICON)
            val title = args.getString(ARG_TITLE)
            val description = args.getString(ARG_DESCRIPTION)

            iconView.setImageResource(iconRes)
            titleView.text = title
            descriptionView.text = description
        }
    }
}