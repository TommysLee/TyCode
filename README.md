<p align="center">
    <img alt="TyFast LOGO" width="130" src="https://raw.githubusercontent.com/TommysLee/TyFast/refs/heads/master/tyfast-web/src/main/resources/public/images/logo/ty-greeen.png">    
</p>
<h1 align="center" style="font-weight: bold;">TyCode 代码生成器</h1>
<h4 align="center">Write once, Generate more. —— 一次模板，无限生成</h4>
<p align="center">
    <img alt="Build Passing" src="https://img.shields.io/badge/build-passing-brightgreen.svg">
    <img alt="TyFast-3.x" src="https://img.shields.io/badge/TyCode-v2.x-brightgreen.svg?logo=github">
    <img alt="Apache License" src="https://img.shields.io/badge/license-apache-brightgreen.svg">
</p>
一款简单而强大、灵活通用的代码生成工具。无论是 Java、Vue、SQL，还是 Python、Go，只要你会写 Thymeleaf 模板，TyCode 就能帮你把重复的工作量降到零。

### 目前支持的数据库

- MySQL
- Oracle
- SQLServer
- （可扩展其他数据库）

### 系统界面

![](https://github.com/TommysLee/images-bed/blob/main/ty-code/code-index-zh.png?raw=true)

![](https://github.com/TommysLee/images-bed/blob/main/ty-code/code-result-zh-1.png?raw=true)

![](https://github.com/TommysLee/images-bed/blob/main/ty-code/code-result-zh-2.png?raw=true)

### 快速上手

#### 前置条件

> **你只需要会一点点 Thymeleaf 基础**，就能写 TyCode 模板。

项目内置了一套标准模板，完全适用于 **[TyFast](https://github.com/TommysLee/TyFast)** 平台。如果你是其他项目架构，也可以参考内置模板，改成符合自己规范的样子。

#### 模板与数据源

所有模板和数据源都存放在：`src/main/resources/repository/`，如下图所示：

![](https://github.com/TommysLee/images-bed/blob/main/ty-code/repos.png?raw=true)

- `jdbc.yml`：数据源配置文件，支持配置多个数据源；
- `templateGroups`：存放模板组的目录，一个模板组即一个目录；
- `modules`、`readme.yml`：每个模板组都必须包含这两个部分；

**模板中可用的动态数据，只需要关心两个类：**

- `Requirement.java`
- `Mapping.java`

#### 生成代码与复制

在 TyCode 界面中，单击标题即可复制当前显示的源码到剪贴板。你只需要 **Ctrl+C / Ctrl+V**，把代码粘贴到你的项目中即可。

![](https://github.com/TommysLee/images-bed/blob/main/ty-code/copy.png?raw=true)

### 支持热加载

- 修改数据源配置？不需要重启。
- 修改模板？也不需要重启。
- 页面上点一下“清除缓存”，立刻生效。

![](https://github.com/TommysLee/images-bed/blob/main/ty-code/clear.png?raw=true)

### 支持 Native 原生编译

TyCode 已支持 GraalVM Native-Image，可以编译成独立的二进制可执行文件，无需 JVM，毫秒级启动。

```shell
# 正常打包
mvn clean package

# 编译为原生应用
mvn clean -Pnative native:compile
```

### 适用人群

- 受够重复 CRUD 的开发者
- 想在团队内统一代码规范的技术负责人
- 希望用 Vue 但不想被前端工程化绊住的团队
- 任何想“写一次模板，无限生成代码”的务实开发者

> **让团队定义好自己的代码模板，然后一键生成。** 生成的代码完全符合你们的规范，零偏差、零歧义、零额外成本。

### 最后

TyCode 在集团内部已使用多年，于 2022 年完全开源。我们希望它能帮助更多团队：

- **让码砖成为过去式**
- **让精力专注于业务开发**
- **让技术回归“解决真实问题”的本质**

欢迎大家踊跃留言，共同推进 TyCode 成长！
