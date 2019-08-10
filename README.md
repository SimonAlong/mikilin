# Mikilin 介绍



该框架是对象属性的核查框架，通过引入黑白名单机制，能够通过可用和不可用两个方面对对象的属性进行拦截。所有的拦截都是基于基本的类型，而对于复杂类型（集合、map或者自定义类型）都是由基本类型组成的，框架也是支持对复杂类型进行拆解并核查内部的基本类型进而对复杂类型进行拦截。该框架具有以下特性：

### 功能性：
- 全类型：可以核查所有类型，基本类型，复杂类型，集合和Map等各种有固定属性的类型
- 多方式：对类型的核查支持多种方式：值列表、属性类型、正则表达式、系统回调、枚举类型、范围判决 和表达式语言判决
- 黑白机制：核查机制引入正反的黑白名单机制，白名单表示只要的值，黑名单表示禁用的值

### 非功能性：
- 零侵入：对代码无侵入，仅作为一个工具类存在
- 易使用：使用超级简单，一个类，两种函数，三种注解，多种匹配器
- 高性能：所有的核查均是内存直接调用
- 可扩展：针对一些不好核查的属性，可以设置外部回调，可设置自己定义的核查逻辑

详细用法请见：
[Mikilin文档](https://persimon.gitbook.io/mikilin/)