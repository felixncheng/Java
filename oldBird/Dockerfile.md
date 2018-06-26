Dockerfile 
===========

> FROM centos
>
>WORKDIR /data/apps/rmi-provider
>
>ADD . /data/apps/rmi-provider
>
>RUN yum -y install python-setuptools
>RUN easy_install supervisor
>RUN rpm -ivh jdk-8u144-linux-x64.rpm
>
>EXPOSE 1099
>
>CMD /usr/bin/supervisord -c supervisord.conf
