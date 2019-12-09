package com.xinshe.web.impl.conf.interceptor;//package com.xinshe.web.impl.conf.interceptor;

import com.alibaba.fastjson.JSON;
import com.xinshe.web.common.authentication.IgnoreLogin;
import com.xinshe.web.common.authentication.UserAuth;
import com.xinshe.web.common.constant.RestfulServiceErrorCodeEnum;
import com.xinshe.web.common.exceptions.BaseException;
import com.xinshe.web.common.util.AgentConstant;
import com.xinshe.web.common.util.CookieUtils;
import com.xinshe.web.common.util.cookie.LoginAndPermissionConfig;
import com.xinshe.web.common.util.jwt.JWTUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author WuShoulei on 2018/1/29
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

//    @Value("${jwt.auth}")
//    private String JWT_AUTH;


//    @Autowired
//    private LoginAndPermissionConfig config;


    public LoginInterceptor() {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 本请求忽略登录验证
         */

        IgnoreLogin methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreLogin.class);
        if(methodAnnotation!=null){
            return true;
        }

        String cookie = CookieUtils.getCookie(request, CookieUtils.PRE_LOGIN_USER);
        //用户未登录
        if (StringUtils.isBlank(cookie)){
            request.setAttribute("login", "未登录");
            response.sendRedirect("/user/toLogin");
        }else {
            request.setAttribute("login", cookie);
        }

//        String jwtAuth = request.getHeader(JWT_AUTH);
//        if(StringUtils.isBlank(jwtAuth)){
//            jwtAuth = CookieUtils.getCookie(request, config.getCookiename());
//        }
//        //JWT校验
//        boolean jwtResult = JWTUtil.validateJWT(jwtAuth);
//
//        if (!jwtResult) {
//            //用户未登录
//            throw new BaseException(RestfulServiceErrorCodeEnum.NOT_LOGGED);
//        }

//        Claims claims = JWTUtil.parseJWT(jwtAuth);

        //检查系统版本号
//        String sysVersion = claims.get(AgentConstant.JWT_VERSION, String.class);
//        if (null == sysVersion || !sysVersion.equals(AgentConstant.SYS_VERSION)) {
//            //用户未登录
//            throw new BaseException(RestfulServiceErrorCodeEnum.NOT_LOGGED);
//        }

        //从JWT中获取用户权限
//        String userAuthStr = claims.get(AgentConstant.JWT_CLAIM_KEY, String.class);
//        UserAuth userAuth = JSON.parseObject(userAuthStr, UserAuth.class);

//        String redisJwt = redis.getValue(AgentConstant.REDIS_JWT_PREFIX + userAuth.getId().toString());
//        //Redis中未取到jwt信息，强制下线，提示重新登录
//        if (StringUtils.isEmpty(redisJwt)) {
//            throw new BaseException(RestfulServiceErrorCodeEnum.RE_LOGGED_IN);
//        }

//        request.setAttribute(AgentConstant.USER_AUTH, userAuth);

        //未分配角色
//        if (null == userAuth.getRoleKey()) {
//            throw new BaseException(RestfulServiceErrorCodeEnum.UNASSIGNED_ROLE);
//        }

        //接口权限检查
//        Permission permissionAnnotation = ((HandlerMethod) handler).getMethodAnnotation(Permission.class);
//        if(null == permissionAnnotation){
//            //没有开放此功能，请联系管理员
//            throw new BaseException(RestfulServiceErrorCodeEnum.UNAUTHORIZED);
//        } else {
//            if(!permissionAnnotation.ignore()){
//
//                //当前用户的权限数组
//                String[] authArray = userAuth.getInterfaceAuth();
//
//                if (null == authArray || 0 == authArray.length) {
//                    //角色未分配接口权限
//                    throw new BaseException(RestfulServiceErrorCodeEnum.UNASSIGNED_ROLE_INTERFACE_AUTH);
//                }
//
//                String tip = "";
//                //接口未指定权限码
//                if(StringTool.isEmpty(permissionAnnotation.codes())){
//                    throw new BaseException(RestfulServiceErrorCodeEnum.LACK_OF_AUTHORITY);
//                }
//                if (Arrays.asList(authArray).contains(String.valueOf(permissionAnnotation.codes()))) {
//                    return true;
//                }
//
//                //获取每个权限代表的权限描述
//                String permissionName = permissionAnnotation.description();
//                tip += permissionName;
//
//                if(StringUtils.isNotBlank(tip)){
//                    tip = "你缺少权限：" + tip;
//                }
//                else {
//                    tip = "你没有权限使用此功能";
//                }
//                //权限不足
//                throw new BaseException(RestfulServiceErrorCodeEnum.LACK_OF_AUTHORITY, tip);
//            }
//
//        }

        return true;
    }

}