package com.ds.app.ui.splash

import android.app.Activity
import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.ds.app.databinding.AppSplashDialogBinding
import com.ds.commonui.web.PrivacyActivity
import com.dylanc.longan.getString
import com.lxj.xpopup.core.CenterPopupView
import com.ds.common.datastore.SettingsRepository
import com.ds.common.therouter.commonui.CommonUIManager
import com.ds.res.ResConst
import com.ds.app.R
import com.ds.app.databinding.AppSplashDialogItemBinding
import com.dylanc.longan.addUnderline
import com.dylanc.longan.appendClickable
import com.dylanc.longan.transparentHighlightColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @Author ljiezhou
 * @date 2023/7/30
 * @Description
 */
class SplashDialog(context: Context) : CenterPopupView(context) {

    private val serviceAgreement by lazy { getString(com.module.res.R.string.res_terms_of_service) }
    private val privacyProtocol by lazy { getString(com.module.res.R.string.res_privacy_policy) }
    private val itemDesc by lazy {
        arrayListOf(
            context.getString(R.string.app_main_privacy_dialog_desc, ResConst.APP_NAME),
            getString(R.string.app_main_privacy_dialog_desc2)
        )
    }

    override fun getImplLayoutId(): Int {
        return R.layout.app_splash_dialog
    }

    private lateinit var binding: AppSplashDialogBinding
    override fun onCreate() {
        super.onCreate()
        binding = AppSplashDialogBinding.bind(popupImplView)

        binding.rv.adapter = DescAdapter().apply {
            submitList(itemDesc)
        }

        val color = com.module.res.R.color.common_res_primary_color
        binding.dialogDesc3.text = buildSpannedString {
            append(getString(R.string.app_main_privacy_dialog_desc3))
            color(ContextCompat.getColor(context, color)) {
                appendClickable(context.getString(com.module.res.R.string.main_book, privacyProtocol)) {
                    CommonUIManager.privacy(privacyProtocol)
                }
            }
            append(getString(R.string.app_main_privacy_dialog_and))
            color(ContextCompat.getColor(context, color)) {
                appendClickable(context.getString(com.module.res.R.string.main_book, serviceAgreement)) {
                    CommonUIManager.privacy(serviceAgreement)
                }
            }
        }
        binding.dialogDesc3.movementMethod = LinkMovementMethod.getInstance()
        binding.dialogDesc3.transparentHighlightColor()

        binding.dialogSure.setOnClickListener { sure() }
        binding.dialogCancel.setOnClickListener { cancel() }
        binding.dialogCancel.addUnderline()
    }

    private fun sure() {
        GlobalScope.launch {
            SettingsRepository.isFirst.set(false)
        }
        postDelayed({
            dismiss()
        }, 350)
    }

    private class DescAdapter : BaseQuickAdapter<String, DescAdapter.VH>() {

        class VH(
            parent: ViewGroup,
            val binding: AppSplashDialogItemBinding = AppSplashDialogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) : RecyclerView.ViewHolder(binding.root)

        override fun onBindViewHolder(holder: VH, position: Int, item: String?) {
            if (item == null) {
                return
            }
            holder.binding.apply {
                dialogDesc.text = item
            }
        }

        override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): VH {
            return VH(parent)
        }

    }

    private fun cancel() {
//        LogcatUtil.d("点击拒绝")
        activity?.apply {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

}
