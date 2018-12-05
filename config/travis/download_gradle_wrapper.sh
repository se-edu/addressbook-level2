#!/bin/sh
# Downloads Gradle wrapper into current directory.

baseurl='https://se-edu.github.io/gradle-wrapper'
mkdir -p gradle/wrapper &&
wget -O gradle/wrapper/gradle-wrapper.jar "$baseurl/gradle/wrapper/gradle-wrapper.jar" &&
wget -O gradle/wrapper/gradle-wrapper.properties "$baseurl/gradle/wrapper/gradle-wrapper.properties" &&
wget -O gradlew "$baseurl/gradlew" &&
chmod +x gradlew &&
wget -O gradlew.bat "$baseurl/gradlew.bat"
