
## JetpackCompose

1. 与传统Android UI相比有哪些优势？

* 命令式
Android传统UI框架属于命令式: view hierarchy是由View构成的树, 当app的状态改变时, UI需要更新. 通常的做法是通过findViewById()等手段找到要更新的节点, 通过set方法更新控件的内部状态. 每个控件都有自己的内部状态, 并且暴露getter/setter, 允许程序逻辑和控件交互.

  缺点:

  * 手动地更新view容易出错，容易创建复杂状态，复杂性随view个数而增加.
  * 很容易创建非法状态, 比如两个更新以不期望的方式冲突了. (比如: 一个要更新值, 另一个要移除节点.)

* 声明式
Jetpack Compose是一个声明式的UI framework. 目的就是简化构建和更新UI，该技术的原理是从头开始重新生成整个屏幕, 仅做必要的更改. 这种方法避免了手动更新view的复杂度.
当数据改变时, composable负责把当前的应用状态转化为UI.

  特点：
  * 声明式UI, 写起来更快, 不容易出错
  * 基于组合的Composable, 比基于继承的View体系, 更加灵活, 易于复用. 比如可以通过组合来达到复用多个源, 不再受单继承限制.
  * 和Jetpack系列的其他库都能完美结合. (ViewModel, Kotlin Flow, Coroutines, Paging, Hilt, Navigation...)
  * 解决了传统Android数据和View的分离, View的无感知与自动更新, 清晰的逻辑管理和分离, 可测试性等等.

















*  Materital 部分Icons图标找不到无法使用
     * 解决方法：
       *https://fonts.google.com/icons*
       ` implementation "androidx.compose.material:material-icons-extended:$compose_version" `

