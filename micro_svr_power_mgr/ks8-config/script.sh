#在镜像服务器上执行
docker build -t hub.atguigu.com/library/micro-svr-power-mgr:0.0.1-SNAPSHOT .
docker push hub.atguigu.com/library/micro-svr-power-mgr:0.0.1-SNAPSHOT

#在k8s master上执行

#删除微服务
kubectl delete deployment/micro-svr-power-mgr
kubectl delete svc/micro-svr-power-mgr

#方式1，使用run方式运行
kubectl run micro-svr-power-mgr --image=hub.atguigu.com/library/micro-svr-power-mgr:0.0.1-SNAPSHOT --port=8000 --NodePort=32222 --replicas=3
kubectl expose deployment micro-svr-power-mgr --type=NodePort

#方式2，使用yaml方式运行
kubectl apply -f Deployment.yaml

#扩容gateway
kubectl scale --replicas=5 deployment/micro-svr-power-mgr

#查询微服务器是否正常运行
http://192.168.227.20:32223/actuator/info
http://192.168.227.20:32223/actuator/health
http://192.168.227.20:32223/power/getFuncByUser

#查询eureka是否存在该微服务
curl http://{eureka ip:port}/eureka/apps/micro-svr-power-mgr
#例子：
curl http://192.168.227.20:31230/eureka/apps/micro-svr-power-mgr
