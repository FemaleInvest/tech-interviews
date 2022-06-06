const express = require("express");

const PORT = 8080;

express()
  .get("/health", (_req, res) => {
    res.status(200).send({ isHealthy: true });
  })
  .listen(PORT, () => {
    console.log(`Server listening on port ${PORT}`);
  });
