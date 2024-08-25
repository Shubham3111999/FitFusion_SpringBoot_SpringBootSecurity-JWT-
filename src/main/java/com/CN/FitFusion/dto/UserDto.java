package com.CN.FitFusion.dto;

import lombok.Data;

@Data
public class UserDto {

	private String email;

	private String password;

	private int age;

	private String gender;

	private Long contactNo;

	private String userType;
}
