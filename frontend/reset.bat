docker rm -f chatws_ui
docker rmi chatws-ui
docker build -t chatws-ui:latest .
docker run -d -p 4545:3000 --name chatws_ui -v /c/Users/thyag/Workspaces/Java/Websockets/chat/frontend:/app/chatws/frontend  chatws-ui