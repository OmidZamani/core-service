package com.boxi.core.conf;


import com.boxi.utils.UserSecurityUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(UserSecurityUtil.getAuthName());
    }

}
