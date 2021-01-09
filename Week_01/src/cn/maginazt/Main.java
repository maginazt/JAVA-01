package cn.maginazt;

import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> helloClass = customClassLoader.loadClass("Hello");
        Object helloObj = helloClass.newInstance();
        Method helloMethod = helloClass.getDeclaredMethod("hello");
        helloMethod.invoke(helloObj);
    }
}
