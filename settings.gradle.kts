pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        google()
//        jcenter()

        flatDir {
            dirs("zzz-ExternalAAR", "libs")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
//        jcenter()
        mavenCentral()

        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/jcenter") }
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/apache-snapshots") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/central") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/apache-snapshots") }

        maven { url = uri("https://repo1.maven.org/maven2/") }
        maven { url = uri("https://jitpack.io") }


        flatDir {
            dirs("libs")
            dirs("zzz-ExternalAAR")
        }
    }
}

//rootProject.name = "com.ds.image.compress"
rootProject.name = "Video"
//rootProject.name = applicationId
include(":app")

include(":app_app")

//f-module
include(":b_module:b_module_compress")
include(":b_module:b_module_video")
//include(":b_module:matisse")
//include(":b_module:b_module_image")
//include(":b_module:b_module_scan")

include(":b_module:b_module_comui")
include(":b_module:camerax")
//include(":b_module:matisse")

include(":e_module_tool:m_module_ad")
include(":e_module_tool:m_module_net")
include(":e_module_tool:m_module_room")
include(":e_module_tool:m_module_opencv")
include(":e_module_tool:m_module_permission")
include(":e_module_tool:m_module_pictureselector")

include(":f_module_base:x_module_common")
include(":f_module_base:z_module_commonsdk")
include(":f_module_base:z_module_compose")
include(":f_module_base:z_module_image")
include(":f_module_base:z_module_res")
include(":f_module_base:z_module_round_view")
include(":f_module_base:z_module_screenmatch")
include(":f_module_base:z_module_app_name")
include(":f_module_base:z_module_therouter")

include(":z_module_screenmatch")
