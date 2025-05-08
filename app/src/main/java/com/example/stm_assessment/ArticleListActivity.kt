package com.example.stm_assessment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stm_assessment.Models.Article
import com.example.stm_assessment.Models.Author

class ArticleListActivity : AppCompatActivity() {

    private lateinit var articleRecyclerView: RecyclerView
    private val articleList = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)

        articleRecyclerView = findViewById(R.id.article_recycler_view)

        // Add the sample articles here
        articleList.addAll(getSampleArticles())

        val adapter = ArticleAdapter(articleList)
        articleRecyclerView.layoutManager = LinearLayoutManager(this)
        articleRecyclerView.adapter = adapter
    }

    private fun getSampleArticles(): List<Article> {
        return listOf(
            Article(
                1, "AI Breakthrough in 2025", "Researchers announce a major step forward in artificial general intelligence.",
                "In a landmark announcement today, scientists revealed a new AI model capable of mimicking human cognition more closely than ever before...",
                Author("Jane Smith", "https://example.com/profiles/jane.jpg"),
                "2025-05-08T09:30:00Z", "Technology", "https://example.com/images/ai-breakthrough.jpg", 124, 18
            ),
            Article(
                2, "Global Markets Rebound After Slow Start", "Stock markets recover as tech leads the way.",
                "After a sluggish start to the quarter, global markets have shown signs of recovery. Tech giants led the charge with positive earnings...",
                Author("Michael Tan", "https://example.com/profiles/michael.jpg"),
                "2025-05-07T14:45:00Z", "Finance", "https://example.com/images/market-rebound.jpg", 78, 5
            ),
            Article(
                3, "Championship Final Ends in Dramatic Tie", "A nail-biting finish leaves fans stunned in the international cricket final.",
                "In an unforgettable conclusion, the final match ended in a tie despite a super over, with both teams locked in at 220 runs...",
                Author("Anita Roy", "https://example.com/profiles/anita.jpg"),
                "2025-05-06T20:15:00Z", "Sports", "https://example.com/images/cricket-final.jpg", 204, 32
            )
        )
    }

    inner class ArticleAdapter(private val articles: List<Article>) :
        RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

        override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): ArticleViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item, parent, false)
            return ArticleViewHolder(view)
        }

        override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
            val article = articles[position]
            holder.titleTextView.text = article.title
            holder.likeButton.setOnClickListener {
                val resultIntent = Intent().apply {
                    putExtra("liked_article_title", article.title)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        override fun getItemCount() = articles.size

        inner class ArticleViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
            val titleTextView: TextView = view.findViewById(R.id.article_title)
            val likeButton: Button = view.findViewById(R.id.like_button)
        }
    }
}
