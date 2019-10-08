FROM node:10.15.3

WORKDIR /usr/app

EXPOSE 4200

COPY ./package.json ./
RUN npm install
COPY ./ ./

CMD ["npm", "start"]