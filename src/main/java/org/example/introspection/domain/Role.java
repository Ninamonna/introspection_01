package org.example.introspection.domain;

import org.springframework.security.core.GrantedAuthority;
//отражает разрешения выданные доверителю в масштабе всего приложения

public enum Role implements GrantedAuthority {
    USER,ADMIN,GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
