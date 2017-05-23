The Virtues of Inversion of Control 
=====================================
Guide to this code
------------------
This example will implement some classes that compute a value from an argument, as this is the simplest thing that we can implement to demonstrate the concepts we want to talk about. 

We will code a computing application that implements a monadic function service that takes a number and doubles it. We will also implement variations on that interface. We will implement a version of it which can square the numberas well.
 
Let's look at the badcode first.
```java
package com.solutionsiq.computers.monadic.badcode;

public class Doubler {
    private long monadicArgument;
    private long monadicResults;

    public void computeDouble() {
        monadicResults = monadicArgument + monadicArgument;
    }

    public void setDoublerArgument(long monadicArgument) {
        this.monadicArgument = monadicArgument;
    }

    public long getDoubledResult() {
        return monadicResults;
    }
}
```
```java
package com.solutionsiq.computers.monadic.badcode;

public class Squarer {
    private long monadicArgument;
    private long monadicResults;

    public void computeSquare() {
        monadicResults = monadicArgument * monadicArgument;
    }

    public void setSquarerArgument(long monadicArgument) {
        this.monadicArgument = monadicArgument;
    }

    public long getSquaredResult() {
        return monadicResults;
    }

}
```
How bad could this code be?  It has getters and setters.  Looks like Object Oriented code!

The way to tell how bad the code is how it has to be used.  Take a look at the tests for this.

```java
package test.com.solutionsiq.computers.monadic;

import com.solutionsiq.computers.monadic.badcode.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BadCodeTest {

    private List services = new ArrayList();
    private List results = new ArrayList();

    @Test
    public void checkServiceMonadicServices() {

        services.add(new Doubler());
        services.add(new Squarer());

        for (Object service : services) {
            if (service instanceof Doubler) {
                ((Doubler) service).setDoublerArgument((long)8);
            } else if (service instanceof Squarer) {
                ((Squarer) service).setSquarerArgument((long)8);
            }
        }

        for (Object service : services) {
            if (service instanceof Doubler) {
                ((Doubler) service).computeDouble();
            } else if (service instanceof Squarer) {
                ((Squarer) service).computeSquare();
            }
        }

        for (Object service : services) {
            if (service instanceof Doubler) {
                assertThat(((Doubler) service).getDoubledResult(), is((long)16));
            } else if (service instanceof Squarer) {
                assertThat(((Squarer) service).getSquaredResult(), is((long)64));
            }
        }

    }

}
```

When I go to treat these objects as monadic function services, I'm constantly forced to use "Shotgun Surgery" if I want to add a new service, such as a tripler.

Everything I want to do has to be put in ugly if/then/else structures.  And the addition of a new monadic function requires careful editting in three places.

Now, look what happens when I use the Object Oriented concept of an interface, and have my tests depend on the interface for use.

```java
package com.solutionsiq.computers.monadic.goodcode;

public interface MonadicFunction {
    long compute(long monadicValue);
}
```

```java
package com.solutionsiq.computers.monadic.goodcode;

public class Doubler implements MonadicFunction{
    public long compute(long argument) {
        return argument + argument;
    }
}
```

```java
package com.solutionsiq.computers.monadic.goodcode;

public class Squarer implements MonadicFunction{
    public long compute(long argument) {
        return argument * argument;
    }
}
```

Our tests now are under control.

```java
package test.com.solutionsiq.computers.monadic;

import com.solutionsiq.computers.monadic.goodcode.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class GoodCodeTest {

    @Test
    public void checkServiceMonadicServices() {
        List<MonadicFunction> services = new ArrayList();
        List<Long> results = new ArrayList();

        services.add(new Doubler());
        services.add(new Squarer());

        for (MonadicFunction service : services) results.add(service.compute((long)8));

        assertThat(results.get(0), is((long)16));
        assertThat(results.get(1), is((long)64));

    }
}

```

Our code is a lot more readable, because we don't have ugly casts and if/then/else clauses.

And adding a new monadic function is child's play.  Put the new function in the goodcode package.  And implement compute().  That's it!