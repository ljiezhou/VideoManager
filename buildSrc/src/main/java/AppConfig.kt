// Config.kt

object AppConfig {
    const val compileSdkVersion = 34
    const val minSdkVersion = 22
    const val targetSdkVersion = 34

    const val applicationId = "com.ds.image.compress"
    const val applicationName = "图片压缩"

    const val versionCode = 2
    const val versionName = "1.0.0"

    const val PRIVACY_POLICY = "https://food.idishi.cn/project/photo/privacy.html"
    const val TERMS_OF_SERVICE = "https://food.idishi.cn/project/photo/tos.html"

    const val outputFileName = versionName + "_base"

    const val localProperties = "local.properties"

    const val email = ""

    const val DATA_BASE_NAME = "compress_database"
    const val DATA_BASE_VERSION = 1
}

object SDKConfig {
    var BUGLY_APPID = ""
    var BUGLY_APPKEY = ""

    var UM_APP_KEY = ""
    const val UM_PUSHSE = ""

    const val COMPOSE_VERSION = "1.5.3"

    const val GDT_APP_ID = ""
}

object ModuleConfig {
    const val app = ":app"
    const val app_app = ":app_app"


    const val b_module = ":b_module"
    const val b_module_compress = ":${b_module}:b_module_compress"
    const val b_module_video_manager = ":${b_module}:b_module_video"
    const val b_module_image = ":${b_module}:b_module_image"
    const val b_module_scan = ":${b_module}:b_module_scan"
    const val b_module_comui = ":${b_module}:b_module_comui"
    const val camerax = ":${b_module}:camerax"


//    tool
    const val e_module = ":e_module_tool"
    const val m_module_ad = ":${e_module}:m_module_ad"

    const val m_module_net = ":${e_module}:m_module_net"
    const val m_module_room = ":${e_module}:m_module_room"
    const val m_module_opencv = ":${e_module}:m_module_opencv"
    const val m_module_permission = ":${e_module}:m_module_permission"
    const val m_module_pictureselector = ":${e_module}:m_module_pictureselector"


//    base
    const val f_module = ":f_module_base"
    const val x_module_common = ":${f_module}:x_module_common"
    const val z_module_commonsdk = ":${f_module}:z_module_commonsdk"
    const val z_module_compose = ":${f_module}:z_module_compose"
    const val z_module_image = ":${f_module}:z_module_image"
    const val z_module_res = ":${f_module}:z_module_res"
    const val z_module_round_view = ":${f_module}:z_module_round_view"
    const val z_module_screenmatch = ":${f_module}:z_module_screenmatch"
    const val z_module_app_name = ":${f_module}:z_module_app_name"
    const val z_module_therouter = ":${f_module}:z_module_therouter"

//    const val z_module_screenmatch = ":f_module_base:z_module_screenmatch"
}