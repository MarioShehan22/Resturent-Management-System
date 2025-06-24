package com.mycompany.mavenproject1.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    private String userName;
    private String telephone;
    private String dob;
    private String password;
    private String email;
}
