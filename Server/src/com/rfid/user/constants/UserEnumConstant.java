package com.rfid.user.constants;

public class UserEnumConstant {

	public enum RoleTpyeName{
		Admin{
			String getRoleName(){
				return "Admin";
			}
		},
		Monitor{
			String getRoleName(){
				return "Monitor";
			}
		},
		User{
			String getRoleName(){
				return "User";
			}
		};
		abstract String getRoleName();
	}
}
