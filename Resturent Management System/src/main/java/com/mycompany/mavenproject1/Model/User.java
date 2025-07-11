package com.mycompany.mavenproject1.Model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private String userName;
    private String email;
    private String phone;
    private Date dob;
    private String password;
}
