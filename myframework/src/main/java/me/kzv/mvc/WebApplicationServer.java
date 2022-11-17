package me.kzv.mvc;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class WebApplicationServer {

    private static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);
    private static final int PORT = 8080;
    private static final String webappDir = "webapps/";


    //  https://auth0.com/blog/spring-5-embedded-tomcat-8-gradle-tutorial/
//    public static void main(String[] args) throws Exception {
//        String appBase = ".";
//        Tomcat tomcat = new Tomcat();
//        tomcat.setBaseDir(createTempDir());
//        tomcat.setPort(PORT);
//        tomcat.getHost().setAppBase(appBase);
//        tomcat.addWebapp("", appBase);
//
//        log.info("server running...");
//
//        tomcat.start();
//        tomcat.getServer().await();
//    }
//
//    // based on AbstractEmbeddedServletContainerFactory
//    private static String createTempDir() {
//        try {
//            File tempDir = File.createTempFile("tomcat.", "." + PORT);
//            tempDir.delete();
//            tempDir.mkdir();
//            tempDir.deleteOnExit();
//            return tempDir.getAbsolutePath();
//        } catch (IOException ex) {
//            throw new RuntimeException(
//                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
//                    ex
//            );
//        }
//    }

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(PORT);

        tomcat.addWebapp("/", new File(webappDir).getAbsolutePath());

        log.info("server running...");
        log.info("configuring app with baseDir: {}", new File(webappDir).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     *  cmd + ; -> project structure
     *  Modules -> project root - paths - Output path : /Users/zerovirus/Desktop/Code/SpringStudy/myframework/out/production/myframework/webapps/WEB-INF/classes
     *
     *  gradle 프로젝트는 build.gradle 이 우선 적용이라 여기서 설정해야함
     */
}
