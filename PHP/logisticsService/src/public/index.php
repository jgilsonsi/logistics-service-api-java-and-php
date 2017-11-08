<?php

use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';

$config['displayErrorDetails'] = true;
$config['addContentLengthHeader'] = false;
$config['db']['host'] = "localhost";
$config['db']['dbname'] = "logistics_service";
$config['db']['user'] = "root";
$config['db']['pass'] = "master";

$app = new \Slim\App(["config" => $config]);

$app->add(new Slim\Middleware\HttpBasicAuthentication([
    "path" => ["/api/delivery", "/api/user"],
    "realm" => "Protected",
    "authenticator" => new Slim\Middleware\HttpBasicAuthentication\PdoAuthenticator([
        "pdo" => new PDO("mysql:host=" . $config['db']['host'] . ";dbname=" . $config['db']['dbname'], $config['db']['user'], $config['db']['pass']),
        "table" => "users",
        "user" => "username",
        "hash" => "password"
            ])
]));

$container = $app->getContainer();

$container['db'] = function ($c) {
    $dbConfig = $c['config']['db'];
    $pdo = new PDO("mysql:host=" . $dbConfig['host'] . ";dbname=" . $dbConfig['dbname'], $dbConfig['user'], $dbConfig['pass']);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
    $db = new NotORM($pdo);
    return $db;
};

// UPDATE -----------------------------------------------------------------------------------------
$app->put('/api/delivery/{id}', function (Request $request, Response $response) {

    $id = $request->getAttribute('id');
    $body = json_decode($request->getBody(), true);

    $delivery = $this->db->delivery[$id];

    $response_info;
    if ($delivery) {
        $data = array(
            "receiver_name" => $body["receiverName"],
            "receiver_cpf" => $body["receiverCpf"],
            "date_time" => $body["dateTime"]
        );
        if ($delivery->update($data)) {
            $response_info = "Success update!";
        } else {
            $response_info = "Updated!";
        }
    } else {
        $response_info = "Delivery not found!";
    }

    return $response->withJson($response_info);
});

// DELETE -----------------------------------------------------------------------------------------
$app->delete('/api/delivery/{id}', function (Request $request, Response $response) {

    $id = $request->getAttribute('id');

    $delivery = $this->db->delivery[$id];

    $response_info;
    if ($delivery && $delivery->delete()) {
        $response_info = "Deleted with success!";
    } else {
        $response_info = "Delivery not found!";
    }

    return $response->withJson($response_info);
});

// CREATE USER ------------------------------------------------------------------------------------
$app->post('/api/user', function (Request $request, Response $response) {

    $body = json_decode($request->getBody(), true);

    $data = array(
        "username" => $body["username"],
        "password" => password_hash($body["password"], PASSWORD_DEFAULT)
    );

    $response_info;
    if ($this->db->users->insert($data)) {
        $response_info = "Success create!";
    } else {
        $response_info = "User exists!";
    }

    return $response->withJson($response_info);
});

$app->run();
?>