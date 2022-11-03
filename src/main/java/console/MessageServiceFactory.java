package console;

import java.util.Properties;

public enum MessageServiceFactory {
    INSTANCE;
    private IMessageRenderer mr;
    private IMessageProvider mp;

    MessageServiceFactory() {
        Properties props = new Properties();
        try {
            props.load(this.getClass().getResourceAsStream("/msf.properties"));
            String renderer = props.getProperty("renderer.class");
            String provider = props.getProperty("provider.class");
            mr = (IMessageRenderer) Class.forName(renderer).getDeclaredConstructor().newInstance();
            mp = (IMessageProvider) Class.forName(provider).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public IMessageRenderer getMr() {
        return mr;
    }

    public IMessageProvider getMp() {
        return mp;
    }
}
