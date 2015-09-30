9/30/2015 - Moved to GitHub (https://github.com/slowtrailrunner/cdi-properties)

# cdi-properties #

Context and Dependency Injection (CDI) does not supply a built in utility for injecting property values from properties files into fields, methods or parameters.  This can be very useful and this library provides the and annotation and supporting classes to make this happen.

## Configuration ##
A system property named `config.base.name` is expected to be present, unless you create your own implementation of Configuration and pick a different System Property name.  The value of this property should be the base-name of the property file.  This library makes us of the ResourceBundle API, so these properties will benefit from localization, although, this is not necessarily a localization utility. For example, if you are putting properties in a file named: `myProperties.properties`, then the `config.base.name` should be `myProperties`.  Reference your container on how to specifically provide System properties. The properties file need to be in the class path of your web applications.

Applications using this library may also define their own Producers and Configurations.  See `AbstractConfiguration` and `AbstractConfigurationProducer`. By implementing your own `Configuration` and `ConfigurationProducer`, you could then directly specify your applications config base name, if necessary.  If may not be a bad thing to have a global properties file. Application specific properties could be qualified with a naming convention like: `myapp.home.page.title` and `yourapp.home.page.title`, for example.

You also have the option of loading properties located outside the application.  A system property names `config.dir.path` may be set (you could also override the default behavior in your own implementation).  If the property is set, the `AbstractConfiguration` will attempt to use that path to find the specified resource bundle.

## Usage ##
You have a properties file named `myProperties.properties` in your class path. And it's content looks something like this:

    home.title:My Cool Homepage
    

You would inject this property like this:

    // Uses the default producer
    @Inject
    @Property("home.title") 
    private String title;

The above syntax is only valid when no other attributes are specified. The same property could be injected using the `name` attribute:

    @Inject
    @Property(name = "home.title") 
    private String title;

You might also implement your own Configuration and ConfigurationProducer.  If you do, then specify it in the @Property annotation using the `producer` attribute.

    // Use the custom producer
    @Inject
    @Property(name = "alt.message", producer = MySuperSpecialConfigurationProducer.class)
    private String message;
    
You can also, optionally specify a default value and whether or not the property value is required.

    @Inject
    @Property(name = "home.title", defaulyValue = "Bacon", required = true) 
    private String title;

If not specified, the "default" default value is `UNKNOWN`.  If you mark a property required and it is missing when the property is injected then an exception will be logged and `UNKNOWN` will be returned as the value.

## Property injection and bean scope ##
Beans have scope.  For example , a Servlet is ApplicationScoped, so it is instantiated once. If you are injecting @Property into a Servlet, it will only be looked up once because the Servlet's scope it Application.  If you have a bean that you declare its scope to be Request, then the container will inject the property with for each request using the @RequestScope bean. More on scopes can be read here: 

http://docs.oracle.com/javaee/7/tutorial/cdi-basic008.htm

## Potential Enhancements ##

1. Support typed properties. Provide for a `type` attribute that takes a class (or maybe the type is discover-able through the InjectionPoint) and the property would be inject as the expected type instead of always a String. 
2. DONE. <strike>Provide support of properties files outside of the class path.</strike>