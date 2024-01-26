package com.teemor.core.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.teemor.core.annotation.role.IgnoreAuth;
import com.teemor.core.annotation.role.Permission;
import com.teemor.core.exception.UnAuthorizedException;
import com.teemor.service.CacheService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Set;

import static com.teemor.core.constant.CommonConstant.TOKEN_PERFIX;

/**
 * 默认登录权限拦截器
 *
 * @author lujing
 * @date 2018/10/8
 */
@Setter
public class AuthenticationInterceptor implements HandlerInterceptor {

    /**
     * 是否需要单设备登录
     */
    private boolean isSingleLogin = false;


    /**
     * 是否校验方法权限
     */
    private boolean isCheckPermission = false;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final JwtTokenUtil jwtTokenUtil;

    private final CacheService cacheService;

    private Set<String> byPassUrl;


    public AuthenticationInterceptor(JwtTokenUtil jwtTokenUtil, CacheService cacheService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.cacheService = cacheService;
        antPathMatcher.setCaseSensitive(false);
    }


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        return getUser(httpServletRequest, object);
    }

    /**
     * 登录校验
     *
     * @param httpServletRequest
     * @param object
     * @return
     */
    private boolean getUser(HttpServletRequest httpServletRequest, Object object) {
        String token = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isBlank(token) || token.length() < TOKEN_PERFIX.length()) {
            token = httpServletRequest.getParameter(HttpHeaders.AUTHORIZATION);
        }
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }
        if (httpServletRequest.getRequestURI().contains("swagger") || httpServletRequest.getRequestURI().contains("error")) {
            return true;
        }

        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        // 注解白名单校验
        if (this.passToken(method)) {
            return true;
        }
        // 配置url白名单
        if (this.bypassUrl(httpServletRequest.getRequestURI())) {
            return true;
        }
        // 执行认证
        if (StrUtil.isEmpty(token)) {
            throw new UnAuthorizedException("请登录");
        }
        token = StrUtil.sub(token, TOKEN_PERFIX.length(), token.length());

        Claims claims;
        try {
            claims = jwtTokenUtil.getClaimFromToken(token);
        } catch (JwtException j) {
            throw new UnAuthorizedException("用户认证失败,请重新登录");
        }

        String userId = claims.get(JwtTokenUtil.USER_ID, String.class);
        String userType = claims.get(JwtTokenUtil.USER_TYPE, String.class);


        //比对服务端token 和 校验单设备登录
        this.checkSingleDevice(userId, token);
        //校验方法权限
        this.checkMethodPermission(userId, method);

        AuthContextHolder.setUserId(userId);

        return true;
    }

    private boolean bypassUrl(String requestURI) {
        if (CollectionUtil.isEmpty(byPassUrl)) {
            return false;
        }
        for (String url : byPassUrl) {
            if (antPathMatcher.match(url, requestURI)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验方法的权限
     *
     * @param userId 用户id
     * @param method 方法
     */
    private void checkMethodPermission(String userId, Method method) {
        if (!method.isAnnotationPresent(Permission.class) || !isCheckPermission) {
            return;
        }
        Permission permission = method.getAnnotation(Permission.class);
        Set<String> permissions = cacheService.getUserPermission(userId);
        if (permissions == null) {
            throw new UnAuthorizedException("用户权限失效，请重新登录");
        }
        String onePermission = permission.value();
        if (!permissions.contains(onePermission)) {
            throw new UnAuthorizedException("对不起，您没有[" + onePermission + "]的权限");
        }
    }


    /**
     * 校验该token 是否为单设备的token
     *
     * @param token 用户token
     */
    private void checkSingleDevice(String userId, String token) {

        Set<String> tokens = cacheService.getUserTokenCache(userId);
        if (CollectionUtil.isEmpty(tokens) || !tokens.contains(token)) {
            throw new UnAuthorizedException("登录失效，请重新登录~");
        }
        if (isSingleLogin && tokens.size() >= 2) {
            throw new UnAuthorizedException("其他用户登录了您的账号~");
        }

    }

    // token校验器
    private boolean passToken(Method method) {
        if (!method.isAnnotationPresent(IgnoreAuth.class)) {
            return false;
        }
        IgnoreAuth passToken = method.getAnnotation(IgnoreAuth.class);

        return passToken != null;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }


}
