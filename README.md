# sparql-service

A small web service that runs SPARQL queries on given datasources and stores the results. Just a demo.

Uses Spring Boot and Apache Jena.

## Run

```bash
docker build -t sparql-service .
docker run -p 8080:8080 sparql-service
```

To make the result store persistent you can mount a volume at `/data`, e.g.:
```bash
docker run -p 8080:8080 -v /tmp/sparql-service-data:/data sparql-service
```

## Use

The service offers two endpoints:
1. `GET /query?sparql=[construct query]&endpoint=[endpoint]`: Runs a given SPARQL CONSTRUCT query against a given endpoint, stores the result and returns it as N-Tuples.
2. `GET /fetch-all`: Returns all N-Tuples that were queried before.

It will respond to other HTTP methods (`POST`, `PUT`, `PATCH`) in the same way to keep things simple.
