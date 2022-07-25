package br.com.coupledev.organizze.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.coupledev.organizze.databinding.ItemIntroSliderBinding
import br.com.coupledev.organizze.presentation.models.SlideIntroModel

class SlideIntroAdapter(
    private val slidersData: List<SlideIntroModel>
) : RecyclerView.Adapter<SlideIntroAdapter.SlideIntroViewHolder>() {
    inner class SlideIntroViewHolder(val binding: ItemIntroSliderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideIntroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemIntroSliderBinding.inflate(layoutInflater, parent, false)
        return SlideIntroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlideIntroViewHolder, position: Int) {
        holder.binding.apply {
            tvIntroTitle.text = slidersData[position].title
            tvIntroSubtitle.text = slidersData[position].subTitle

            slidersData[position].imageId.let {
                if (it != null) introImageView.setImageResource(it)
                else introImageView.visibility = View.GONE
            }

            slidersData[position].buttonText.let {
                if (it != null) btnIntro.text = it
                else btnIntro.visibility = View.GONE
            }

            slidersData[position].linkText.let {
                if (it != null) tvIntroLink.text = it
                else tvIntroLink.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return slidersData.size
    }
}