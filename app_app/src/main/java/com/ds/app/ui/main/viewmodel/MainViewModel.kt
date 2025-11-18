package com.ds.app.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ds.app.ui.main.repository.MainRepository
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.res.NavItem

/**
 * @Author ljiezhou
 * @date 2023/5/13
 * @Description
 */
class MainViewModel : BaseViewModel() {
    val navs = MutableLiveData<List<NavItem>>()

    init {
        initFragment()
    }

    private fun initFragment() {
        navs.value = MainRepository.getItems()
    }
}
