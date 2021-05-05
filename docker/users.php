<?php

$config = array(

    'admin' => array(
        'core:AdminPassword',
    ),

    'example-userpass' => array(
        'exampleauth:UserPass',
        'saml.user:password' => array(
            'uid' => array('1'),
            'username' => 'saml.user',
            'samlRole' => array('group1'),
            'email' => 'saml.user@example.com',
        ),
        'saml.admin:password' => array(
            'uid' => array('2'),
            'username' => 'saml.admin',
            'samlRole' => array('group2'),
            'email' => 'saml.admin@example.com',
        ),
    ),

);
