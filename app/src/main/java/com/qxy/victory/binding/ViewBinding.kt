/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qxy.victory.binding

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView
import com.google.android.material.tabs.TabLayout
import com.qxy.victory.R
import com.qxy.victory.model.RankItem
import com.qxy.victory.ui.activity.follow.FollowActivity
import com.qxy.victory.utils.PokemonTypeUtils
import com.qxy.victory.utils.SpacesItemDecoration
import com.skydoves.androidribbon.RibbonRecyclerView
import com.skydoves.androidribbon.ribbonView
import com.skydoves.progressview.ProgressView
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.whatif.whatIfNotNullOrEmpty
import timber.log.Timber

object ViewBinding {

  @JvmStatic
  @BindingAdapter("toast")
  fun bindToast(view: View, text: String?) {
    text.whatIfNotNullOrEmpty {
      Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
    }
  }

  @JvmStatic
  @BindingAdapter("circleImage")
  fun bindLoadCircleImage(view: AppCompatImageView, url: String?) {
    var loadUrl = url
    if (url.isNullOrEmpty()) {
      loadUrl = R.string.img_test.toString()
    }
    Glide.with(view.context)
      .load(loadUrl)
      .circleCrop()
      .into(view)
  }

  @JvmStatic
  @BindingAdapter("paletteImage", "paletteCard")
  fun bindLoadImagePalette(view: AppCompatImageView, url: String, paletteCard: MaterialCardView) {
    Glide.with(view.context)
      .load(url)
      .listener(
        GlidePalette.with(url)
          .use(BitmapPalette.Profile.MUTED_DARK)
          .intoCallBack { palette ->
            val rgb = palette?.darkMutedSwatch?.rgb
            if (rgb != null) {
              paletteCard.setCardBackgroundColor(rgb)
            }
          }.crossfade(true)
      ).into(view)
  }

  @JvmStatic
  @BindingAdapter("paletteImage", "paletteView")
  fun bindLoadImagePaletteView(view: AppCompatImageView, url: String, paletteView: View) {
    val context = view.context
    Glide.with(context)
      .load(url)
      .listener(
        GlidePalette.with(url)
          .use(BitmapPalette.Profile.MUTED_LIGHT)
          .intoCallBack { palette ->
            val light = palette?.lightVibrantSwatch?.rgb
            val domain = palette?.dominantSwatch?.rgb
            if (domain != null) {
              if (light != null) {
                Rainbow(paletteView).palette {
                  +color(domain)
                  +color(light)
                }.background(orientation = RainbowOrientation.TOP_BOTTOM)
              } else {
                paletteView.setBackgroundColor(domain)
              }
              if (context is AppCompatActivity) {
                context.window.apply {
                  addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                  statusBarColor = domain
                }
              }
            }
          }.crossfade(true)
      ).into(view)
  }

  @JvmStatic
  @BindingAdapter("gone")
  fun bindGone(view: View, shouldBeGone: Boolean) {
    view.visibility = if (shouldBeGone) {
      View.GONE
    } else {
      View.VISIBLE
    }
  }

  @JvmStatic
  @BindingAdapter("onBackPressed")
  fun bindOnBackPressed(view: View, onBackPress: Boolean) {
    val context = view.context
    if (onBackPress && context is OnBackPressedDispatcherOwner) {
      view.setOnClickListener {
        context.onBackPressedDispatcher.onBackPressed()
      }
    }
  }

  @JvmStatic
  @BindingAdapter("onFollowClick")
  fun bindFollowClick(view: View, layout: TransformationLayout) {
    view.setOnClickListener(object : View.OnClickListener {
      override fun onClick(v: View?) {
        FollowActivity.startActivity(layout)
      }
    })
  }

  @JvmStatic
  @BindingAdapter("bindPokemonTypes")
  fun bindPokemonTypes(recyclerView: RibbonRecyclerView, types: List<RankItem>?) {
    types.whatIfNotNullOrEmpty {
      recyclerView.clear()
      for (type in it) {
        with(recyclerView) {
          addRibbon(
            ribbonView(context) {
              setText(type.name)
              setTextColor(Color.WHITE)
              setPaddingLeft(84f)
              setPaddingRight(84f)
              setPaddingTop(2f)
              setPaddingBottom(10f)
              setTextSize(16f)
              setRibbonRadius(120f)
              setTextStyle(Typeface.BOLD)
              setRibbonBackgroundColorResource(
                PokemonTypeUtils.getTypeColor(type.name)
              )
            }.apply {
              maxLines = 1
              gravity = Gravity.CENTER
            }
          )
          addItemDecoration(SpacesItemDecoration())
        }
      }
    }
  }

  @JvmStatic
  @BindingAdapter("progressView_labelText")
  fun bindProgressViewLabelText(progressView: ProgressView, text: String?) {
    progressView.labelText = text
  }

  @JvmStatic
  @BindingAdapter("progressView_progress")
  fun bindProgressViewProgress(progressView: ProgressView, value: Int?) {
    if (value != null) {
      progressView.progress = value.toFloat()
    }
  }

  @JvmStatic
  @BindingAdapter("progressView_max")
  fun bindProgressViewMax(progressView: ProgressView, value: Int?) {
    if (value != null) {
      progressView.max = value.toFloat()
    }
  }

  @JvmStatic
  @BindingAdapter("tab_change")
  fun bindTabChange(view: TabLayout, pager: ViewPager2) {
    view.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.position?.let { pager.setCurrentItem(it, false) }
      }

      override fun onTabReselected(tab: TabLayout.Tab?) {
      }

      override fun onTabUnselected(tab: TabLayout.Tab?) {
      }
    })
  }

  @JvmStatic
  @BindingAdapter("tab_change_with_animation")
  fun bindTabChangeWithAnimation(view: TabLayout, pager: ViewPager2) {

    view.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.position?.let { pager.setCurrentItem(it, true) }
      }

      override fun onTabReselected(tab: TabLayout.Tab?) {
      }

      override fun onTabUnselected(tab: TabLayout.Tab?) {
      }
    })
  }

  @JvmStatic
  @BindingAdapter("viewpager_change")
  fun bindViewpagerChangeWithAnimation(view: ViewPager2, tabLayout: TabLayout) {
    // 滑动修改TabLayout
    view.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
      override fun onPageSelected(position: Int) {
        tabLayout.getTabAt(position)?.select()
        Timber.d("onPageSelected: $position")
      }
    })
  }

}
