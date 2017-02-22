# FieldsCopier
General use class which copies properties between objects from same or different types.

Copy all properties with equal names from one to another object, even with different types. 
You can pass the destination object for the copy or it's type, which requires to have an empty constructor.

See license.

## How to use 
### Maven
FieldsCopier with maven

Add the repository
```
<repository>
    <id>FieldsCopier-mvn-repo</id>
    <url>https://raw.github.com/ericbreno/FieldsCopier/mvn-repo/</url>
    <snapshots>
        <enabled>true</enabled>
        <updatePolicy>always</updatePolicy>
    </snapshots>
</repository>
```

Core of FieldsCopier
```
<dependency>
    <groupId>org.fields.utils</groupId>
    <artifactId>FieldsCopier</artifactId>
    <version>1.0.0</version>
</dependency>
```

Attach sources
```
<dependency>
    <groupId>org.fields.utils</groupId>
    <artifactId>FieldsCopier</artifactId>
    <version>1.0.0</version>
    <classifier>sources</classifier>
</dependency>
```

## Authors
* **Eric Breno**

See also the list of [contributors](https://github.com/ericbreno/FieldsCopier/contributors) who participated in this project.

## License

This project is licensed under the Apache License - see the [LICENSE.md](LICENSE.md) file for details
