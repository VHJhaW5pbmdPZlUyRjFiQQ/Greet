package xin.saul.greet.testclass;

import xin.saul.greet.IocContextImpl;

public class ClassWithContructRegisterBean {
    public ClassWithContructRegisterBean() throws InstantiationException, IllegalAccessException {
        IocContextImpl iocContext = new IocContextImpl();
        iocContext.registerBean(MyBean.class);
    }
}