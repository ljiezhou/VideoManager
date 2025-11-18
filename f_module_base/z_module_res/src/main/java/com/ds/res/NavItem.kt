package com.ds.res

import androidx.fragment.app.Fragment

/**
 * @Author ljiezhou
 * @date 2023/5/14
 * @Description
 */
class NavItem {
    var id: Int = 0
    var fragment: Fragment
    var title: Int = 0
    var icon: Int = 0
    var isLight: Boolean = false
    // 新增：用于排序的序号（默认 0）
    var order: Int = 0

    constructor(id: Int, fragment: Fragment, title: Int, icon: Int, isLight: Boolean = false) {
        this.id = id
        this.fragment = fragment
        this.title = title
        this.icon = icon
        this.isLight = isLight
    }

    constructor(fragment: Fragment, title: Int, icon: Int) {
        this.fragment = fragment
        this.title = title
        this.icon = icon
    }

    constructor(title: Int, fragment: Fragment) {
        this.fragment = fragment
        this.title = title
    }

    constructor(id: Int, fragment: Fragment, icon: Int) {
        this.id = id
        this.fragment = fragment
        this.icon = icon
    }
}
