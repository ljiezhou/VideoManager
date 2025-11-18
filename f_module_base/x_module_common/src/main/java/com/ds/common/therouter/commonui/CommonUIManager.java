package com.ds.common.therouter.commonui;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.ds.common.CommonConst;
import com.ds.common.therouter.Path;
import com.ds.res.ResConst;
import com.therouter.TheRouter;

import java.util.Objects;

/**
 * @Author ljiezhou
 * @date 2023/9/24
 * @Description
 */
public class CommonUIManager {
    public static void privacy(String title) {
        LogUtils.d("privacy");
        String url = "";

        if (Objects.equals(title, StringUtils.getString(com.module.res.R.string.res_privacy_policy))) {
            url = ResConst.privacy_policy_url;
        } else if (Objects.equals(title, StringUtils.getString(com.module.res.R.string.res_terms_of_service))) {
            url = ResConst.terms_of_service_url;
        }
        TheRouter.build(Path.App.PRIVACYACTIVITY)
                .withString(CommonConst.TITLE, title)
                .withString(CommonConst.URL, url).navigation();
    }

    public static void feedback() {
        TheRouter.build(Path.App.FEEDBACKACTIVITY).navigation();
    }
}
