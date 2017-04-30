package next.util;

import core.annotation.Controller;
import core.annotation.RequestMapping;
import core.mvc.ControllerData;
import core.mvc.ControllerData;
import next.controller.HomeController;
import next.controller.qna.*;
import next.controller.user.CreateUserController;
import next.controller.user.ListUserController;
import next.controller.user.LogoutController;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by johngrib on 2017. 4. 27..
 */
public class ReflectionUtilTest {

    @Before
    public void prepare() {
    }

    @Test
    public void 패키지_경로상의_모든_클래스를_가져와야_한다() {

        final List<Class<?>> classes = ReflectionUtil.getClassesInPackage("next.controller");

        assertTrue(classes.contains(HomeController.class));
        assertTrue(classes.contains(AddAnswerController.class));
        assertTrue(classes.contains(ApiDeleteQuestionController.class));

        assertTrue(classes.contains(CreateUserController.class));
        assertTrue(classes.contains(ListUserController.class));
    }

    @Test
    public void 어노테이션이_붙은_클래스를_필터링한다() {

        final List<Class<?>> classes = ReflectionUtil.getClassesInPackage("next.controller");
        final List<Class<?>> controllers = ReflectionUtil.getAnnotatedClasses(classes, Controller.class);

        assertTrue(controllers.contains(HomeController.class));
        assertTrue(controllers.contains(DeleteAnswerController.class));
        assertTrue(controllers.contains(LogoutController.class));
    }
}