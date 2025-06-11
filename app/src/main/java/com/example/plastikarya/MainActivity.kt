// MainActivity.kt - Updated dengan navigasi ke RegisterActivity
package com.example.plastikarya

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var btnStartCreating: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupViewPager()
        setupButtonClickListener()
    }

    private fun setupViews() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        btnStartCreating = findViewById(R.id.btn_start_creating)
    }

    private fun setupButtonClickListener() {
        btnStartCreating.setOnClickListener {
            // Intent untuk pindah ke RegisterActivity (bukan LoginActivity)
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewPager() {
        val adapter = OnboardingPagerAdapter(this)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3

        // Improved page transformer for better card spacing and visual effect
        viewPager.setPageTransformer { page, position ->
            val pageWidth = page.width
            val pageMargin = resources.getDimensionPixelOffset(R.dimen.page_margin)
            val offsetPx = position * -(pageMargin * 1)

            page.translationX = offsetPx.toFloat()

            // Add scaling effect for better visual visual appeal
            when {
                position < -1 -> { // Page is way off-screen to the left
                    page.alpha = 0f
                }
                position <= 1 -> { // Page is visible or partially visible
                    page.alpha = 1f
                    val scaleFactor = Math.max(0.85f, 1 - Math.abs(position) * 0.15f)
                    page.scaleY = scaleFactor
                    page.scaleX = scaleFactor
                }
                else -> { // Page is way off-screen to the right
                    page.alpha = 0f
                }
            }
        }

        // Connect TabLayout with ViewPager2 for dots indicator
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Empty - visual handled by selector
        }.attach()

        // Improved dot spacing
        tabLayout.post {
            for (i in 0 until tabLayout.tabCount) {
                val tab = tabLayout.getTabAt(i)
                val tabView = tab?.view
                tabView?.let { view ->
                    val layoutParams = view.layoutParams
                    if (layoutParams is ViewGroup.MarginLayoutParams) {
                        val marginInPx = resources.getDimensionPixelOffset(R.dimen.dot_margin)
                        layoutParams.setMargins(marginInPx, 0, marginInPx, 0)
                        view.layoutParams = layoutParams
                        view.minimumWidth = 0 // Remove minimum width to make dots smaller
                    }
                }
            }
        }

        updateDots(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDots(position)
            }
        })
    }

    private fun updateDots(selectedPosition: Int) {
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            tab?.view?.isSelected = i == selectedPosition
        }
    }

    private inner class OnboardingPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> OnboardingFragment.newInstance(
                    iconRes = R.drawable.ic_recycle,
                    title = "Ubah Sampah Jadi Karya",
                    description = "Bergabunglah dengan komunitas kreatif yang mengubah limbah plastik menjadi produk bernilai tinggi"
                )
                1 -> OnboardingFragment.newInstance(
                    iconRes = R.drawable.ic_community,
                    title = "Komunitas Kreatif",
                    description = "Terhubung dengan sesama kreator, berbagi ide, dan berkolaborasi dalam proyek berkelanjutan"
                )
                2 -> OnboardingFragment.newInstance(
                    iconRes = R.drawable.ic_book,
                    title = "Belajar & Berkembang",
                    description = "Akses pelatihan gratis tentang teknik upcycling dan kewirausahaan berkelanjutan"
                )
                3 -> OnboardingFragment.newInstance(
                    iconRes = R.drawable.ic_growth,
                    title = "Raih Penghasilan",
                    description = "Jual karya daur ulang Anda dan bangun bisnis berkelanjutan yang menguntungkan"
                )
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }
    }
}