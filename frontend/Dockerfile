FROM node

WORKDIR /app/chatws/frontend

COPY . .

RUN npm install -g nodemon -y \
    && apt-get update -y \
    && apt-get install vim -y

EXPOSE 3000

CMD npm install -y && npm start