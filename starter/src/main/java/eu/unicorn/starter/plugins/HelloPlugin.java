package eu.unicorn.starter.plugins;

public class HelloPlugin implements GreetingsPlugin{

	public String getGreeting(String name) {
		if (name != null) {
			return "Hello " + name;
		}
		return "";
	}

}
