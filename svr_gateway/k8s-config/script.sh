#在镜像服务器上执行
docker build -t hub.atguigu.com/library/svr-gateway:0.0.1-SNAPSHOT .
docker push hub.atguigu.com/library/svr-gateway:0.0.1-SNAPSHOT

#在k8s master上执行

#删除微服务
kubectl delete deployment/svr-gateway
kubectl delete svc/svr-gateway

#方式1，使用run方式运行
kubectl run svr-gateway --image=hub.atguigu.com/library/svr-gateway:0.0.1-SNAPSHOT --port=8000 --NodePort=32222 --replicas=3
kubectl expose deployment svr-gateway --type=NodePort

#方式2，使用yaml方式运行
kubectl apply -f Deployment.yaml

#扩容gateway
kubectl scale --replicas=5 deployment/svr-gateway

#查询微服务器是否正常运行
http://192.168.227.20:32222/actuator/info
http://192.168.227.20:32222/actuator/health
http://192.168.227.20:32222/powerMgr/power/getFuncByUser

#查询eureka是否存在该微服务
curl http://{eureka ip:port}/eureka/apps/svr-gateway
#例子：
curl http://192.168.227.20:31230/eureka/apps/svr-gateway
