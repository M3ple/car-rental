package com.car.entity;

import lombok.*;

/**
 * user info
 *
 * @author Gandalf
 * @since 2022-06-13 10:39
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

    private String name;
    private String username;
    private String password;
}
