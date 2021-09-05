package com.cm.core.request_object;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String email;
    private String password;
}
