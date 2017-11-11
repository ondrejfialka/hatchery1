package eu.unicorn.starter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import eu.unicorn.starter.plugins.GreetingsPlugin;

public class App {
	
	private static final String userName = "John";
	
    public static void main( String[] args ) {
    	Set<Class<? extends GreetingsPlugin>> allClasses = getAllPlugins();
    	 
    	 for (Class<? extends GreetingsPlugin> c: allClasses) {
    		 try {
				GreetingsPlugin greetingsPlugin = c.newInstance();
				System.out.println(greetingsPlugin.getGreeting(userName));
			} catch (InstantiationException e) {				
				e.printStackTrace();
			} catch (IllegalAccessException e) {				
				e.printStackTrace();
			}
    	 }
    }

	private static Set<Class<? extends GreetingsPlugin>> getAllPlugins() {
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
    	classLoadersList.add(ClasspathHelper.contextClassLoader());
    	classLoadersList.add(ClasspathHelper.staticClassLoader());

    	Reflections reflections = new Reflections(new ConfigurationBuilder()
    	    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
    	    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
    	    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("eu.unicorn.starter.plugins"))));

    	 Set<Class<? extends GreetingsPlugin>> allClasses = 
    	     reflections.getSubTypesOf(GreetingsPlugin.class);
		return allClasses;
	}
}
