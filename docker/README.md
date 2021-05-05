# SAML test container

## Glossary

- Identity Provider (IdP): The SAML instance that authenticates users.
- Service Provider (SP): The service that wants to authenticate users; the Tingit application.

## Configure

To test integration with SAML you can run the container in this directory. This file contains two environment variables:

- SIMPLESAMLPHP_SP_ENTITY_ID: This provides the identifying name of the SP.
- SIMPLESAMLPHP_SP_ASSERTION_CONSUMER_SERVICE: The IdP validates the request using this URL.

Enable SAML in the `application.yml`.

```yaml
saml.enabled: true
```

## Users

**In the SP a user should exist with the same username.**

The file `users.php` in this directory defines the users. It contains an array of users. The
`username:password` is used to log into the IdP. The user has four attributes of which the
`username` is relevant.

```php
    array(
        'username:password' => array(
            'uid' => array('2'),
            'username' => 'samluser',
            'samlRole' => array('group2'),
            'email' => 'samln@example.com',
        )
    )
```

# Run

To run the container got to this directory and run:

```bash
docker-compose up
```

The server is now available via: `http://localhost:8090/simplesaml`

# Use

To login with the SAML users, you go to `localhost:8000/api/saml/login`. This should redirect you to
the SAML login page. Login with the credentials defined in `users.php`. If successfully logged in you
will be redirected to the `success_url`.

# Keystore

This step is already done for this repository, but in any case one needs a keystore that works
with this SAML setup you can create a keystore with Key Pair with a self-signed certificate, as
follows:

```bash
keytool -genkeypair -alias 42 -dname CN=42 -keyalg rsa -keysize 4096 -keypass <passwd> -validity 9999 -storepass <passwd> -keystore 42-saml.jks
```

# Truststore

To communicate over SSL the CA Certificate of the IdP must be imported into the Java truststore of the SP.

```bash
keytool -import -alias 42.nl -file public.cert -keystore java_home/lib/security/cacerts
```
