package me.kzv.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        return new String[]{
//                "me.kzv.config.autoconfig.DispatcherServletConfig",
//                "me.kzv.config.autoconfig.TomcatWebServerConfig"
//        };

        // 방법 1
//        List<String> autoConfigs = new ArrayList<>();
//        for (String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
//            autoConfigs.add(candidate);
//        }
//        return autoConfigs.toArray(new String[0]); // 사이즈가 작은 배열을 넣으면 알아서 크기에 맞춰서 새로운 배열에 복사해서 준다.

        // 방법 2
//        List<String> autoConfigs = new ArrayList<>();
//        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);
//        return Arrays.copy(autoConfigs.toArray(), autoConfigs.size(), String[].class);

        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader); // META-INF/spring/~
        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);
    }
}
