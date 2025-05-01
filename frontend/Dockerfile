FROM node:18.16.0-slim

LABEL maintainer="Thiago Silva"
LABEL email="thyagosousasilva@gmail.com"

WORKDIR /app/chatws/frontend

RUN npm install -g nodemon -y \
    && apt-get update -y \
    && apt-get install git lsof vim iputils-ping -y

    # Copia os arquivos do projeto
COPY . .

# Dá permissão de execução ao script
RUN chmod +x ./entrypoint.sh

EXPOSE 3007

ENTRYPOINT ["./entrypoint.sh"]