var HelloHelper = Java.type("com.yq.demo.HelloHelper");
var helloHelper = new HelloHelper();

function sayHelloImpl(name) {
    return helloHelper.getPrefix() + " " + name;
}

function newHelloWorld() {
    return new Packages.com.yq.demo.HelloWorld() {
        sayHello: sayHelloImpl
    }
}

newHelloWorld();