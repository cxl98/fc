### ****1.1 数据结构的操作****

**下面简单介绍常见命令：**

#### 一、**字符串（strings）：**

 + 添加：set key value
 + 取值：get key
 + (整型)递增： incr key
 + (多值)添加：mset key1 val1 key2 val2 key3 val3
 + (多值)取值：mget key1 key2 key3
 + 修改：set key newVal
 + 查询（是否存在）：exists key
 + 删除：del key
 + 查询类型：type key
 + (创建值后)设置超时（time时间后将key对应值删除）：expire key time
 + (创建值时)设置超时：set key val ex time
 + 去除超时：persist key
 + 查看超时剩余时间：ttl key
 
#### 二、**散列（hashes）：**

 * 添加多值：hmset yourhash field val [field val ...]
 * 添加单值：hset yourhash field val
 * 取多值：hmget yourhash field [field ...]
 * 取单值：hget yourhash field
 * 取全值： hgetall yourhash
 * 删除值：hdel yourhash field [field ...]

#### 三、**列表（lists）：**

 * 链表左边添加：lpush list val
 * 链表右边添加：rpush list val
 * 范围内取值：lrange list index_start index_end
 * 截取范围内值：ltrim list index_start index_end
 * 添加多值：rpush list val1 val2 val3 val4
 * 左边删除：lpop list
 * 右边删除: rpop list
 * 阻塞式访问左删除：blpop list 或者 blpop list1 list2 list3 
 * 阻塞式访问右删除：brpop list 或者 brpop list1 list2 list3
 * 原子性地返回并移除存储在 list1 的列表的最后一个元素（列表尾部元素）， 并把该元素放入存储在 list2的列表的第一个元素位置（列表头部） : rpoplpush list1 list2
 * 阻塞版RPOPLPUSH：brpoplpush list1 list2
 
#### 四、**集合（sets）： String 的无序排列 ， 适合用于表示对象间的关系 。**

 * 添加： sadd myset val1 val2 val3
 * 查询元素：smembers myset
 * 查询特定元素： sismember myset val
 * 删除（随机）：spop myset
 
#### 五、**有序集合（sorted sets）：**

 * 添加（更新）：zadd mysortedset score val [score val ...]
 * 范围内取值：zrange mysortedset score_begin score_end
 * 取索引值：zscore mysortedset val
 * 删除索引值最大的值： zpopmax mysortedset
 * 删除索引值最小的值： zpopmin mysortedset
 
#### 六、**bitmaps：不是实际的数据结构，而是一个字符串类型定义的面向比特的集合。**

 * 添加值：setbit key offset [offset ...]
 * 取值：getbit key offset
 
#### 七、**hyperloglogs：是一种概率的数据结构，用于计算唯一的数据。**

 * 添加：pfadd key element [element ...]
 * 合并： pfmerge key key1 key2