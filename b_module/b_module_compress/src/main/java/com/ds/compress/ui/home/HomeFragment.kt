package com.ds.compress.ui.home

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils
import com.ds.appname.AppNameConst
import com.ds.common.base.ItemLayoutData
import com.ds.common.base.fragment.BaseVbFragment
import com.ds.compress.R
import com.ds.compress.databinding.ImageHomeFragmentBinding
import com.ds.compress.ui.aboutus.AboutUsActivity
import com.ds.compress.ui.edit.EditImageActivity
import com.ds.compress.ui.edit.result.CompressResultActivity
import com.ds.compress.ui.param.CompressParamActivity
import com.ds.res.ResConst
import com.dylanc.longan.activityresult.registerForGetMultipleContentsResult
import com.dylanc.longan.activityresult.registerForPickContentResult
import com.gyf.immersionbar.ktx.fitsTitleBarMarginTop
import com.permissionx.guolindev.PermissionX

/**
 * @Author ljiezhou
 * @date 2023/12/4
 * @Description
 */
class HomeFragment : BaseVbFragment<ImageHomeFragmentBinding>() {
    private val mViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val homeAdapter = HomeAdapter()

    private var itemLayoutData: ItemLayoutData? = null
    override fun initView(savedInstanceState: Bundle?) {
        fitsTitleBarMarginTop(mViewBind.topLayout.container)
        mViewBind.topLayout.titleTv.text = getString(AppNameConst.APP_NAME_ID)
        mViewBind.rv.adapter = homeAdapter

        homeAdapter.setOnItemClickListener { adapter, view, position ->
            itemLayoutData = homeAdapter.getItem(position) ?: return@setOnItemClickListener
            when (itemLayoutData!!.titleId) {
                R.string.compress_home_compress_size -> {
                    compress()
                }

                R.string.compress_home_compress_dimension -> {
                    compress()
                }

                R.string.image_compress_by_quality_title -> singleCompress()
                R.string.image_compress_by_scale_title -> compress()
                R.string.image_compress_by_about_us -> AboutUsActivity.action()
                R.string.image_compress_by_contact_us -> contactUs()
                R.string.image_home_test -> {
                    CompressResultActivity.action()
//                        AboutUsActivity.action()
//                        PrivacyActivity.Companion.action(StringUtils.getString(com.module.res.R.string.res_privacy_policy))
                }
            }
        }
        if (ResConst.IS_DEBUG) {
            mViewBind.iconIv.isVisible = false
            mViewBind.topLayout.titleTv.text = ""
        }
    }

    /**
     * 单张压缩
     */
    private fun singleCompress() {
        storagePermission {
            try {
                pickContentLauncher.launchForImage()
            } catch (e: Exception) {
                LogUtils.d(e.message)
            }
        }
    }

    /**
     * 批量压缩
     */
    private fun compress() {
        storagePermission {
            getMultipleContentsLauncher.launchForImage()
        }
    }

    private fun contactUs() {
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${ResConst.email}")
            putExtra(Intent.EXTRA_SUBJECT, getString(AppNameConst.APP_NAME_ID))
            putExtra(Intent.EXTRA_TEXT, "")
            startActivity(this)
        }
    }

    private val permiss by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayListOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                )
        } else {
            arrayListOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

    private fun storagePermission(block: () -> Unit) {
        PermissionX.init(this)
            .permissions(permiss)
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    block.invoke()
                }
            }
    }

    private val imageSelectorLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.apply {
                component = ComponentName(mActivity, EditImageActivity::class.java)
                startActivity(this)
            }
        }
    }

    private val pickContentLauncher = registerForPickContentResult { uri: Uri? ->
        if (uri != null) {
            // 处理 uri
            val intent = Intent(mActivity, EditImageActivity::class.java)
            intent.putExtra(EditImageActivity.EXTRA_RESULT_SELECTION, arrayListOf(uri))
            EditImageActivity.action(intent)
        } else {
            LogUtils.d("没有选择")
        }
    }

    private val getMultipleContentsLauncher = registerForGetMultipleContentsResult { uris ->
        if (uris.isNotEmpty()) {
            val arrayList = arrayListOf<Uri>()
            arrayList.addAll(uris)
            // 处理 uri
//            val intent = Intent(mActivity, EditImageActivity::class.java)
//            intent.putExtra(EditImageActivity.EXTRA_RESULT_SELECTION, arrayList)
//            EditImageActivity.action(intent)

//            val intent = Intent(mActivity, CompressParamActivity::class.java)
//            intent.putExtra(CompressParamActivity.EXTRA_RESULT_SELECTION, arrayList)
            CompressParamActivity.action(itemLayoutData!!.titleId, arrayList)
        } else {
            LogUtils.d("没有选择")
        }
    }

    override fun createObserver() {
        mViewModel.items.observe(this) {
            homeAdapter.submitList(it)
        }
    }

    companion object {
        fun getInstance(): Fragment {
            val fragment = HomeFragment()
            return fragment
        }
    }
}