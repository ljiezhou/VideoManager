package com.ds.compress.ui.param

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.format.Formatter
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.ds.common.base.activity.BaseVbActivity
import com.ds.compress.R
import com.ds.compress.databinding.ImageCompressParamActivityBinding
import com.ds.compress.ui.edit.result.CompressResultActivity
import com.ds.compress.ui.param.popup.CompressPopup
import com.ds.compress.ui.param.viewmodel.CompressParamViewModel
import com.ds.compress.ui.result.CompleteResultActivity
import com.ds.compress.widget.getValue
import com.ds.compress.widget.setPercentListener
import com.ds.res.ext.setToolbarPrimary
import com.dylanc.longan.startActivity
import com.lxj.xpopup.XPopup

class CompressParamActivity : BaseVbActivity<ImageCompressParamActivityBinding>() {
    private val mViewModel by lazy { ViewModelProvider(this)[CompressParamViewModel::class.java] }
    private val mTitle by lazy { intent.getIntExtra(TITLE, 0) }

    private val arrayList: ArrayList<Uri> by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(EXTRA_RESULT_SELECTION, Uri::class.java) ?: arrayListOf()
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayListExtra(EXTRA_RESULT_SELECTION) ?: arrayListOf()
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.toolbar.setToolbarPrimary(this, title = getString(mTitle))
        mViewBind.imageInfoDescTv.text = getString(R.string.compress_param_image_info_desc, arrayList.size, "0")
        mViewModel.calculateImageStats(arrayList)
        LogUtils.d("initView")

        if (R.string.compress_home_compress_size == mTitle) {
            mViewBind.sizeLayout.sizeRadioLl.visibility = View.VISIBLE
            mViewBind.scaleLayout.root.visibility = View.GONE

            mViewBind.sizeLayout.autoRadio.isChecked = true
            mViewBind.sizeLayout.specifySizeCl.visibility = View.GONE
            mViewBind.sizeLayout.customSizeLayout.percentEt.setText("5000")
        } else if (R.string.compress_home_compress_dimension == mTitle) {
            mViewBind.sizeLayout.root.visibility = View.GONE
            mViewBind.scaleLayout.root.visibility = View.VISIBLE
            mViewBind.scaleLayout.scalePercentAuto.isChecked = true

            mViewBind.scaleLayout.scalePercentLayout.root.visibility = View.VISIBLE
            mViewBind.scaleLayout.scaleResolutionLayout.root.visibility = View.GONE
            mViewBind.scaleLayout.qualitySwitch.isChecked = true
            mViewBind.scaleLayout.qualityPercentLayout.root.visibility = View.GONE
        }
    }

    private var compressPopup: CompressPopup? = null
    override fun createObserver() {
        mViewModel.totalSize.observe(this) {
            mViewBind.imageInfoDescTv.text = getString(R.string.compress_param_image_info_desc, arrayList.size, Formatter.formatFileSize(this, it))
        }
        mViewModel.progress.observe(this) {
            if (compressPopup == null) {
                compressPopup = CompressPopup(this)
                XPopup.Builder(this).dismissOnBackPressed(false).dismissOnTouchOutside(false).asCustom(compressPopup).show()
            }
            compressPopup?.setProgress(it)
            if (it == 100) {
//                CompleteResultActivity.start(this)
                CompressResultActivity.action()
                finish()
            }
        }
    }

    override fun initListener() {
        LogUtils.d("initListener")
        // 大小压缩
        mViewBind.sizeLayout.apply {
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    // 处理 "自动" 选项
                    autoRadio.id -> {
                        specifySizeCl.visibility = View.GONE
                    }
                    // 处理 "指定到大小" 选项
                    specifySizeRadio.id -> {
                        specifySizeCl.visibility = View.VISIBLE
                    }
                }
            }
            LogUtils.d("setPercentListener")
            customSizeLayout.setPercentListener(1000)
        }
        // 缩放照片
        mViewBind.scaleLayout.apply {
            scaleRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    scalePercentAuto.id -> {//百分比
                        scalePercentLayout.root.visibility = View.VISIBLE
                        scaleResolutionLayout.root.visibility = View.GONE
                        LogUtils.d("百分比")
                    }

                    scaleSpecifySizeRadio.id -> {//像素
                        scalePercentLayout.root.visibility = View.GONE
                        scaleResolutionLayout.root.visibility = View.VISIBLE
                        LogUtils.d("像素")
                    }
                }
            }
            qualitySwitch.setOnCheckedChangeListener { _, isChecked ->
                qualityPercentLayout.root.visibility = if (isChecked) View.GONE else View.VISIBLE
            }
        }
        mViewBind.compressTv.setOnClickListener {

            when (mTitle) {
                R.string.compress_home_compress_size -> {// Compress by size  图片大小压缩
                    mViewModel.sizeCompress(mViewBind.sizeLayout.customSizeLayout.getValue())
                }

                R.string.compress_home_compress_dimension -> {// Compress by dimension  图片尺寸压缩
                    scaleCompress()
                }
            }
//            mViewModel.compress(mTitle)
        }
    }

    private fun scaleCompress() {
        mViewBind.scaleLayout.apply {
            var percent = 0
            var width = 0
            var height = 0
            //获取宽高
            if (scalePercentAuto.isChecked) {
                //百分比模式
                if (qualitySwitch.isChecked) {//保持原图质量
                } else {//指定质量
                }
                percent = qualityPercentLayout.percentEt.text.toString().toInt()
            } else {
                //像素模式
                width = scaleResolutionLayout.widthEt.text.toString().toInt()
                height = scaleResolutionLayout.heightEt.text.toString().toInt()
            }
            //获取质量
            val quality = qualityPercentLayout.percentEt.text.toString().toInt()
            mViewModel.scaleCompress(percent, width, height, quality)
        }
    }

    companion object {
        private const val EXTRA_RESULT_SELECTION = "EXTRA_RESULT_SELECTION"
        private const val TITLE = "title"
        fun action(title: Int, arrayList: ArrayList<Uri>) = startActivity<CompressParamActivity>(
            TITLE to title,
            EXTRA_RESULT_SELECTION to arrayList
        )
    }
}