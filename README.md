# [ChatItems]
Show your items in chat!

JavaDocs can be found [here](https://mackan.xyz/ChatItems/).

# Usage with your plugin

### Requirements

Java 8 and Bukkit / Spigot.

Supports versions after 1.8.

[ChatItems] requires Maven or a Maven compatible build System and is available on [JitPack.io](https://jitpack.io/#Sven65/Item-Names)

### pom.xml

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.Sven65</groupId>
    <artifactId>ChatItems</artifactId>
    <version>1.0.8</version>
    <scope>provided</scope>
</dependency>
```

# Code Examples

To get the name of an ItemStack, simply call `ItemNames.getItemName(ItemStack);`