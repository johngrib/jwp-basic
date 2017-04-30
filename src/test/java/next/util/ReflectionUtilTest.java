package next.util;

import core.annotation.Controller;
import next.controller.HomeController;
import next.controller.qna.*;
import next.controller.user.LoginController;
import next.controller.user.UserController;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        assertTrue(classes.contains(AnswerController.class));
        assertTrue(classes.contains(QuestionController.class));

        assertTrue(classes.contains(UserController.class));
    }

    @Test
    public void 어노테이션이_붙은_클래스를_필터링한다() {

        final List<Class<?>> classes = ReflectionUtil.getClassesInPackage("next.controller");
        final List<Class<?>> controllers = ReflectionUtil.getAnnotatedClasses(classes, Controller.class);

        assertTrue(controllers.contains(HomeController.class));
        assertTrue(controllers.contains(AnswerController.class));
        assertTrue(controllers.contains(LoginController.class));
    }
}