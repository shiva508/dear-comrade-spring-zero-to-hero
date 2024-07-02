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

## If you want to 

## Order Of execution