# Add project specific ProGuard rules here.
# By default, Android Studio will automatically add rules for third-party libraries
# that you use in your project.

# If you use Room, ensure its annotations are kept
-keep class androidx.room.RoomDatabase_Impl { *; }
-keep class * extends androidx.room.RoomDatabase {
    public <init>(...);
    public abstract java.util.List getDao(...);
}
-keep class * implements androidx.room.IMultiInstanceInvalidationService { *; }
-keepnames class androidx.room.TypeConverter

# Hilt rules (usually handled by Hilt's own processor, but good to have explicit)
-keep class dagger.hilt.android.internal.managers.ActivityComponentManager
-keep class dagger.hilt.android.internal.managers.FragmentComponentManager
-keep class dagger.hilt.android.internal.managers.ServiceComponentManager
-keep class dagger.hilt.android.internal.managers.BroadcastReceiverComponentManager
-keep class dagger.hilt.android.internal.managers.ContentProviderComponentManager
-keep class dagger.hilt.android.internal.managers.ViewComponentManager
-keep class dagger.hilt.android.internal.managers.ApplicationComponentManager

# Keep all classes annotated with @Keep
-keep @androidx.annotation.Keep class * {*;}
-keepclassmembers class * {
    @androidx.annotation.Keep <methods>;
}

# WorkManager rules
-keep class androidx.work.** { *; }
-keep interface androidx.work.** { *; }

# AdMob rules (usually handled by Play Services, but sometimes explicit rules are needed)
-keep public class com.google.android.gms.ads.** {
    public <fields>;
    public <methods>;
}
-keep public class com.google.ads.mediation.admob.AdMobAdapter { *; }
# Keep custom event classes from being stripped
-keep class * implements com.google.android.gms.ads.mediation.MediationAdapter {
    <init>();
}
-keep class * implements com.google.android.gms.ads.mediation.MediationBannerAdapter {
    <init>();
}
-keep class * implements com.google.android.gms.ads.mediation.MediationInterstitialAdapter {
    <init>();
}
-keep class * implements com.google.android.gms.ads.mediation.MediationNativeAdapter {
    <init>();
}

# Keep Compose related classes
-dontwarn androidx.compose.**
-keep class androidx.compose.** { *; }
-keep class * extends androidx.compose.ui.tooling.preview.PreviewParameterProvider { *; }