
# use jsch for ssh tunnel

> just for learning


## build

```shell
mvn clean package
```

## Running

```shell
java -jar target/sshgtunnelapp-0.0.1-SNAPSHOT.jar
```


## add local ssh transport 


```shell

POST http://localhost:8080/conf
Content-Type: application/json

{
  "host": "<sshhost>",
  "user": "<ssh user>",
  "password": "<ssh password>",
  "port": <ssh port>,
  "tunnelLocalPort": <localport>,
  "tunnelRemoteHost": "<remote service host>",
  "tunnelRemotePort": <remote service port>
}
```


## view conf list


```shell
### GET request with parameter
GET http://localhost:8080/conflist
Accept: application/json
```