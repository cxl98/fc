# 注意!

### 思路如下
* 客户端发送匹配request -----开始计时
* 加入服务器的匹配队列 ---返回开始匹配true ---客户端开始计时
* 匹配成功 ----- queue.remove
* 匹配失败  ----- 超时: 客户端的计时超时，给服务器返回错误信息，remove
          -----  异常: 服务器给客户端发送失败
* 对应channelId,返回对手的信息(id,name)
* 绑定这两个玩家，直传操作类，去客户端处理信息

### 游戏业务
1 --- 登录注册
2 --- 玩家匹配
3 --- 战斗数据
4 --- 保持心跳
5 --- 数据缓存
6 --- 日志记录
7 --- 副本打怪


### 待处理:
* 不需要每个包都有netty---到最后要整合代码,取消重复部分
* redis需要设置过期时间

### 关于测试模板 (见单测,无法运行,要调试)
* 1----注册
* 2----登录
* 3----匹配