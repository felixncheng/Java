supervisord.conf
===================

>[supervisord]
>
>nodaemon=true
>
>[program:rmi-provider]
>
>directory=/data/apps/rmi-provider
>
>command=java -jar rmi-provider-1.0-SNAPSHOT-jar-with-dependencies.jar 
>
>autostart=true
>
>autorestart=true
>
>user=root
