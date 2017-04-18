FROM mhart/alpine-node:6.10.2

WORKDIR /opt/app
RUN mkdir -p ./public
ADD dist ./public

RUN npm install http-server -g

EXPOSE 3000
ENTRYPOINT http-server -p 3000
