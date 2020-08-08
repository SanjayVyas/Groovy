/*
 * ----------------------------------------------------------------------
 * File:      Closures.groovy
 * Project:   Closures
 * Author:    Sanjay Vyas
 * 
 * Description:
 * 
 * ----------------------------------------------------------------------
 * Revision History:
 * 201-Jun-07    [SV]: Created
 * ----------------------------------------------------------------------
 */

/*
    Closure is a block of code which closes over its environment
    Anonymous functions are called lambdas
    A lambda in groovy can also act as a closure, IF it closes over its environment
 */

// This is a named method
def print() {
    println "Named method"
}

// An anonymous function (lambda) created and assigned to a ref variable
def ref = { println "This is a lambda closure"}

// Correct way to call a lambda is with a .call()
ref.call()

// However, .call() is optional in Groovy
ref()

// You can pass lambda to other methods
def dothis(lambdaref) {
    println "calling lambda method from function dothis"
    lambdaref()
}

def dothat(r) {
    println "calling lambda method from function dothat"
    r()
}

// Pass the lambda ref to both functions
dothis(ref)
dothat(ref)

// You can directly pass lambda to functions arguments
dothis( {println "This is another lambda"} )
dothat { println "Yet another lambda block"}

// Lambdas can be closure too
def enclosingMethod() {
    println "This method contains a closure but doesnt call it"
    def name = "James Strachan"
    def closure = { println "captured $name from outer environment"}
}

enclosingMethod()


// Closure have to be returned on called
def outerMethod(int param) {
    println "This method not only defines a closure but returns it"

    // define a closure and assign to a reference
    def sqr = {

        /*
            param is not a variable defined inside this closure
            but a closure "closes over" its environment
            Even after the outerMethod is over, the closure
            still holds the "captured" value of n which it will
            use when its called later directly
          */

        param ** 2
    }
    return sqr
}

// obtain the closure from the method
innerClosure = outerMethod(5)

/*
    The outermethod has finished executing, so its stackframe is destroyed
    Even the parameter 'param', which was on its stackframe is destroyed
    Yes, the inner closure has captured param internally
  */
printf("innerClosure returned %d\n" , innerClosure()) // will print 25

// Named methods can also be used as closures
def namedMethod() {
    println "nameMethod called"
}

// Now take the address of named method and assign to reference
def cl = this.&namedMethod

// Now pass this reference cl to other methods like a closure
dothis(cl)
dothat(cl)