package xin.saul.greet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import xin.saul.greet.testclass.ClassWithDependency;
import xin.saul.greet.testclass.DependencyClass;
import xin.saul.greet.testclass.Snack;
import xin.saul.greet.testclass.Status;

import static org.junit.jupiter.api.Assertions.*;
public class IoCContextDependencyTest {
    @Test
    void test_should_create_instance_with_dependency_with_had_been_register() throws InstantiationException, IllegalAccessException {

        IoCContextImpl iocContext = new IoCContextImpl();
        iocContext.registerBean(DependencyClass.class);
        iocContext.registerBean(ClassWithDependency.class);
        ClassWithDependency bean = iocContext.getBean(ClassWithDependency.class);

        assertEquals(ClassWithDependency.class, bean.getClass());
        assertEquals(DependencyClass.class, bean.getDependencyClass().getClass());

    }

    @Test
    void test_should_create_class_with_dependency_with_had_not_been_register() throws InstantiationException, IllegalAccessException {

        IoCContextImpl iocContext = new IoCContextImpl();
        iocContext.registerBean(ClassWithDependency.class);
        Executable executable= () -> {
            ClassWithDependency bean = iocContext.getBean(ClassWithDependency.class);
        };

        assertThrows(IllegalStateException.class, executable);
    }

    @Test
    void test_should_throw_IllegalStateException_when_a_dependency_cause_cyclic_dependence() throws InstantiationException, IllegalAccessException {

        IoCContextImpl iocContext = new IoCContextImpl();
        iocContext.registerBean(Status.class);
        iocContext.registerBean(Snack.class);

        Executable executable = () -> {
            Snack bean = iocContext.getBean(Snack.class);
        };

        assertThrows(IllegalStateException.class, executable);
    }

}