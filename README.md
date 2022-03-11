### 项目说明

    谨以此项目来学习Java和Android相关的知识


​    

> javalib: Java学习相关
    
    查找算法
    排序算法

    基础算法50题
    https://www.cnblogs.com/tonylp/archive/2013/03/20/2971272.html
    
    100个Java算法实例收集
    http://www.weixueyuan.net/java/suanfa/list_413_2.html

    JAVA经典算法40题
    https://www.jianshu.com/p/e6da97caaa47

> androidlib: 

    DownloadProgressView 下载进度条，文字显示跟随背影变化而变化
    实现：LinearGradient和Matrix

如下图所示：

![DownloadProgressView](/image/download_progress_view.png)


> ui/multitype/.. 目录下是简单封装的多个类型的item布局的RecyclerView 列表
    
    adapter：
        MultiTypeAdapter：处理数据和holder的绑定，不向外暴露，不需要修改
        
    base类：
        MultiTypeBaseHolder：
            holder的基类，其他holder必须继承这个holder，所有的数据操作均放在holder中，向外暴露的holder
        MultiTypeBaseModel：
            数据的基类，定义了基本的数据type，adapter支持的数据类型，所有的数据model均需要继承这个model
    
    MultiTypeListManager：
        类型、UI、holder对应表管理类，在表中添加需要加入的type和对应的UI和holder即可
        
    MultiTypeListModel：
        MultiTypeListManager管理的对照表的model模型
        
    实现方式：
        1、model继承MultiTypeBaseModel，添加需要的字段
        2、holder继承MultiTypeBaseHolder，添加对应的操作
        3、创建UI布局
        4、在MultiTypeListManager中添加对应的表
        5、数据中添加对应的type类型的数据即可
        
    这种方式可以保证向外暴露对应的model和holder和manager类之后，不需要修改adapter
    即使多个人维护多个类型的布局只需要管理好自己的holder即可，不需要的holder只需要在manager中将对应的表删除即可
