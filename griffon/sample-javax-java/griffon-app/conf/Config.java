import griffon.util.AbstractMapResourceBundle;

import javax.annotation.Nonnull;
import java.util.Map;

import static java.util.Arrays.asList;
import static griffon.util.CollectionUtils.map;

public class Config extends AbstractMapResourceBundle {
    @Override
    protected void initialize(@Nonnull Map<String, Object> entries) {
        map(entries)
            .e("application", map()
                .e("title", "sample-javax-java")
                .e("startupGroups", asList("sampleJavaxJava"))
                .e("autoShutdown", true)
            )
            .e("mvcGroups", map()
                .e("sampleJavaxJava", map()
                    .e("model", "org.example.SampleJavaxJavaModel")
                    .e("view", "org.example.SampleJavaxJavaView")
                    .e("controller", "org.example.SampleJavaxJavaController")
                )
            );
    }
}