#!/usr/bin/env bash
./gradlew shadowJar --quiet

java -jar build/libs/ionos-yaml-parser-0.0.1-all.jar \
"samples/sample-00.yaml" \
".version" ".services.db.volumes.0" ".services.wordpress.environment.WORDPRESS_DB_HOST"