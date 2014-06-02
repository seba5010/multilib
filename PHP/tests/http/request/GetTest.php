<?php

/**
 * Generated by PHPUnit_SkeletonGenerator 1.2.1 on 2014-06-02 at 18:59:59.
 */
class GetTest extends PHPUnit_Framework_TestCase {

    /**
     * @var Get
     */
    protected $object;

    /**
     * Sets up the fixture, for example, opens a network connection.
     * This method is called before a test is executed.
     */
    protected function setUp() {
        $this->object = new Get(array(
            "q" => "php"
        ), true);
    }

    /**
     * Tears down the fixture, for example, closes a network connection.
     * This method is called after a test is executed.
     */
    protected function tearDown() {
        
    }

    /**
     * @covers Get::sendRequest
     */
    public function testSendRequest() {
        $this->assertNotEmpty($this->object->sendRequest("www.google.com/search"));
    }

}