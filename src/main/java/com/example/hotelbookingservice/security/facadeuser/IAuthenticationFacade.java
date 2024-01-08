package com.example.hotelbookingservice.security.facadeuser;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

  Authentication getAuthentication();

}
