package org.asuka.lease.common.login;

public class LoginUserHolder {
    private static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static void setLoginUser(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }

    public static LoginUser getLoginUser() {
        return threadLocal.get();
    }

    public static void clean() {
        threadLocal.remove();
    }
}
