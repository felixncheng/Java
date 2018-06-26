jenkins  
==================

#install
>wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat/jenkins.repo
>
>rpm --import https://pkg.jenkins.io/redhat/jenkins.io.key
>
>yum install jenkins 

# build
> mvn clean package -Dmaven.test.skip=true&&
>
> mv /var/lib/jenkins/workspace/java/rmi/rmi-provider/target/rmi-provider-1.0-SNAPSHOT-jar-with-dependencies.jar /data/apps/rmi-provider&&
>
> cd /data/apps/rmi-provider&&
>
> docker build -t rmi:${BUILD_NUMBER} .&&docker run -d -p 1099:1099 rmi:${BUILD_NUMBER}