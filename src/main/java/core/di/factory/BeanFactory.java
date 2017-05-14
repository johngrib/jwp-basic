package core.di.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.*;

import core.annotation.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;

public class BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);

    private Set<Class<?>> preInstanticateBeans;

    private Map<Class<?>, Object> beans = Maps.newHashMap();

    public BeanFactory(Set<Class<?>> preInstanticateBeans) {
        this.preInstanticateBeans = preInstanticateBeans;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }

    public BeanFactory initialize() {
        for (Class<?> aClass : preInstanticateBeans) {
            if (!beans.containsKey(aClass)) {
                beans.put(aClass, instantiateClass(aClass));
            }
        }
        return this;
    }

    private Object instantiateClass(Class<?> aClass) {
        final Constructor cons = BeanFactoryUtils.getInjectedConstructor(aClass);
        if (cons == null) {
            return BeanUtils.instantiate(aClass);
        }
        return getInstance(cons, preInstanticateBeans);
    }

    private Object getInstance(Constructor cons, Set<Class<?>> preInstanticateBeans) {

        final Class[] injectTypes = cons.getParameterTypes();

        final Object[] arguments = Arrays.stream(injectTypes).map(aClass -> {
            final Class concreteClass = BeanFactoryUtils.findConcreteClass(aClass, preInstanticateBeans);

            if (!preInstanticateBeans.contains(concreteClass)) {
                throw new IllegalStateException("not bean");
            }

            if (beans.containsKey(concreteClass)) {
                return beans.get(concreteClass);
            }

            return instantiateClass(concreteClass);
        }).toArray();

        return BeanUtils.instantiateClass(cons, arguments);    // recursion
    }

    public Map<Class<?>, Object> getControllers() {
        final Map<Class<?>, Object> controllers = new HashMap<>();
        for (final Class aClass : preInstanticateBeans) {
            final Annotation anno = aClass.getAnnotation(Controller.class);
            if(anno != null) {
                controllers.put(aClass, beans.get(aClass));
            }
        }
        return controllers;
    }
}
