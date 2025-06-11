package com.example.plastikarya

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MarketActivity : AppCompatActivity() {

    private lateinit var btnBack: View
    private lateinit var btnCart: View
    private lateinit var btnFilter: CardView
    private lateinit var etSearch: EditText
    private lateinit var tvResultsCount: TextView
    private lateinit var tvSort: TextView
    private lateinit var rvProducts: RecyclerView
    private lateinit var bottomNavigation: BottomNavigationView

    // Category buttons
    private lateinit var categoryAll: CardView
    private lateinit var categoryFashion: CardView
    private lateinit var categoryHome: CardView
    private lateinit var categoryGadget: CardView

    private lateinit var marketProductsAdapter: MarketProductsAdapter
    private var allProducts = mutableListOf<Product>()
    private var filteredProducts = mutableListOf<Product>()
    private var selectedCategory = "all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market)

        setupViews()
        setupRecyclerView()
        setupEventListeners()
        setupBottomNavigationIcon()
        loadSampleData()
        filterProducts()
    }

    private fun setupViews() {
        btnBack = findViewById(R.id.btn_back)
        btnCart = findViewById(R.id.btn_cart)
        btnFilter = findViewById(R.id.btn_filter)
        etSearch = findViewById(R.id.et_search)
        tvResultsCount = findViewById(R.id.tv_results_count)
        tvSort = findViewById(R.id.tv_sort)
        rvProducts = findViewById(R.id.rv_products)
        bottomNavigation = findViewById(R.id.bottom_navigation)

        // Category buttons
        categoryAll = findViewById(R.id.category_all)
        categoryFashion = findViewById(R.id.category_fashion)
        categoryHome = findViewById(R.id.category_home)
        categoryGadget = findViewById(R.id.category_gadget)
    }

    private fun setupRecyclerView() {
        marketProductsAdapter = MarketProductsAdapter(filteredProducts) { product ->
            // Handle product click - navigate to product detail
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product_id", product.id)
            startActivity(intent)
        }

        rvProducts.apply {
            layoutManager = GridLayoutManager(this@MarketActivity, 2)
            adapter = marketProductsAdapter
        }
    }

    private fun setupEventListeners() {
        // Back button
        btnBack.setOnClickListener {
            finish()
        }

        // Cart button
        btnCart.setOnClickListener {
            // Navigate to cart activity
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Filter button
        btnFilter.setOnClickListener {
            // Show filter dialog or navigate to filter activity
            showFilterDialog()
        }

        // Search functionality
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterProducts()
            }
        })

        // Sort button
        tvSort.setOnClickListener {
            showSortDialog()
        }

        // Category buttons
        categoryAll.setOnClickListener { selectCategory("all") }
        categoryFashion.setOnClickListener { selectCategory("fashion") }
        categoryHome.setOnClickListener { selectCategory("home") }
        categoryGadget.setOnClickListener { selectCategory("gadget") }
    }

    private fun setupBottomNavigationIcon() {
        bottomNavigation.selectedItemId = R.id.nav_market

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_market -> {
                    // Already in market
                    true
                }
                R.id.nav_learn -> {
                    startActivity(Intent(this, LearnActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_community -> {
                    startActivity(Intent(this, CommunityActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun selectCategory(category: String) {
        selectedCategory = category
        updateCategoryUI()
        filterProducts()
    }

    private fun updateCategoryUI() {
        // Reset all category buttons to inactive state
        resetCategoryButton(categoryAll, R.drawable.ic_grid, "Semua")
        resetCategoryButton(categoryFashion, R.drawable.ic_fashion, "Fashion")
        resetCategoryButton(categoryHome, R.drawable.ic_home, "Rumah")
        resetCategoryButton(categoryGadget, R.drawable.ic_gadget, "Gadget")

        // Set selected category to active state
        when (selectedCategory) {
            "all" -> setActiveCategoryButton(categoryAll, R.drawable.ic_grid, "Semua")
            "fashion" -> setActiveCategoryButton(categoryFashion, R.drawable.ic_fashion, "Fashion")
            "home" -> setActiveCategoryButton(categoryHome, R.drawable.ic_home, "Rumah")
            "gadget" -> setActiveCategoryButton(categoryGadget, R.drawable.ic_gadget, "Gadget")
        }
    }

    private fun resetCategoryButton(cardView: CardView, iconRes: Int, text: String) {
        cardView.setCardBackgroundColor(getColor(android.R.color.white))
        // Update text and icon colors to inactive state
        // You'll need to implement this based on your layout structure
    }

    private fun setActiveCategoryButton(cardView: CardView, iconRes: Int, text: String) {
        cardView.setCardBackgroundColor(getColor(R.color.black))
        // Update text and icon colors to active state
        // You'll need to implement this based on your layout structure
    }

    private fun filterProducts() {
        val searchQuery = etSearch.text.toString().lowercase()

        filteredProducts.clear()

        val filtered = allProducts.filter { product ->
            val matchesSearch = product.name.lowercase().contains(searchQuery) ||
                    product.description.lowercase().contains(searchQuery)

            val matchesCategory = selectedCategory == "all" ||
                    product.category.lowercase() == selectedCategory.lowercase()

            matchesSearch && matchesCategory
        }

        filteredProducts.addAll(filtered)
        marketProductsAdapter.notifyDataSetChanged()

        updateResultsCount()
    }

    private fun updateResultsCount() {
        val count = filteredProducts.size
        tvResultsCount.text = "$count Produk Ditemukan"
    }

    private fun showFilterDialog() {
        // Implement filter dialog
        // For now, just show a simple toast or implement a basic filter
    }

    private fun showSortDialog() {
        // Implement sort dialog with options like:
        // - Harga terendah
        // - Harga tertinggi
        // - Rating tertinggi
        // - Terbaru
        // - Terlaris
    }

    private fun loadSampleData() {
        allProducts.addAll(listOf(
            Product(
                id = 1,
                name = "Tas Belanja Eco-Friendly",
                price = "Rp 45.000",
                imageUrl = "",
                rating = 4.8f,
                soldCount = 120