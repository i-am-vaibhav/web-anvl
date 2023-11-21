package com.anvl.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserVo {
    private BigDecimal id;

    private String username;

    private String password;

    private String email;

    private String designation;

    private List<String> roles;
}
