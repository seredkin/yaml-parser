# Streaming Yaml Parser

In addition to the ordinary parsing, this project demonstrates:

1. Compatibility of the latest Kotlin language with Java 8 language level and runtime.

2. Non-recursive streaming is memory-efficient and not limited with the JVM callstack size 
contrary to Batch/Recursive processors.

3. Extendible to other programming models (Batch/Reactive) and formats (Json, Tree etc...)

## System Requirements:

* JDK 8

## Build 

`$ ./gradlew shadowJar`

## Run/Test

Check out comments in shell scripts for details, here's a simple case:

```$bash
java -jar build/libs/ionos-yaml-parser-0.0.1-all.jar \
"samples/sample-00.yaml" ".version"
```

whereas first parameter is the file and second is the YAML path
