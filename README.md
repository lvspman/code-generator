
# <center>定制化代码生成平台 - DiyCode</center>
<div style="text-align:right">作者：吕小匠</div>



>一 . 项目背景
>
从**学习意义**、**解决的问题**、**实际应用**三个方面阐述该项目

**1. 学习意义**

1）教程资料少：网上虽然有现成的项目模板，但基本都是别人封装好的，只能按作者的要求生成，并且缺少项目教程

2）新颖且亮眼：别人写代码，而我们做生产代码的脚手架、工具和平台来提高研发效能。

3）能学到东西：不再是增删改查的项目，而是包含了大量的实际业务场景、系统设计和解决方案。

4）有区分度：区别于传统 Web 应用，项目涉及命令行应用、响应式编程、性能优化的入门及实战

**2. 解决的问题**

1）代码生成器本身的作用就是自动生成常见、重复性的代码片段，解决重复编码、效率低下的问题 。

2）虽然网上有很多代码生成器，但都是别人制作封装好的，很多时候还是无法满足实际开发的定制化需求 （比如要在每个类上增加特定的注解和注释）。

3）在团队开发中，要生成的代码可能是需要频繁变化和持续更新维护的。如果有一个线上平台来维护多个不同的代码生成器，支持在线编辑和共享生成器，在提高开发效率的同时、将有利于协作共建，打造更高质量的代码生成器。


**3. 实际应用**

举例一些代码生成的实际应用场景，我们将通过本项目进行解决：

1）经常做算法题目的同学，可能需要一套 Java ACM 代码输入模板，能够支持多种不同输入模式（比如单次读取和循环）。

2）经常开发新项目的同学，可能需要一套初始化项目模板代码，比如一键生成 Controller 层代码（替换其中的对象）、整合 Redis 和 MySQL 依赖等。

3）甚至可以制作项目 “换皮” 工具，支持一键给网络热门项目换皮（比如替换项目的名称、Logo 等）  

<br>


> 二. 需求分析
>
网上有一些代码生成器项目，比如前端 Ant Design Pro 中后台项目脚手架，能够让用户交互式地创建指定的项目；  
还有后端MyBatis X 插件，能够让用户通过界面来创建 CRUD 重复代码。

但这些项目都是开发者提前制作好了代码生成器，然后让你 根据他们设置好的规则 生成代码（或者拉取特定位置的代码），生成后的代码通常还要再自己二次修改，不够灵活  
<br>

> 三. 技术选型
>
**前端**

●React 开发框架 + 组件库 + 代码编辑器

●前端工程化：ESLint + Prettier + TypeScript

**后端**

●Java Spring Boot + MySQL + MyBatis Plus

●Java 命令行应用开发

●FreeMarker 模板引擎

●Vert.x 响应式编程

●Caffeine + Redis 多级缓存

●分布式任务调度系统

●多种设计模式

●多种系统设计的巧思

●对象存储  
<br>

> 四. 项目设计
>
**第一阶段 - 本地代码生成器**  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这个阶段，我们的目标是做一个本地（离线）的代码生成器，实现一个简易的 Java ACM 模板项目的定制化生成。  
<br>
<br>

**第二阶段 - 代码生成器制作工具**  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这个阶段，我们的目标是做一个本地的代码生成器制作工具，能够快速将一个项目制作为可以动态定制部分内容的代码生成器。并且以一个 Spring Boot 初始化项目模板（Maven 项目）为例，演示如何根据自己的需要动态生成 Java 后端初始化项目。
<br>
<br>
<br>

**第三阶段 - 在线代码生成器平台**  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;经过前两个阶段，我们已经能够使用本地的代码生成器制作工具来快速定制自己的代码生成器了。但如果我们想和团队其他人共同维护代码生成器、或者使用其他人的代码生成器，通过本地文件互传的方式肯定就很麻烦了。

所以本阶段我们要打造一个在线代码生成器平台，可以理解为代码生成器的应用市场。所有人都能发布、使用、甚至是在线制作自己的代码生成器！

本阶段我们的终极目标是，在平台上制作和发布项目 “换皮” 工具，帮助小伙伴们一键给网络热门项目换皮！

