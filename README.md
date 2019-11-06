# MomentView

## 添加依赖

1. 在项目的 `build.gradle` 文件中添加仓库:

```Groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

```Groovy
// 可以 Release 页面中查看最新版本并替换
implementation 'com.github.oswayne:MomentView:1.0.0'
```