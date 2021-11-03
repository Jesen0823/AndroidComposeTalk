## DataStore

#### 简介

DataStore 是一个经过改进的新型数据存储解决方案，旨在替代 SharedPreferences。该方案依托于 Kotlin 协程和 Flow 构建而成，可提供两种不同的实现方式：用于存储输入对象的 Proto DataStore（由 协议缓冲区支持）；以及用于存储键值对的 Preferences DataStore。数据可支持采用异步、一致和事务的方式进行存储，从而解决了 SharedPreferences 的一些缺陷。