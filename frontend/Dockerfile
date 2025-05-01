FROM node:18.16.0-slim

LABEL maintainer="Thiago Silva"
LABEL email="thyagosousasilva@gmail.com"

WORKDIR /app/chatws/frontend

RUN npm install -g nodemon -y \
    && apt-get update -y \
    && apt-get install git lsof vim iputils-ping -y

    # Copia os arquivos do projeto
COPY . .

# Converte as quebras de linha e dá permissão de execução
RUN dos2unix entrypoint.sh && \
    chmod +x entrypoint.sh

EXPOSE 3007

ENTRYPOINT ["/app/chatws/frontend/entrypoint.sh"]