/*
 * package com.bridgelabz.fundooNotes.utility;
 * 
 * import java.io.IOException; import java.io.InputStream; import
 * java.util.Properties; import java.util.Set;
 * 
 * public class PropertyFileRead { //create Object of property class private
 * final Properties configProp = new Properties();
 * 
 * private PropertyFileRead() { //Private constructor to restrict new instances
 * InputStream in =
 * this.getClass().getClassLoader().getResourceAsStream("message.properties");
 * System.out.println("Read all properties from file"); try {
 * configProp.load(in); } catch (IOException e) { e.printStackTrace(); } }
 * 
 * //Bill Pugh Solution for singleton pattern private static class LazyHolder {
 * private static final PropertyFileRead INSTANCE = new PropertyFileRead(); }
 * 
 * 
 * public static PropertyFileRead getInstance() { return LazyHolder.INSTANCE; }
 * 
 * public String getProperty(String key) { return configProp.getProperty(key); }
 * 
 * public Set<String> getAllPropertyNames(){ return
 * configProp.stringPropertyNames(); }
 * 
 * public boolean containsKey(String key) { return configProp.containsKey(key);
 * } }
 * 
 * 
 * 
 * 
 * public static void main(String[] args) { //Get individual properties
 * System.out.println(PropertiesCache.getInstance().getProperty("firstName"));
 * System.out.println(PropertiesCache.getInstance().getProperty("lastName"));
 * 
 * //All property names
 * System.out.println(PropertiesCache.getInstance().getAllPropertyNames()); }
 * 
 * Output:
 * 
 * Read all properties from file Lokesh Gupta [lastName, technology, firstName,
 * blog]
 * 
 */
