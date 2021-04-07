# sunny-activiti
### 数据库脚本获取看项目安装步骤第7条 数据库脚本获取看项目安装步骤第7条 数据库脚本获取看项目安装步骤第7条 (重要的事情说三遍)
#### 介绍
1.  sunny-activiti是一个SpringBoot集成activiti实现在创建、部署流程、复制流程、删除流程以及流程规则配置，实现请假流程工作流流转和业务处理，感兴趣可以Watch、Start持续关注项目最新状态
2.  **本项目只实现activit流程相关的业务与权限相关的用户角色菜单管理未做实现**
3. 增加通过页面动态控制定时任务的启动、暂停、创建、删除

#### 软件架构
| 定位  | 技术栈               |
|-----|-------------------|
| 后端  | SpringBoot v2.3.0 、Activiti工作流|
| 数据库 | MySQL             |
| 数据库 | mybatis-plus      |
| 缓存 | redis      |
| 前端| layui、thymeleaf|



#### 安装步骤

- 1.  idea或者eclipse下载项目自动从maven库下载依赖
- 2.  创建数据库、安装redis
- 3.  修改application-dev.yml数据库的配置和redis配置为自己的配置
- 4. 启动项目访问默认8080端口
- 5. **登录用户名默认密码：123** 
- 6. **用户级别关系图** 
- 7. 相关数据库脚本获取群公告， **扫码进群截图项目star** 
- ![输入图片说明](https://images.gitee.com/uploads/images/2021/0407/170334_92d70be4_806588.jpeg "qq群.jpg")

| 用户名  | 级别               |
|-----|-------------------|
| sunqi  | 最底层员工|
| zhaoliu | 最底层员工             |
| wangwu | sunqi和zhaoliu的直接上级领导      |
| lisi | wangwu的直接上级领导      |
| zhangsan| lisi的直接上级领导，最高领导(无需填写请假申请)|


#### 项目截图
- 视频演示
链接：https://pan.baidu.com/s/1d6UF19OhmQcaCqERTkzsCQ
提取码：zrbe
- 登录页
![登录页面](https://images.gitee.com/uploads/images/2020/0605/171237_7c3597aa_806588.png "屏幕截图.png")
- 首页
![输入图片说明](https://images.gitee.com/uploads/images/2020/0605/171322_ae275f60_806588.png "屏幕截图.png")
- 创建工作流
![输入图片说明](https://images.gitee.com/uploads/images/2020/0605/171353_f8230e44_806588.png "屏幕截图.png")
- 流程规则配置
![输入图片说明](https://images.gitee.com/uploads/images/2020/0605/171431_d86afc47_806588.png "屏幕截图.png")
- 审批流程查看
![输入图片说明](https://images.gitee.com/uploads/images/2020/0605/171550_26046adb_806588.png "屏幕截图.png")
- 流程审批
![输入图片说明](https://images.gitee.com/uploads/images/2020/0605/171624_accb6c12_806588.png "屏幕截图.png")
- 定时任务管理
![输入图片说明](https://images.gitee.com/uploads/images/2020/0610/111155_11aed12e_806588.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0610/111216_2c83c874_806588.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0610/111233_31135d40_806588.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0610/111248_4fbbebe6_806588.png "屏幕截图.png")

