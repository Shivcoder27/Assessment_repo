package com.example.stm_assessment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var likedArticleTextView: TextView
    private lateinit var openArticleListButton: Button

    private val articleLikeResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val likedArticleTitle = result.data?.getStringExtra("liked_article_title")
                likedArticleTextView.text = "Liked Article: $likedArticleTitle"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        likedArticleTextView = findViewById(R.id.liked_article_text)
        openArticleListButton = findViewById(R.id.open_article_list_button)

        openArticleListButton.setOnClickListener {
            val intent = Intent(this, ArticleListActivity::class.java)
            articleLikeResult.launch(intent)
        }
    }
}
