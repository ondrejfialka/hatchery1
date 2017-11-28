package eu.unicorn.starter.plugins;

public class JDPlugin implements GreetingsPlugin{

    public String getGreeting(String name) {
        return "How are you " + name + "?";
    }
    
}
