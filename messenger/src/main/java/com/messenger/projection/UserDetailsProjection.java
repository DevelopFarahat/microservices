package com.messenger.projection;

import org.springframework.beans.factory.annotation.Value;

public interface UserDetailsProjection {
    @Value("#{target.ID}")
    public Long getId();

    @Value("#{target.USERNAME}")
    public String getUsername();

    @Value("#{target.EMAIL}")
    public String getEmail();
}
