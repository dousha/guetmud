# 通信协议说明

## 包格式

数据包格式如下：

| 字节偏移量 :|: 含义 |
|-------------|-------|
| 0x00 | 数据包类型 |
| 0x01 | 数据区长度（字节数） |
| 0x05 | 数据（可选） |

如果数据包不包含其他数据，则数据区长度为 0.

所有包（发自客户端和发自服务器）的格式均相同。

客户端发送的数据包的可能的类型、其含义和具体格式如下：

## 0x00 - 心跳

类型： 0x00

长度： 4

数据：32 位无符号整数，代表此为第几个心跳包。

说明：心跳包用于确认服务器与客户端仍在通信。其发送频率为 1 次/秒。

若服务端持续未收到来自客户端的心跳包超过 5 秒，则服务器单方面断开连接。

回复：0x80

## 0x01 - 玩家速度改变

类型： 0x01

长度： 19

数据： 64 位玩家 ID、16 位当前地图编号、32 位当前 X 位置、32 位当前 Y 位置 和 4 位稀疏位，代表速度方向（以 1 字节发送）。

说明：稀疏位由高位向低位依次表示「左」、「上」、「右」、「下」。

**目前**只能有一个位为 1, 代表玩家走动的方向。如果玩家停下，则所有位均为 0.

回复：无，当服务器认为这个包是作弊时，回复 0xff.

## 0x02 - 玩家聊天

类型： 0x02

长度：不定

数据：头 8 字节为玩家 ID, 之后为玩家聊天的内容， UTF-8 编码。

说明：如果玩家聊天内容以 '`' 开始，则认为这是一个命令，并转交给命令处理器进行解析。

回复：普通聊天内容回复 0x82, 命令内容回复 0x83.

## 0x80 - 心跳回复

类型： 0x80

长度： 1

数据： 2 位状态码（以 1 字节发送）。

说明：服务器收到客户端心跳包后会发送此包回复。状态码可能的值如下：

* 0b00 - 已成功接收心跳包
* 0b01 - 脱漏了早期心跳包
* 0b10 - 重复了早期心跳包
* 0b11 - 心跳包不合法

## 0x81 - 玩家速度改变广播

类型： 0x81

长度： 19

数据： 8 字节玩家 ID、2 字节当前地图位置、4 字节当前 X 位置、4 字节当前 Y
位置和 1 字节新速度方向。

## 0x82 - 玩家聊天广播

类型： 0x82

长度：不定

数据：解析过的玩家名称和该玩家的聊天内容，UTF-8 编码。