# tycode

#### 介绍
Write once, Generate more，So stupid.

一款简单而强大、灵活的代码生成器Cloud工具。

与传统代码生成器有着很大区别，它可以生成任何编程语言的任意代码。如：生成一个模块完整的CRUD代码，从前端到后端，大幅提高开发人员的生产力。

使用起来也非常简单！点点按钮，几天的工作量，在弹指一挥间就完成了。

技术选型：SpringBoot + JDBC + Vue + Vuetify。

目前支持的数据库有：MySQL、Oracle、SQLServer等。

系统支持热加载，即修改了数据源和模板后，不需要重启哦！

该工具在集团内使用多年，为了自身不断优化、发扬开源贡献精神，于2022年完全开源。

支持i18N国际化。

#### 产品界面

![](https://gitee.com/tommycloud/TyStudy/raw/master/src/main/resources/assets/ty-code/code-index-zh.png)

![](https://gitee.com/tommycloud/TyStudy/raw/master/src/main/resources/assets/ty-code/code-result-zh.png)

#### 使用前置条件

> 划重点：代码模板是基于Thyemleaf编写的，所以你**只要会一点点Thymeleaf基础就可以啦**！So Easy！！！

项目中内置了一套标准模板，此模板完全适用于 **[TyFast](https://gitee.com/tommycloud/TyFast)** 平台。

因每个团队的项目架构搭建方式不尽相同，大家可以根据标准模板改成符合自己编码规范的样子。

#### 如何编写代码模板

所有的模板和数据源都存放在项目的 **src / main / resources / repository** 目录中，如下图所示：

![](https://gitee.com/tommycloud/TyStudy/raw/master/src/main/resources/assets/ty-code/repos.png)

repository 各目录和文件的说明：

- jdbc.yml：是数据源配置文件，支持配置多个数据源；
- templateGroups：是存放模板组的目录，一个模板组即一个目录；
- modules、readme.yml：每个模板组都必须包含这两个部分，系统会按此规则进行读取；

**模板中用到的动态数据，大家只需要关心如下两个类即可：**

1. Requirement.java
2. Mapping.java

> 无论是数据源的修改，还是模板的修改，都不需要重启TyCode，因为TyCode**支持热加载**，只要在页面点一下清除缓存即可。

![](https://gitee.com/tommycloud/TyStudy/raw/master/src/main/resources/assets/ty-code/clear.png)

#### 如何复制源码

只需要单击标题，即可复制当前显示的源码到剪切板；

所以大家 做的更多的事情就是：Ctrl + C 和 Ctrl +V

![](https://gitee.com/tommycloud/TyStudy/raw/master/src/main/resources/assets/ty-code/copy.png)

#### 最后

欢迎大家踊跃留言，共同推进TyCode成长，让码砖成为过去式，让我们的精力专注于业务开发。

### 附：中国站点
https://gitee.com/tommycloud/tycode
