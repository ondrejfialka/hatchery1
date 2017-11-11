package eu.unicorn.starter.plugins;

public interface GreetingsPlugin {
	
	/**
	 * Returns customized greeting for a user
	 * 
	 * @param name The name of the user
	 * @return Sentence greeting the user
	 */
	String getGreeting(String name);

}
