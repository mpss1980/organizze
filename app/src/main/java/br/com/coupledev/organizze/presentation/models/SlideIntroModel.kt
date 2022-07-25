package br.com.coupledev.organizze.presentation.models

data class SlideIntroModel(
    val title: String,
    val subTitle: String,
    val imageId: Int? = null,
    val buttonText: String? = null,
    val linkText: String? = null,
)
