package com.ds.common.base.activity

//noinspection SuspiciousImport
import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.common.ext.getVmClazz

/**
 * @Author ljiezhou
 * @date 2023/11/25 14:07
 * @Description
 */
abstract class BaseComposeActivity<VM : BaseViewModel> : ComponentActivity() {
    protected val mContext = this
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel()
    }

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this)[getVmClazz(this)]
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}