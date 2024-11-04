package com.paul.newsapp.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.paul.newsapp.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        val url = intent.getStringExtra("URL")
        if (url !=null){
            val detailWebView: WebView = findViewById(R.id.detailWebView)
            detailWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"
            detailWebView.settings.javaScriptEnabled = true
            detailWebView.webViewClient = object :WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    val progressBar :ProgressBar = findViewById(R.id.progressBar)
                    progressBar.visibility = View.GONE
                    detailWebView.visibility = View.VISIBLE
                }

            }
            detailWebView.loadUrl(url)
        }
    }
}