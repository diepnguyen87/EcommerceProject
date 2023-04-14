# Download app
Please go to: https://github.com/sdetpro-blog/sdetpro-shop-app/releases

# Find AppPackage and AppActivity
adb shell dumpsys window | grep -E mCurrentFocus
adb shell "dumpsys activity activities | grep mResumedActivity" => work on android Q/R
