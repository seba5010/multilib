<?php

require_once "HttpRequest.php";

class Post extends HttpRequest {

    private $data;
    private $ssl;

    /**
     * Sets the data to be sent along with the request and specifies if the connection should be secure or not
     * @param array $data
     * @param boolean $ssl
     */
    public function __construct($data, $ssl) {
        $this->data = $this->setData($data);
        $this->ssl = $ssl;
    }

    /**
     * Sends a POST request to the specified URL
     * @param string $url
     * @return string
     */
    public function sendRequest($url) {
        $ch = curl_init();
        if($this->ssl) {
            curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
            curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        }
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $this->data);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $response = curl_exec($ch);
        curl_close($ch);
        return $response;
    }

}

?>