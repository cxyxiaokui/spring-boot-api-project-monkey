![Spring Boot 2.0](https://img.shields.io/badge/Spring%20Boot-2.0-brightgreen.svg)
![JDK 1.8](https://img.shields.io/badge/JDK-1.8-brightgreen.svg)
[![GitHub Release](https://img.shields.io/badge/version-V2.0.0-blue)](https://github.com/lihengming/spring-boot-api-project-seed/releases)

## 平台简介

Spring Boot API Project Monkey 项目基于  [Spring Boot API Project Speed](https://github.com/lihengming/spring-boot-api-project-seed) 开源项目改造而来，将原先的 SpringBoot 版本 1.5.4.RELEASE 升级为 2.1.1.RELEASE 。
去除 MyBatis 通用Mapper插件替换为MyBatis 提供SpringBoot starter 原生方式，添加在线文档工具 Knife4j、单元测试封装、优化代码以及代码目录结构等新功能。具体请参看 *提供内置功能*列表。

### 开源该项目的初衷

Spring Boot API Project Speed 项目是自己在 GitHub 查找 SpringBoot API 开源项目时发现的，参看了下源码后觉得写的确实不错，
但是有些地方和我想要的不太一样，于是利用业务时间改造了一个自己喜欢的后台 API 项目的轮子。本着继续延续 Spring Boot API Project Speed 项目的宗旨：快速、简洁、灵活，故又起了一个新名字 Moneky ，
同时项目部分代码参考优秀开源项目 [RuoYi](https://github.com/lerry903/RuoYi)

## 提供内置功能

- 统一响应结果封装及生成工具。
- 统一异常处理。
- 简单的接口签名认证
- 常用基础方法抽象封装。
- 使用Druid Spring Boot Starter 集成Druid数据库连接池与监控。
- 使用FastJsonHttpMessageConverter，提高JSON序列化速度。
- 使用 Jasypt加密，提高项目配置安全性。
- 集成原生MyBatis Xml方式、PageHelper分页插件。
- 集成 Swagger-Bootstrap-UI 增强版 Knife4j 在线展示 Swagger 配置文档。
- 提供代码生成器根据表名生成对应的Model、Mapper、MapperXML、Service、ServiceImpl、Controller基础代码。
- 对 MockMvc 进行封装使用MockMvc 测试更简单，同时基于封装的MockMvc单元测试基础代码生成。
- 集成 kk-anti-reptile 反爬虫，防止接口盗刷。
- 集成优秀工具类库 Hutool
- 集成 Jmockdta  一款模拟JAVA类型或对象的实例化并随机初始化对象的数据的工具 
- 另有彩蛋，待你探索
 
## 快速开始
1. 克隆项目
2. 对```test```包内的代码生成器```CodeGeneratorConstant```进行配置，主要是包名和项目路径以及开发者信息。
3. 如果只是想根据上面的演示来亲自试试的话可以使用```test resources```目录下的```demo-user.sql```，否则忽略该步
3. CodeGenerator 修改表名(TABLE_NAME)、包名（PACKAGE_NAME）、Entitiy名称（ENTITY_NAME）运行```CodeGenerator.genCodeByCustomModelName()```测试方法，生成基础代码（可能需要刷新项目目录才会出来）
4. 根据业务在基础代码上进行扩展
5. 对开发环境配置文件```application-dev.properties```进行配置，启动项目。
6. 访问 localhost:8080/doc.html 查看项目API文档以及测试。

## 开发建议
- 表名，建议使用小写，多个单词使用下划线拼接
- 建议业务失败直接使用```BusinessException("message")```抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new ServiceException("该手机号已被注册")```，会直接被封装为```{"code":400,"message":"该手机号已被注册"}```返回，无需自己处理，尽情抛出
- 开发规范建议遵循阿里巴巴Java开发手册（[最新版下载](https://github.com/alibaba/p3c))
 
## 技术选型&文档
- Spring Boot（[查看Spring Boot学习&使用指南](http://www.jianshu.com/p/1a9fd8936bd8)）
- MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatis PageHelper分页插件（[查看官方中文文档](https://pagehelper.github.io/)）
- Druid Spring Boot Starter（[查看官方中文文档](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter/)）
- Fastjson（[查看官方中文文档](https://github.com/Alibaba/fastjson/wiki/%E9%A6%96%E9%A1%B5)）
- Knife4j [查看官方中文文档](https://doc.xiaominfo.com/guide/useful.html)）
- Hutool [查看Hutool使用文档](https://www.hutool.cn/docs/#/)）
- kk-anti-reptile [查看kk-anti-reptile使用文档](https://github.com/kekingcn/kk-anti-reptile)）
- Jmockdta [查看Jmockdta使用文档](https://github.com/jsonzou/jmockdata)）
- 其他略

## License
Apache-2.0 License ，感谢大家 [Star](https://github.com/zhuoqianmingyue/spring-boot-api-project-monkey/stargazers) & [Fork](https://github.com/lihengming/spring-boot-api-project-monkey/network/members) 的支持。同时欢迎大家关注我的微信公众号 *桌前明月*，后续会在公公众号上提供项目详细文档。

<div align=center><img src="https://img-blog.csdnimg.cn/20191005233835434.png"  /></div>
