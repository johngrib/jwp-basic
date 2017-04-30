package core.mvc;

import next.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    private Class annotation;
    private String pkg;
    private Map<String, ControllerData> urlMap = new HashMap<>();

    public ControllerMapping(Class annotation, String pkg) {
        this.annotation = annotation;
        this.pkg = pkg;
        initMapping();
    }

    public ControllerData get(String method, String url) {
        return urlMap.get(method + url);
    }

    private void initMapping() {
        try {
            final List<Class<?>> classes = ReflectionUtil.getClassesInPackage(pkg);
            final List<Class<?>> controllers = ReflectionUtil.getAnnotatedClasses(classes, annotation);

            for (Class<?> controller : controllers) {

                final core.annotation.Controller canno = controller.getDeclaredAnnotation(core.annotation.Controller.class);
                final String prefix = canno.value();

                final Controller instance = (Controller) controller.newInstance();

                for (final Method method : controller.getDeclaredMethods()) {

                    final RequestMapping anno = method.getDeclaredAnnotation(RequestMapping.class);

                    if (anno != null && anno.value() != null) {
                        final String uri = prefix + anno.value();
                        final ControllerData controllerData = new ControllerData(instance, uri, anno.method(), method);
                        urlMap.put(anno.method() + uri, controllerData);
                    }
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        logger.info("Initialized Controller Mapping!");
    }
}
