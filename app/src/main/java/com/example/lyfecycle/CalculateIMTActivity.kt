package com.example.lyfecycle

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculateIMTActivity : AppCompatActivity() {

    private lateinit var result_imtTV: TextView
    private lateinit var imt_imageView: ImageView
    private lateinit var adviceTV: TextView

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculate_imtactivity)

        result_imtTV = findViewById(R.id.result_imtTV)
        imt_imageView = findViewById(R.id.imt_imageView)
        adviceTV = findViewById(R.id.adviceTV)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val total_result = intent.getStringExtra("key")
        result_imtTV.text = "${total_result}"

        val result: Double = total_result?.toDouble() ?: 22.0

        when((result * 10).toInt()) {
             in 160..185 -> {
                 imt_imageView.setImageResource(R.drawable.imt_min)
                 adviceTV.text = "Рекомендуется повысить калорийность суточного рациона на 300–500 ккал."
             }
            in 186..249 -> {
                imt_imageView.setImageResource(R.drawable.imt_norm)
                adviceTV.text = "Придерживайтесь своего рациона питания. Не забывайте о достаточной активности."
            }
            in 250..299 -> {
                imt_imageView.setImageResource(R.drawable.imt_norm_fat)
                adviceTV.text = "Количество потребляемых килокалорий в день необходимо уменьшить на 500."+
                        "При значениях более 40 врач может порекомендовать снижение калорийности на 700 ккал и выше."
            }
            in 300..349 -> {
                imt_imageView.setImageResource(R.drawable.imt_fat)
                adviceTV.text = "Cледует ежедневно ходить от 2000 шагов, увеличивая количество на 500 каждую неделю, при хорошем самочувствии добавить плавание."+
                        "При показателе свыше 40 начальной нагрузкой может быть ходьба 100 шагов в день с медленным увеличением."
            }
            in 350..500 -> {
                imt_imageView.setImageResource(R.drawable.imt_very_fat)
                adviceTV.text = "Обратитесь ко врачу для получения рекомендаций."
            }
            else -> return

        }
    }
}