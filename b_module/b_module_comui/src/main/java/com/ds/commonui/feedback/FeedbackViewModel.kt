package com.ds.commonui.feedback

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.common.net.apiService
import com.ds.common.net.request
import com.ds.common.net.stateCallback.UpdateUiState

/**
 * @Author ljiezhou
 * @date 2023/8/15
 * @Description
 */
class FeedbackViewModel : BaseViewModel() {

    var updateUi = MutableLiveData<UpdateUiState<Any>>()
    fun feedback(message: String) {
        request({
            apiService.message(message)
        }, {
            updateUi.value = UpdateUiState(true)
        }, {
            //请求失败
            updateUi.value = UpdateUiState(false)
            LogUtils.d("失败")
        }, isShowDialog = true)
    }
}
