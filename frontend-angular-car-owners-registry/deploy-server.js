const express = require("express");
const https = require("https");
const app = express();

app.use("/node_modules", express.static("/src/node_modules"));
app.use("/", express.static("/src/app/"));

app.listen(80, () => console.log("HTTP Server running on port 80"));