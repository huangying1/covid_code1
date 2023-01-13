package com.tyut.covid.bean;

import lombok.*;

import java.io.Serializable;

/**
 * User实体类
 * @author hls
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7177917625256579809L;
	private Integer uid;
	private String username;
	private String password;
	private String salt;
	private Integer gender;
	private String phone;
	private String email;
	private String avatar;
	private Integer isDelete;


}
