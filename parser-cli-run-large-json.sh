#!/usr/bin/env bash
./gradlew shadowJar --quiet

# Really big Json over here: https://github.com/zemirco/sf-city-lots-json/
# Then convert it to Yaml
wget https://github.com/zemirco/sf-city-lots-json/raw/master/citylots.json
python -c 'import sys, yaml, json; yaml.safe_dump(json.load(sys.stdin), sys.stdout, default_flow_style=False)'  citylots.json  citylots.yaml
java -jar build/libs/ionos-yaml-parser-0.0.1-all.jar \
"citylots.yaml" ".type"