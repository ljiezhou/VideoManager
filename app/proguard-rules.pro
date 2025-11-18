# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-flattenpackagehierarchy "com.ds.scan.manager"
-allowaccessmodification

# 混淆字典
-obfuscationdictionary proguard_keywords.txt
-classobfuscationdictionary proguard_keywords.txt
-packageobfuscationdictionary proguard_keywords.txt


-keep public class com.ds.food.R$*{
public static final int *;
}


##############################去掉所有打印 包括自己封装的
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** e(...);
    public static *** i(...);
    public static *** v(...);
    public static *** println(...);
    public static *** w(...);
    public static *** wtf(...);
}
-assumenosideeffects class android.util.Log {
   public static *** d(...);
   public static *** v(...);
}

-assumenosideeffects class android.util.Log {
    public static *** e(...);
    public static *** v(...);
}

-assumenosideeffects class android.util.Log {
    public static *** i(...);
    public static *** v(...);
}

-assumenosideeffects class android.util.Log {
    public static *** w(...);
    public static *** v(...);
}
-assumenosideeffects class java.io.PrintStream {
    public *** println(...);
    public *** print(...);
}

-assumenosideeffects class com.blankj.utilcode.util.LogUtils{
    public static *** d(...);
    public static *** dTag(...);

    public static *** e(...);
    public static *** eTag(...);

    public static *** i(...);
    public static *** iTag(...);

    public static *** w(...);
    public static *** wTag(...);
}

-assumenosideeffects class com.ds.common.util.LogcatUtils{
    public static *** d(...);
    public static *** dTag(...);

    public static *** e(...);
    public static *** eTag(...);

    public static *** i(...);
    public static *** iTag(...);

    public static *** w(...);
    public static *** wTag(...);
}


-keepattributes Signature
