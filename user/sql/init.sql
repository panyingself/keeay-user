--初始化数据库 - manager_center
CREATE DATABASE IF NOT EXISTS `manager_center` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

--创建组织结构表
DROP TABLE IF EXISTS `organization_info`;
CREATE TABLE `manager_center`.`organization_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255)  NOT NULL COMMENT '组织编码',
  `parent_code` varchar(255)  NOT NULL COMMENT '父级组织编码(根节点-1)',
  `name` varchar(255)  NOT NULL COMMENT '组织名称',
  `show_order` int NOT NULL COMMENT '展示顺序(同级)',
  `active_status` tinyint(1) NULL DEFAULT NULL COMMENT '激活状态 0未激活 1已激活',
  `remark` varchar(255)  NULL DEFAULT NULL COMMENT '备注',
  `create_user` varchar(255)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(255)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '组织信息表' ROW_FORMAT = Dynamic;

--创建用户信息表
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `manager_center`.`user_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `organization_code` varchar(255)  NULL DEFAULT NULL COMMENT '组织编码',
  `user_code` varchar(255)  NOT NULL COMMENT '用户编码',
  `login_name` varchar(255)  NOT NULL COMMENT '登录名',
  `login_pwd` varchar(255)  NOT NULL COMMENT '登录密码',
  `user_name` varchar(255)  NOT NULL COMMENT '用户名',
  `phone` varchar(255)  NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255)  NULL DEFAULT NULL COMMENT '邮箱',
  `gender` tinyint(1) NULL DEFAULT 1 COMMENT '性别，0 女 1 男',
  `remark` varchar(255)  NULL DEFAULT NULL COMMENT '备注',
  `active_status` tinyint(1) NULL DEFAULT NULL COMMENT '激活状态 0未激活，1激活',
  `create_user` varchar(255)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(255)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_idx_login_name`(`login_name` ASC) USING BTREE COMMENT '登录名唯一索引',
  UNIQUE INDEX `uniq_idx_phone`(`phone` ASC) USING BTREE COMMENT '手机号唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息' ROW_FORMAT = Dynamic;

--创建角色信息表
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `manager_center`.`role_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_code` varchar(255)  NOT NULL COMMENT '角色code',
  `role_name` varchar(255)  NOT NULL COMMENT '角色名称',
  `remark` varchar(255)  NULL DEFAULT NULL COMMENT '备注',
  `create_user` varchar(255)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(255)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息' ROW_FORMAT = Dynamic;

--创建角色用户关联表
DROP TABLE IF EXISTS `user_role_info`;
CREATE TABLE `manager_center`.`user_role_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_code` varchar(255)  NOT NULL COMMENT '用户编码',
  `role_code_list` varchar(255)  NOT NULL COMMENT '用户拥有的角色',
  `remark` varchar(255)  NULL DEFAULT NULL COMMENT '备注',
  `create_user` varchar(255)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(255)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

--创建菜单信息表
DROP TABLE IF EXISTS `menu_info`;
CREATE TABLE `manager_center`.`menu_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menu_code` varchar(255)  NOT NULL COMMENT '菜单code',
  `parent_menu_code` varchar(255)  NOT NULL COMMENT '父级菜单code',
  `menu_name` varchar(255)  NOT NULL COMMENT '出单名称',
  `path` varchar(255)  NULL DEFAULT NULL COMMENT '菜单路径',
  `icon_url` varchar(255)  NULL DEFAULT NULL COMMENT '菜单icon地址',
  `sort` int NULL DEFAULT NULL COMMENT '排序字段，越小越靠前',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '菜单类型:  0 - 目录, 1 - 菜单 , 2 - 按钮',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(255)  NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` varchar(255)  NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_index_menu_code`(`menu_code` ASC) USING BTREE COMMENT '菜单code唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

--创建菜单角色关联表
DROP TABLE IF EXISTS `role_menu_info`;
CREATE TABLE `manager_center`.`role_menu_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_code` varchar(255)  NOT NULL COMMENT '角色code',
  `menu_codes` mediumtext  NULL COMMENT '角色拥有的菜单code集，全选: menu_code *  (10*)',
  `create_user` varchar(255)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(255)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

--创建权限信息表(接口)
DROP TABLE IF EXISTS `permission_info`;
CREATE TABLE `manager_center`.`permission_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `permission_code` varchar(255)  NOT NULL COMMENT '权限code',
  `permission_name` varchar(255)  NOT NULL COMMENT '权限名称',
  `uri` varchar(255)  NULL DEFAULT NULL COMMENT 'api接口路径',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(255)  NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` varchar(255)  NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

--创建权限(接口)菜单关联表
DROP TABLE IF EXISTS `menu_permission_info`;
CREATE TABLE `manager_center`.`menu_permission_info`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menu_code` varchar(255)  NOT NULL COMMENT '菜单编码',
  `permission_code_list` varchar(255)  NOT NULL COMMENT '权限编码集合',
  `remark` varchar(255)  NULL DEFAULT NULL COMMENT '备注',
  `create_user` varchar(255)  NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(255)  NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;