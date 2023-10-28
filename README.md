# Demo Web Server

The Docker Compose file has a simple server with an endpoint secured with
digest authentication.  Start using `docker compose up`.

The endpoint `/api.cgi` is protected with digest authentication, and the
response shows the authenticated user, content type, and body. 

```bash
$ curl --user user:password --digest -d param=value localhost:8080/api.cgi
Remote user: user
Content type: application/x-www-form-urlencoded
Body:
0000000   p   a   r   a   m   =   v   a   l   u   e
0000013
```

# RestTemplate Demo

The demonstration application configures `RestTemplate` with a `HttpClient`
that can provide the required credentials.

The code calls `RestTemplate.postForObject` with `MultiValueMap` to make
a POST request, providing digest authentication when challenged.

This works with Spring Framework 6.0.13, but fails with Spring Framework 6.1.0-RC2.
