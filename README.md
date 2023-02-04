# annapolis

Kubernetes-native distributed application lab for learning the ropes. An academy, if you will.

## Build

```bash
$ cd annapolis
$ lein uberjar
$ docker build -t annapolis:0.1.0 .
```

## Deploy

Quick start on macOS:
```bash
$ brew install colima kubernetes-cli helm

$ colima start --kubernetes

$ helm repo add bitnami https://charts.bitnami.com/bitnami
$ helm repo add traefik https://traefik.github.io/charts
$ helm repo update
$ helm install zookeeper bitnami/zookeeper
$ helm install traefik traefik/traefik

$ cd annapolis
$ kubectl apply -f deploy/k8s/app.yaml
$ kubectl apply -f deploy/k8s/ingress.yaml

$ curl http://localhost/api/ping
OK
```
