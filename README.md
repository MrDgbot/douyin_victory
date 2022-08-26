# douyin_victory


**一、项目介绍**

*极简抖音项目*

*Github  地址，https://github.com/MrDgbot/douyin\_victory/tree/main*


**三、项目实现**


**3.1 技术选型与相关开发文档**

*使用Hilt, Moshi, Coroutines, Flow, Jetpack (Room, ViewModel) 等工具开发MVVM 架构。*

基于开源项目：https://github.com/skydoves/Pokedex搭建本项目MVVM框架

项目架构图：

![Aspose Words a10f0a5b-4525-4f69-9842-eca122ed1dda 001](https://user-images.githubusercontent.com/60038945/185963135-fb36af30-622d-452e-bcaa-ed4fc5e279c9.png)

**3.2 项目代码介绍**

![Aspose Words a10f0a5b-4525-4f69-9842-eca122ed1dda 002](https://user-images.githubusercontent.com/60038945/185963161-17b30c5f-04c1-437e-b47e-9b3dff77924d.png)

项目区分使用Hilt进行注解

Biinding可注解一些方法，在Xml资源中使用

Di层对各类权限和网络请求进行单例注入

initializer做部分工具初始化

model为各类数据类

network存放API等

persistence存放Room存储层

repository存放网络请求实例

ui为页面层

utils为封装各类

**四、测试结果**

*建议从功能测试和性能测试两部分分析，其中功能测试补充测试用例，性能测试补充性能分析报告、可优化点等内容。*


|存在问题|解决方案|
| :-: | :- |
|后续对xml进行复用|对可复用组建进行抽离 include引入|
|未引入网页拦截器|使用反射解析去解析对应Token类拦截|
|关注列表存在异常|API参数错误，未根据文档 使用cursor请求|

**五、Demo 演示视频 （必填）**

实践一截图：

|入口|综艺榜单|剧集榜单|电影榜单|
| :-: | :-: | :-: | :-: |
|![Aspose Words a10f0a5b-4525-4f69-9842-eca122ed1dda 003](https://user-images.githubusercontent.com/60038945/185963320-a8d3ff76-26b3-45d6-88a9-59a54ba60c73.png)|![Aspose Words a10f0a5b-4525-4f69-9842-eca122ed1dda 004](https://user-images.githubusercontent.com/60038945/185963422-bdd9ec88-3b21-49e7-9a7e-4288c5a22037.png)|![Aspose Words a10f0a5b-4525-4f69-9842-eca122ed1dda 005](https://user-images.githubusercontent.com/60038945/185963476-e8e39d70-a611-4a79-bcb1-9aecf7f15337.png)|![Aspose Words a10f0a5b-4525-4f69-9842-eca122ed1dda 005](https://user-images.githubusercontent.com/60038945/185963794-f81eb3bd-489a-4056-9aed-7f96be9c5286.png)

|关注列表|
| :-: |
|![Aspose Words a10f0a5b-4525-4f69-9842-eca122ed1dda 006](https://user-images.githubusercontent.com/60038945/185964024-05b4d253-40bb-4b9e-8ac2-f0d70865651c.jpeg)|
**六、项目总结与反思**

1. *目前仍存在的问题*
2. *已识别出的优化项*
3. *架构演进的可能性*
4. *项目过程中的反思与总结*

1、目前存在问题

- 部分场景不符合UI需求、授权拉取方式有待优化、电影页面未根据文档实现
- 性能测试并未实现
- 代码注释过少

2、已识别出的优化项：

- 拉取授权方式可修改判断条件、视频页面UI有待修改、个人页的功能区有待完善
- 粉丝列表和关注列表的可以增加刷新
- 弹窗报错有待拦截优化，进行封装统一处理
- 网络拦截器可以模块化区分Token，减少性能消耗（目前反射处理[回顾 Android「网络通讯」-- 利用反射 - 掘金](https://juejin.cn/post/7134691372635799588)）

3、架构演进的可能性

- 可以抽出各个包进行模块化处理，如网络请求，个人，数据库等

4、项目过程中的反思与总结

- 人员分配不合理，督促没有到位
- 大家都是初学者，框架选型过重








