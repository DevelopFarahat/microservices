package com.user.projection;

import org.springframework.beans.factory.annotation.Value;

public interface UserDetailsProjection {
    @Value("#{target.ID}")
    Long getId();
    @Value("#{target.USERNAME}")
    String getUsername();
    @Value("#{target.EMAIL}")
    String getEmail();
}
