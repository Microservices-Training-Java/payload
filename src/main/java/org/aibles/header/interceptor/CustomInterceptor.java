package org.aibles.header.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.aibles.header.constant.PayloadConstant;
import org.aibles.header.dto.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class CustomInterceptor implements HandlerInterceptor {

  @Autowired
  private Payload payload;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    try {
      log.info("Start catch header: ------->");
      payload.setUserRoles(
          Collections.singleton(request.getHeader(PayloadConstant.USER_ROLES.getValue())));
      payload.setUsername(request.getHeader(PayloadConstant.USERNAME.getValue()));
      payload.setUserId(request.getHeader(PayloadConstant.USER_ID.getValue()));
      payload.setLanguage(request.getHeader(PayloadConstant.LANGUAGE.getValue()));
      log.info("Payload: {}", payload.toString());
    } catch (Exception e) {
      log.error("Error when catch header: {}", e.getMessage());
      e.printStackTrace();
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception exception) throws Exception {
  }
}
