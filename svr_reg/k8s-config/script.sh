#在镜像服务器上执行
docker build -t hub.atguigu.com/library/svr-reg:0.0.1-SNAPSHOT .
docker push hub.atguigu.com/library/svr-reg:0.0.1-SNAPSHOT

#在k8s master上执行
kubectl apply -f StatefulSet.yaml

#eureka访问地址
http://192.168.227.20:31230/

#私有化仓库地址
https://hub.atguigu.com/harbor/sign-in
