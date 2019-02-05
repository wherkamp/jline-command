# Jline-Command 
[![Build Status](https://ci.kingtux.me/job/Jline-Command/badge/icon)](https://ci.kingtux.me/job/Jline-Command/)

This is a simple library for having commands inside your command line

## Maven
```xml
   <repository>
      <id>kingtux-repo</id>
      <url>https://repo.kingtux.me/repository/maven-public/</url>
    </repository>
    
    <dependency>
      <groupId>me.kingtux</groupId>
      <artifactId>jline-command</artifactId>
      <!---Make sure you use Latest Version!-->
      <version>1.0/version>
      <scope>compile</scope>
    </dependency>
```
## Gradle
```
repositories {
  maven { url 'http://repo.kingtux.me/repository/maven-public/' }
}

dependencies {
   compile "me.kingtux:jline-command:1.0"
}
```
# How to use
```java
public class Main {
    public static void main(String[] args) {
        JlineCommandManager jlineCommandManager = new JlineCommandManager("jline-test", ">");
        jlineCommandManager.register(new JlineCommand() {
            @Override
            public List<String> commands() {
                return Collections.singletonList("test");
            }

            @Override
            public void execute(List<String> args) {
                if (args.size() == 1) {
                    if (args.get(0).equals("test")) {
                        System.out.println("Hello");
                    }
                }else {
                    System.out.println("Bye");
                }
            }
        }, new JlineCompleter() {
            @Override
            public List<String> complete(List<String> args) {
                return Collections.singletonList("test");
            }
        });
    }
}

```

