# annapolis

Kubernetes-native distributed application lab for learning the ropes. An academy, if you will.

## Build

```bash
cd annapolis
lein uberjar
docker build -t annapolis:0.1.0 .
```

## Deploy

```bash
cd annapolis
colima start --kubernetes
helm repo add traefik https://traefik.github.io/charts
helm repo update
helm install traefik traefik/traefik
kubectl apply -f deploy/k8s/app.yaml
kubectl apply -f deploy/k8s/ingress.yaml
```

Open a browser and navigate to http://localhost/api/ping. You should see an `OK`.
