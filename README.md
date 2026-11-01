# hollow_knight_wiki

## Backend setup

The backend uses Java 21. Install a JDK 21 distribution and configure
`JAVA_HOME` to its installation directory before running Gradle:

```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.13.9-hotspot"
.\back_de_HK\demo\gradlew.bat -p .\back_de_HK\demo bootRun
```

Alternatively, run the backend with Docker from
`back_de_HK\demo`, which already uses Java 21.
