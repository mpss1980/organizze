package br.com.coupledev.organizze

import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import br.com.coupledev.organizze.databinding.ActivityMainBinding
import br.com.coupledev.organizze.presentation.adapters.SlideIntroAdapter
import br.com.coupledev.organizze.presentation.models.SlideIntroModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SlideIntroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sliders = listOf(
            SlideIntroModel(
                title = getString(R.string.intro_one_content),
                subTitle = getString(R.string.intro_one_content_two),
                imageId = R.drawable.um
            ),
            SlideIntroModel(
                title = getString(R.string.intro_two_content),
                subTitle = getString(R.string.intro_two_content_two),
                imageId = R.drawable.dois
            ),
            SlideIntroModel(
                title = getString(R.string.intro_three_content),
                subTitle = getString(R.string.intro_three_content_two),
                imageId = R.drawable.tres
            ),
            SlideIntroModel(
                title = getString(R.string.intro_four_content),
                subTitle = getString(R.string.intro_four_content_two),
                imageId = R.drawable.quatro
            ),
            SlideIntroModel(
                title = getString(R.string.subscribe_title),
                subTitle = getString(R.string.subscribe_subtitle),
                buttonText = getString(R.string.subscribe),
                linkText = getString(R.string.have_an_account)
            ),
        )

        adapter = SlideIntroAdapter(this, sliders)
        binding.introViewPager.adapter = adapter
        binding.introViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.introViewPager.setCurrentItem(0, true)

        setupIndicators()
        setCurrentIndicator(0)

        binding.introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(adapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

}