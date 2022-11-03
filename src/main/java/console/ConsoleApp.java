package console;

public class ConsoleApp {
    public static void main(String[] args) {
        IMessageRenderer mr = MessageServiceFactory.INSTANCE.getMr();
        IMessageProvider mp = MessageServiceFactory.INSTANCE.getMp();
        mr.setMessageProvider(mp);
        mr.render();
    }
}
