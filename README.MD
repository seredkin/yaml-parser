# Streaming Yaml Parser

In addition to the ordinary parsing, this project demonstrates:

1. Compatibility of the latest Kotlin language with Java 8 language level and runtime.

2. Non-recursive streaming is memory-efficient and not limited with the JVM callstack size 
contrary to Batch/Recursive processors.

3. Extendible to other programming models (Batch/Reactive) and formats (Json, Tree, HOCON etc...)

## System Requirements:

* JDK 8

## Build / Test

`$ ./gradlew shadowJar`

## Run

Check out comments in shell scripts for details, here's a simple case:

```$bash
java -jar build/libs/ionos-yaml-parser-0.0.1-all.jar \
"samples/sample-00.yaml" \
".version" ".services.db.volumes.0" ".services.wordpress.environment.WORDPRESS_DB_HOST"
```

whereas first parameter is the file and latter ones are YAML paths
