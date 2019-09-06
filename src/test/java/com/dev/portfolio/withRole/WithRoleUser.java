package com.dev.portfolio.withRole;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "user", roles = "USER")
public @interface WithRoleUser {
}
