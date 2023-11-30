package io.yangood.goodale.loader;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import org.jetbrains.annotations.NotNull;

/**
 * @ClassName: GoodaleLoader
 * @Description: 装载机
 * @Author: Yang WeiJie
 * @Date: 2023/11/29
 * @Version: 1.0
 */
public class GoodaleLoader implements PluginLoader {

    // 为了插件价值 创建 预期/动态环境  目前仅适用与为插件创建目标类路径   例如提供外部库
    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder) {
        // 通过路径 添加外部依赖
        //classpathBuilder.addLibrary(new JarLibrary(Path.of("dependency.jar")));

        // maven解析器 添加外部远程依赖
        //MavenLibraryResolver resolver = new MavenLibraryResolver();
        //resolver.addDependency(new Dependency(new DefaultArtifact("com.example:example:version"), null));
        //resolver.addRepository(new RemoteRepository.Builder("paper", "default", "https://repo.papermc.io/repository/maven-public/").build());
        //
        //classpathBuilder.addLibrary(resolver);
    }
}
