# digital-customer

## user服务

### 介绍

本服务提供组织结构、用户信息、角色信息、菜单信息、权限信息维护功能。

统一采用JWT认证授权，用户密码采用单向加密模式【SpringSecurity BCryptPasswordEncoder: 采用SHA-256 +随机盐+密钥对明文密码进行加密】

### 服务启动

    直接使用maven指令 : mvn clean package 进行打包,通过java -jar 命令启动即可

### 服务访问

    ip地址:8089/keeay-user/

### 默认超管用户
    
    用户名:   admin@qiqiguaiguai.com
    密码:    123qwe!@#QWE
