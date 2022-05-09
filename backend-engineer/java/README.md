# Backend engineer interviewing case

This repository contains the source code for the backend of the Female Invest trading platform, which allows users to:

- Search for public stocks and see their price.
- Place orders to buy or sell stocks
- See their stock portfolio.

The backend is a Java service, built with the Spring Boot framework.

To perform its function, the backend doesn’t integrate directly with stock exchanges. Instead, it relies on Alpaca, a software-as-a-service platform offering an API for trading stocks on the world’s major exchanges. Documentation for the Alpaca API can be found here: https://alpaca.markets/docs/api-documentation/.

## Assignment

Part of the interview consists of a pair programming sessions where you will be asked to extend the trading platform backend in this repository, adding some functionality to it. We will tell you during the interview which functionalities we would like you to add.

## Prerequisites

You should be ready to screen share during the pair programming session. Also, it would be nice if before the interview you could:

- Familiarize yourself a bit with the codebase and the Alpaca API, just so they are not completely new to you.
- Clone the repository.
- Ensure you can build and run the project, either via command line (`mvn spring-boot:run`) or via an IDE. Note: for the project to run, you will need to set the `ALPACA_KEY_ID` and `ALPACA_SECRET_KEY` environment variables. You can either set them to random strings (in which case the project will run, but requests will fail), get valid ones by signing up for Alpaca (Trading API), or you can also ask the interviewer to provide them for you.
