package lambda;


import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by laiyy
 * Date 2017/7/11.
 */
public class BeetlTest1 {

    private Template template;

    @Before
    public void before() throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("classpath");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        template = groupTemplate.getTemplate("test1.txt");
    }

    @Test
    public void testForEach() throws IOException {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        template.binding("list", numbers);
        System.out.println(template.render());
    }

    @Test
    public void testBeetl1() throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("classpath");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        Template template = groupTemplate.getTemplate("test1.txt");
        System.out.println(template.render());
    }

    @Test
    public void testClasspathResourceLoader() throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("classpath");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        Template template = groupTemplate.getTemplate("hello.txt");
        template.binding("name", "哇哈哈");
        System.out.println(template.render());
    }

    @Test
    public void testFileResourceLoader() throws IOException {
        String path = System.getProperty("user.dir") + File.separator + "WEB-INF/template";
        FileResourceLoader resourceLoader = new FileResourceLoader(path, "UTF-8");
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        Template template = groupTemplate.getTemplate("hello.txt");
        template.binding("name", "张三");
        System.out.println(template.render());
    }

    // 1、GroupTemplate
    @Test
    public void testGroupTemplate() throws IOException {
        // StringTemplateResourceLoader ---> 字符串模板
        // FileResourceLoader ---> 文件模板
        // ClasspathResourceLoader ---> classpath 文件模板
        // WebAppResourceLoader ---> web 集成模板
        // MapResourceLoader ---> 动态模板
        // CompositeResourceLoader ---> 混合加载模板
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration configuration = Configuration.defaultConfiguration();
        GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, configuration);
        Template template = groupTemplate.getTemplate("hello, ${name}");
        template.binding("name", "laiyy");
        String render = template.render();
        System.out.println(render);
    }

}
