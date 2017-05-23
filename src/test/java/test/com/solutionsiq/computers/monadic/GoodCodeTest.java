package test.com.solutionsiq.computers.monadic;

import com.solutionsiq.computers.monadic.goodcode.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//
//  So, this is a little better than BadCode.
//
//  We've implemented a MonadicFunction interface, and we've changed the various
//  monadic functions to use that interface.
//
//  That's worked wonders for making our code much more readable and maintainable!
//

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
