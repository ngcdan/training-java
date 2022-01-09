## Training java project 

### Build And Run

To check out code:
```sh
$ git clone https://github.com/ngcdan/training-java.git
```

To build:

```sh
$ cd training-java
$ gradle clean build
```

To build the release:

```sh
$ cd app/server
$ gradle release
```

To Run:

```sh
$ ./server.sh start
```

You can access rest api via http://localhost:8080/rest/v1.0.0/demo/hello