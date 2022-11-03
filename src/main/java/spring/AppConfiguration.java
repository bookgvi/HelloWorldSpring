package spring;

import console.IMessageProvider;
import console.IMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

@Configuration
public class AppConfiguration {
    private final String providerClass;
    private final String renderClass;

    public AppConfiguration() throws IOException {
        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream("/msf.properties"));
        providerClass = props.getProperty("provider.class");
        renderClass = props.getProperty("renderer.class");
    }

    @Bean
    public IMessageProvider provider() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (IMessageProvider) Class.forName(providerClass).getDeclaredConstructor().newInstance();
    }

    @Bean
    public IMessageRenderer renderer() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        IMessageRenderer renderer = (IMessageRenderer) Class.forName(renderClass).getDeclaredConstructor().newInstance();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}
