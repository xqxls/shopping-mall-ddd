package com.xqxls.domain;

import lombok.*;

import java.util.List;

/**
 * 登录用户信息
 * Created by xqxls on 2020/6/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String icon;
    private List<String> roles;
    private List<String> permissionList;
}
