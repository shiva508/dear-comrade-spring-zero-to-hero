# Profiles
## When spring.profiles.active=local
### It will take only local properties
#### properties in file
```
app:
  profile: local
  level: L1-local
  emitPercent: 22-local
  noArgs: No-local
```
#### output
```
{
"profile": "local",
"level": "L1-local",
"emitPercent": "22-local",
"noArgs": "No-local",
"helper": null
}
```

## spring.profiles.active=dev
### It will take only dev properties
#### properties in file
```
app:
  profile: dev
  level: L1-dev
  emitPercent: 22-dev
```
#### output
```
{
"profile": "dev",
"level": "L1-dev",
"emitPercent": "22-dev",
"noArgs": null,
"helper": null
}
```

## spring.profiles.active=local,dev
### Taking dev properties has priority
### dev properties overriding local properties
#### output
```
{
"profile": "dev",
"level": "L1-dev",
"emitPercent": "22-dev",
"noArgs": "No-local",
"helper": null
}
```
## spring.profiles.active=dev,local
### Taking local properties has priority
### local properties overriding dev properties
#### output
```
{
"profile": "local",
"level": "L1-local",
"emitPercent": "22-local",
"noArgs": "No-local",
"helper": null
}
```

## Profile group

### Using profile group we can activate other properties files on selecting specific profile

```
spring:
  profiles:
    group:
      dev:
        - "dear"
        - "helper-dev"
      local:
        - "helper-local"
```

## If you want to activate any additional profile on selection specific profile we can use 
```
spring:
  config:
    activate:
      on-profile:
        - "local"
app:
  profile: dev
  level: L1-dev
  emitPercent: 22-dev
  author: shiva-local
```

## Order Of execution

