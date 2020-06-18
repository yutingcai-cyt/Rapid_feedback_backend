# Rapid_feedback_backend with Springboot

## How to run
```
1.git clone
```
```
2.maven package(get the jar file)
```
```
3.cd docks(find the docker-compose file)
```
```
4.docker-compose up --build(build the image and run it)
```

## Deployment
We deployed backend server on AWS, the base URL is http://ec2-100-25-151-95.compute-1.amazonaws.com:8022/v1

## Use HTTPS
due to security concerns, audio api can only work with front-end in a secure context(https), steps to enable https are:
1. Get the app running on port 80 (or 8080) of on an EC-2 instance. 
2. Register or transfer a domain name to AWS Route 53
3. Create a security group for the instance - open ports 22 and 80 (or 8080)
4. Create a security group for the load balancer - open ports 80 and 443
5. Configure a Target Group - link to the instance on port 80 (or 8080)
6. Configure a Load Balancer - link to the Target Group and add listeners on ports 80 and 443 (when you add a listener to port 443, you’ll kick off a process to obtain a security certificate)
7. Make sure, at Route 53, that the DNS names from the Hosted Zones are set in the Registered Domains section.
8. Configure the Spring Boot App to run securely, and redirect HTTP to HTTPS (discussed in detail below)
9. Make sure that Port 80 of the instance is custom configured to only allow access from the Load Balancer’s security group.

Reference: 
https://geocolumbus.github.io/HTTPS-ELB-AWS-Spring-Boot/


