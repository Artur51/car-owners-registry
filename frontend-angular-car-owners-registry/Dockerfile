FROM node:16-alpine as compile
WORKDIR /compiled
COPY package.json package-lock.json ./
COPY . .
RUN npm install

RUN npm run build_prod


FROM node:16-alpine as package

WORKDIR /src
COPY --from=compile compiled/dist/frontend-angular-car-owners-registry app
COPY deploy-server.js server.js
COPY deploy-package.json package.json

RUN npm install

EXPOSE 80
CMD ["npm", "start"]

