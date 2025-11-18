package com.ds.compress.ui.edit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.ds.common.base.ItemLayoutData
import com.ds.common.base.activity.BaseVbActivity
import com.ds.compress.R
import com.ds.compress.databinding.ImageEditActivityBinding
import com.ds.compress.ui.edit.adapter.EditImageAdapter
import com.ds.compress.ui.edit.adapter.QualityAdapter
import com.ds.compress.ui.edit.popup.CustomSizePopup
import com.ds.compress.ui.edit.popup.EditImageProgressPopup
import com.ds.compress.ui.edit.result.CompressResultActivity
import com.ds.compress.ui.edit.viewmodel.EditImageViewModel
import com.dylanc.longan.safeIntentExtras
import com.dylanc.longan.startActivity
import com.gyf.immersionbar.ktx.fitsTitleBarMarginTop
import com.lxj.xpopup.XPopup

/**
 * @Author ljiezhou
 * @date 2023/12/6
 * @Description
 */
class EditImageActivity : BaseVbActivity<ImageEditActivityBinding>() {
    private val mViewModel by lazy { ViewModelProvider(this)[EditImageViewModel::class.java] }
    private val uris: List<Uri> by safeIntentExtras(EXTRA_RESULT_SELECTION)
    private val editImageAdapter = EditImageAdapter()
    private val scalAdapter: QualityAdapter = QualityAdapter()
    private val qualityAdapter: QualityAdapter = QualityAdapter()
    private val editImageProgressPopup by lazy {
        EditImageProgressPopup(this@EditImageActivity)
    }

    override fun initView(savedInstanceState: Bundle?) {
        fitsTitleBarMarginTop(mViewBind.topLayout.container)
        BarUtils.setStatusBarLightMode(this, true)
        mViewBind.apply {
            editContentI.scalRv.adapter = scalAdapter
            editContentI.qualityRv.adapter = qualityAdapter
            vp2.adapter = editImageAdapter
            root.post {
                mViewModel.imageItems(uris)
            }
        }
    }

    private fun notifyData(adapter: QualityAdapter, item: ItemLayoutData) {
        adapter.items.forEach {
            it.isChecked = it.titleId == item.titleId
        }
        adapter.notifyDataSetChanged()
    }

    fun showLoading(message: String) {
        mViewBind.vp2.isUserInputEnabled = false
        mViewBind.progressLayout.root.visibility = View.VISIBLE
    }

    fun dismissLoading() {
        mViewBind.vp2.postDelayed({
            mViewBind.vp2.isUserInputEnabled = true
            mViewBind.progressLayout.root.visibility = View.GONE
        }, 200)
    }

    override fun createObserver() {
        mViewModel.apply {
            scales.observe(this@EditImageActivity) {
                scalAdapter.submitList(it)
            }
            qualitys.observe(this@EditImageActivity) {
                qualityAdapter.submitList(it)
            }
            imageItems.observe(this@EditImageActivity) {
                editImageAdapter.submitList(it)
            }
            currentImageSize.observe(this@EditImageActivity) {
                mViewBind.currentImageDescTv.text = it
            }
            compressImageSize.observe(this@EditImageActivity) {
                mViewBind.compressImageDescTv.text = it
            }
            compressItem.observe(this@EditImageActivity) {
                editImageAdapter.notifyItemChanged(mViewModel.currentPosition)
            }
            saveResult.observe(this@EditImageActivity) {
                if (it) {
                    ToastUtils.showShort("压缩成功")
                    CompressResultActivity.action()
                    finish()
                }
            }
            currentCompressPosition.observe(this@EditImageActivity) {
                if (editImageProgressPopup.isShow)
                    editImageProgressPopup.setCompressDesc(it)
            }
            isCompress.observe(this@EditImageActivity) {
                XPopup.Builder(this@EditImageActivity).dismissOnBackPressed(false).dismissOnTouchOutside(false).asCustom(editImageProgressPopup).show()
            }
        }
    }

    override fun initListener() {
        //压缩流程 每次选择，都会马上执行压缩并预览
        scalAdapter.setOnItemClickListener { adapter, view, position ->
            scalAdapter.getItem(position)?.apply {
                if (titleId == R.string.image_edit_scale_custom_title) {
                    customPopup(this)
                } else {
                    notifyData(scalAdapter, this)
                    mViewModel.setParam()
                }
            }
        }
        qualityAdapter.setOnItemClickListener { adapter, view, position ->
            qualityAdapter.getItem(position)?.apply {
                notifyData(qualityAdapter, this)
                mViewModel.setParam()
            }
        }
        editImageAdapter.canScrollListener = object : EditImageAdapter.CanScrollListener {
            override fun canScroll(canScrool: Boolean) {
                mViewBind.vp2.isUserInputEnabled = canScrool
            }
        }
        mViewBind.apply {
            editSaveBtn.setOnClickListener {
                mViewModel.save2Album()
            }
            vp2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    LogUtils.d(position)
//                    ?.apply {editImageAdapter.getItem(position)
//                        mViewModel.currentImage(this.uri,position)
//                    }
                    editImageAdapter.items.forEachIndexed { index, imageItem ->
                        if (index == position) {
                            mViewModel.currentImage(imageItem.uri, position)
                        } else {
                            imageItem.compressUri?.let { it.recycle() }
                        }
                    }
                }
            })
        }
    }

    private fun customPopup(layoutData: ItemLayoutData) {
        XPopup.Builder(this@EditImageActivity).asCustom(CustomSizePopup(this@EditImageActivity).apply {
            customSizeListener = object : CustomSizePopup.CustomSizeListener {
                override fun customSize(width: Int, height: Int) {
                    mViewModel.setCustomSize(width, height)

                    notifyData(scalAdapter, layoutData)
                    mViewModel.setParam()
                }
            }
        }).show()
    }

    companion object {
        fun action(intent: Intent) {
            startActivity(intent)
        }
        const val EXTRA_RESULT_SELECTION = "EXTRA_RESULT_SELECTION"
    }
}