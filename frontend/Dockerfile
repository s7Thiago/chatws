FROM node:18.16.0-slim

WORKDIR /app/chatws/frontend

RUN npm install -g nodemon -y \
    && apt-get update -y \
    && apt-get install git lsof vim iputils-ping -y

EXPOSE 3007

# CMD npm install -y && npm start
CMD [ "tail", "-f", "/dev/null" ]