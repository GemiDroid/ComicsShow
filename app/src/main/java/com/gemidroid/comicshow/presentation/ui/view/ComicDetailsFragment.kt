package com.gemidroid.comicshow.presentation.ui.view

import Utilities.COMIC_ITEM
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.gemidroid.comicshow.R
import com.gemidroid.comicshow.base.BaseFragment
import com.gemidroid.comicshow.data.models.Comic
import kotlinx.android.synthetic.main.comic_details_fragment.*


class ComicDetailsFragment : BaseFragment() {

    override fun resId() = R.layout.comic_details_fragment

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getSerializable(COMIC_ITEM)?.let {
            val comicItem = (it as Comic)
            Glide.with(this).load(comicItem.img).into(img_banner)
            txt_title.text = comicItem.title
            txt_alt.text = comicItem.alt
            txt_outlines.text =
                HtmlCompat.fromHtml(comicItem.transcript, HtmlCompat.FROM_HTML_MODE_COMPACT)
            txt_link.text = comicItem.link
            txt_comic_name.text = comicItem.safeTitle

            img_share.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, comicItem.toString())
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
                img_add_to_fav.setImageDrawable(if (comicItem.isLiked)
                    requireActivity().getDrawable(R.drawable.ic_favorite)
                else
                    requireActivity().getDrawable(R.drawable.ic_unfavorite))
        }

        img_back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
