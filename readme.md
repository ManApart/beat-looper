Beat Looper



## Running

```
runJvm
runJs
jsBrowserDistribution
```

## Pushing to web

```
aws s3 sync build/distributions/ s3://austinkucera.com/games/beat-looper/


./gradlew jsBrowserDistribution && aws s3 sync build/distributions/ s3://austinkucera.com/games/beat-looper/
```


### Other
If the app complains that Swagger Config needs to be open, enable annotation processing in Intellij

View the swagger at `http://localhost:8080/swagger-ui.html`

### TODO

Proper set of pitches


## Sources

https://samplefocus.com/samples/piano-c