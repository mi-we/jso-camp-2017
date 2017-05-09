# DropwizardMovieService

Simple REST application made with Dropwizard and created from the official Dropwizard Maven archetype.

How to start the DropwizardMovieService application
---

1. Run `mvn clean install` to build your application
2. Start application with `java -jar target/dropwizard-movieservice-1.0-SNAPSHOT.jar server config.yml`
3. To check that your application is running enter url `http://localhost:8080`

Get all movies by making a GET request to: `http://localhost:8080/movies`.

Get a specific movie by making a GET request to: `http://localhost:8080/movies/<id>`.

You can create a movie entry by making a POST request to `http://localhost:8080/movies`, with the following request body:

```
{
  "title": "some title",
  "year": "2017"
}
```

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


Useful Links
---

User manual of Dropwizard Core module: `http://www.dropwizard.io/1.1.0/docs/manual/core.html`
User manual of Dropwizard JDBI module: `http://www.dropwizard.io/1.1.0/docs/manual/jdbi.html`
