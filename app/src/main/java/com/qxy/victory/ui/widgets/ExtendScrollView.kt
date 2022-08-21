package com.qxy.victory.ui.widgets

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.qxy.victory.R
import com.qxy.victory.utils.DisplayUtil.dp2px


class ExtendScrollView : NestedScrollView {

  lateinit var mZoomView: View
  private var mZoomViewWidth = 0
  private var mZoomViewHeight = 0
  private var mViewGroupWidth = 0
  private var mViewGroupHeight = 0
  private var isTouch: Boolean = false
  private var x = 0
  private var y = 0
  private var isToTop = false
  private var isGetDown = false
  private var isIntercept = false
  private var isFling = false
  lateinit var mViewGroup: ViewGroup

  //记录第一次按下的位置
  private var firstPosition = 0f

  //是否正在缩放
  private var isScrolling = false

  //缩放系数，缩放系数越大，变化的越大
  private var mScrollRate = 0.6f

  //回调系数，越大，回调越慢
  private var mReplyRate = 0.5f


  private companion object {
    const val TAG = "ExtendScrollView"
  }

  constructor(context: Context?) : super(context!!) {}
  constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context!!,
    attrs,
    defStyleAttr
  )

  /**
   * 当视图膨胀时调用。
   */
  override fun onFinishInflate() {
    super.onFinishInflate()
    init()
  }

  /**
   * 它获取包含图像视图的视图组和包含文本视图的视图组。
   */
  private fun init() {
    overScrollMode = OVER_SCROLL_NEVER
    mZoomView = findViewById(R.id.mine_bg)
    mViewGroup = findViewById(R.id.linear_layout)

  }

  @SuppressLint("ClickableViewAccessibility")
  /**
   * 当用户触摸屏幕时调用的函数。
   *
   * @param ev 运动事件
   * @return 返回值是一个布尔值。
   */
  override fun onTouchEvent(ev: MotionEvent): Boolean {
    if (mZoomViewWidth <= 0 || mZoomViewHeight <= 0) {
      mZoomViewWidth = mZoomView.measuredWidth
      mZoomViewHeight = mZoomView.measuredHeight
    }
    if (mViewGroupWidth <= 0 || mViewGroupHeight <= 0) {
      mViewGroupWidth = mViewGroup.measuredWidth
      mViewGroupHeight = mViewGroup.measuredHeight
    }
    when (ev.action) {
      MotionEvent.ACTION_DOWN -> {
        isTouch = true
        x = ev.x.toInt()
        y = ev.y.toInt()

      }
      MotionEvent.ACTION_UP -> {
        //手指离开后恢复图片
        isScrolling = false
        replyImage()
        isTouch = false
        isGetDown = false
        isIntercept = false
        isFling = true

      }
      MotionEvent.ACTION_MOVE -> {
        isTouch = true
        isFling = false
        if (!isScrolling) {
          if (scrollY == 0) {
            firstPosition = ev.y // 滚动到顶部时记录位置，否则正常返回
          }
        }
        // 滚动距离乘以一个系数
        val distance = ((ev.y - firstPosition) * mScrollRate).toInt()
        if (distance < 0) { // 当前位置比记录位置要小，正常返回
          return true
        }
        isScrolling = true
        setZoom(distance.toFloat())

        return true // 返回true表示已经完成触摸事件，不再处理
      }
    }
    return true
  }

  //回弹动画
  private fun replyImage() {
    val distance = (mZoomView.measuredWidth - mZoomViewWidth).toFloat()
    val valueAnimator =
      ValueAnimator.ofFloat(distance, 0f).setDuration((distance * mReplyRate).toLong())
    valueAnimator.addUpdateListener { animation -> setZoom(animation.animatedValue as Float) }
    valueAnimator.start()
  }

  private fun setZoom(distance: Float) {

    if (mZoomViewWidth <= 0 || mZoomViewHeight <= 0) {
      return
    }
    val lp = mZoomView.layoutParams
    lp.width = (mZoomViewWidth + distance).toInt()
    lp.height = (mZoomViewHeight * ((mZoomViewWidth + distance) / mZoomViewWidth)).toInt()
    if (lp.height >= (mViewGroupHeight / 2.3)) {
      lp.height = (mViewGroupHeight / 2.3).toInt()
    }
    (lp as MarginLayoutParams).setMargins(
      -(lp.width - mZoomViewWidth) / 2,
      0,
      0,
      0
    )
    mZoomView.layoutParams = lp

  }

  private fun <T> getChildView(viewGroup: View?, targetClass: Class<T>): T? {
    if (viewGroup != null && viewGroup.javaClass == targetClass) {
      return viewGroup as T
    }
    if (viewGroup is ViewGroup) {
      for (i in 0 until viewGroup.childCount) {
        val view = viewGroup.getChildAt(i)
        if (view is ViewGroup) {
          val result = getChildView(view, targetClass)
          if (result != null) {
            return result
          }
        }
      }
    }
    return null
  }

  override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {


    // tablayout_viewpager 的高度和 NestedScrollView 的高度一样，
    // 所以可以用 LinearLayoutCompat的高度 - NestedScrollView的高度，得到 AppCompatImageView
    val headerViewHeight = getChildAt(0).measuredHeight - measuredHeight
    if (scrollY < headerViewHeight) {
      scrollBy(0, dy)
      consumed[1] = dy
    }


  }

  override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
    super.onScrollChanged(l, t, oldl, oldt)
    if (t == 0) {
      isGetDown = true

      if (!isTouch) {
        if (isFling) {
          isFling = false
          toTopScaleAnimator()
        }

      }
    } else {
      isToTop = false
    }
  }

  override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
    if (isGetDown) {
      isIntercept = true
      return true
    }
    return super.onInterceptTouchEvent(ev)
  }

  private fun toTopScaleAnimator() {

    if (!isTouch) {
      val scaleAnimator = ValueAnimator.ofFloat(1f, 2.6f)
      scaleAnimator.addUpdateListener {
        val scale = it.animatedValue as Float
        val lp = mZoomView.layoutParams
        lp.width = (mZoomViewWidth + scale * 400).toInt()
        lp.height =
          (mZoomViewHeight * ((mZoomViewWidth + scale * 400) / mZoomViewWidth)).toInt()
        (lp as MarginLayoutParams).setMargins(-(lp.width - mZoomViewWidth) / 2, 0, 0, 0)
        mZoomView.layoutParams = lp

      }
      scaleAnimator.duration = 300
      scaleAnimator.interpolator = LinearInterpolator()
/*        val tranAnimator = ObjectAnimator  .ofFloat (mViewGroup,"translationY",0f, ((mViewGroup?.y?.let {
            mZoomView?.y?.minus(
                it
            )
        } ?: 0f) -dp2px(context,30)))*/


      val transAnimator = ValueAnimator.ofFloat(0f, 1f)

      transAnimator.addUpdateListener {
        val cp = mViewGroup.layoutParams
        (cp as MarginLayoutParams).setMargins(
          0,
          mZoomView.bottom - dp2px(context, 30),
          0,
          0
        )
        mViewGroup.layoutParams = cp
      }
      transAnimator.duration = 300
      transAnimator.interpolator = LinearInterpolator()


      val set = AnimatorSet()
      set.playTogether(scaleAnimator, transAnimator)

      set.start()
      set.addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
          if (!isTouch) {
            replyImage()
          }
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationRepeat(animation: Animator?) {
        }

      })

    }
  }

  override fun fling(velocityY: Int) {
    super.fling(velocityY)
    if (velocityY > 0) {
      val viewPager2 = findViewById<ViewPager2>(R.id.mine_view_pager)
      if (viewPager2 != null) {
        val childRecyclerView = getChildView(
          viewPager2.getChildAt(0) as ViewGroup,
          RecyclerView::class.java
        )

        childRecyclerView?.fling(0, velocityY)
        isFling = true
      }
    }
  }

}