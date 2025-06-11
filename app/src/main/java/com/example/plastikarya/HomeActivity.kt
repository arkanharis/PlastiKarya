package com.example.plastikarya

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var tvGreeting: TextView
    private lateinit var tvSubtitle: TextView
    private lateinit var tvProductsCount: TextView
    private lateinit var tvEarnings: TextView
    private lateinit var cardEcoMarket: CardView
    private lateinit var cardKelasKarya: CardView
    private lateinit var cardKomunitas: CardView
    private lateinit var cardDampakku: CardView
    private lateinit var tvSeeAll: TextView
    private lateinit var rvTrendingProducts: RecyclerView
    private lateinit var bottomNavigation: BottomNavigationView

    private lateinit var trendingProductsAdapter: TrendingProductsAdapter
    private val trendingProducts = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initializeViews()
        setupTrendingProducts()
        setupClickListeners()
        setupBottomNavigation()
        loadUserData()
    }

    private fun initializeViews() {
        tvGreeting = findViewById(R.id.tv_greeting)
        tvSubtitle = findViewById(R.id.tv_subtitle)
        tvProductsCount = findViewById(R.id.tv_products_count)
        tvEarnings = findViewById(R.id.tv_earnings)
        cardEcoMarket = findViewById(R.id.card_eco_market)
        cardKelasKarya = findViewById(R.id.card_kelas_karya)
        cardKomunitas = findViewById(R.id.card_komunitas)
        cardDampakku = findViewById(R.id.card_dampakku)
        tvSeeAll = findViewById(R.id.tv_see_all)
        rvTrendingProducts = findViewById(R.id.rv_trending_products)
        bottomNavigation = findViewById(R.id.bottom_navigation)
    }

    private fun setupTrendingProducts() {
        // Sample data for trending products
        trendingProducts.addAll(listOf(
            Product(
                id = 1,
                name = "Tas Belanja Plastik",
                price = "Rp 45.000",
                imageUrl = "", // You can add actual image URLs here
                rating = 4.5f,
                soldCount = 25
            ),
            Product(
                id = 2,
                name = "Vas Bunga Kreatif",
                price = "Rp 35.000",
                imageUrl = "",
                rating = 4.8f,
                soldCount = 18
            ),
            Product(
                id = 3,
                name = "Tempat Pensil Unik",
                price = "Rp 25.000",
                imageUrl = "",
                rating = 4.3f,
                soldCount = 32
            ),
            Product(
                id = 4,
                name = "Kotak Penyimpanan",
                price = "Rp 55.000",
                imageUrl = "",
                rating = 4.6f,
                soldCount = 15
            )
        ))

        trendingProductsAdapter = TrendingProductsAdapter(trendingProducts) { product ->
            // Handle product click
            // You can navigate to product detail here
        }

        rvTrendingProducts.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingProductsAdapter
        }
    }

    private fun setupClickListeners() {
        cardEcoMarket.setOnClickListener {
            // Navigate to EcoMarket
            // startActivity(Intent(this, EcoMarketActivity::class.java))
        }

        cardKelasKarya.setOnClickListener {
            // Navigate to KelasKarya
            // startActivity(Intent(this, KelasKaryaActivity::class.java))
        }

        cardKomunitas.setOnClickListener {
            // Navigate to Komunitas
            // startActivity(Intent(this, KomunitasActivity::class.java))
        }

        cardDampakku.setOnClickListener {
            // Navigate to DampakKu
            // startActivity(Intent(this, DampakKuActivity::class.java))
        }

        tvSeeAll.setOnClickListener {
            // Navigate to all products
            // startActivity(Intent(this, AllProductsActivity::class.java))
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigation.selectedItemId = R.id.nav_home

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Already on home
                    true
                }
                R.id.nav_market -> {
                    // Navigate to market
                    // startActivity(Intent(this, MarketActivity::class.java))
                    true
                }
                R.id.nav_learn -> {
                    // Navigate to learning
                    // startActivity(Intent(this, LearnActivity::class.java))
                    true
                }
                R.id.nav_community -> {
                    // Navigate to community
                    // startActivity(Intent(this, CommunityActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    // Navigate to profile
                    // startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun loadUserData() {
        // In a real app, you would load this data from API or database
        tvGreeting.text = "Halo, Sarah! 👋"
        tvSubtitle.text = "Selamat datang di PlastiKarya"
        tvProductsCount.text = "15"
        tvEarnings.text = "2.5M"
    }
}