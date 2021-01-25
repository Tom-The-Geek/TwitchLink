[ ![Download](https://api.bintray.com/packages/tomthegeek/mods/TwitchLink/images/download.svg) ](https://bintray.com/tomthegeek/mods/TwitchLink/_latestVersion)
# TwitchLink

## For integrations:
1. Add the mod as a dependency in your `build.gradle` like so:
```groovy
// Add this to the repositories
repositories {
  maven {
    name = "TomTheGeek-bintray"
    url = "https://dl.bintray.com/tomthegeek/mods"
  }
}

// Add this to the dependencies
dependencies {
  // [... Generic Fabric mod dependencies (MC, loader, fapi)]
  
  modImplementation "me.geek.tom:twitchlink:<latest version>" // Check the version with the icon above
}
```

2. Read the Javadoc of [TwitchLinkIntegration](https://github.com/Geek202/TwitchLink/blob/master/src/main/java/me/geek/tom/twitchlink/api/TwitchLinkIntegration.java) to understand how to subscribe to events.
(see the [Fabric wiki](https://fabricmc.net/wiki/documentation:entrypoint) for more details on entrypoints)
