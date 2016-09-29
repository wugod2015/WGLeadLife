# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android_Studio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
## ----------------------------------
##   ########## Retrofit混淆    ##########
## ----------------------------------
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-dontwarn okio.**
## ----------------------------------
##   ########## Gson混淆    ##########
## ----------------------------------
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.Android.model.** { *; }


# # -------------------------------------------
# #  ######## greenDao混淆  ##########
# # -------------------------------------------
-libraryjars libs/greendao-2.1.0.jar
-keep class de.greenrobot.dao.** {*;}
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static Java.lang.String TABLENAME;
}
-keep class **$Properties

# # -------------------------------------------
# #  ######## zxing混淆  ##########
# # -------------------------------------------
-dontwarn com.google.zxing.**
-keep  class com.google.zxing.**{*;}

## ----------------------------------
##   ########## bean混淆    ##########
## ----------------------------------
-keep com.wugod.wg_framework2.bean.** {*;}

## ----------------------------------
##   ########## Glide混淆    ##########
## ----------------------------------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule