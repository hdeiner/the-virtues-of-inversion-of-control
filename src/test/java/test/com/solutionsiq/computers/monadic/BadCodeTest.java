package test.com.solutionsiq.computers.monadic;

import com.solutionsiq.computers.monadic.badcode.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//
//  DON'T DO THIS AT HOME!!!
//
//  Notice how every time we add a new monadic function for computing, we havw to change
//  our code in multiple places.  This is classic "Shotgun Surgery".
//
//  And that big if/then/else blocks are a very large large PIA.
//  They are all the same.  But just a little different.
//
//  Also, our code is very repetative.  Not DRY ("Don't repeat yourself") in the least!
//
//  It all seems so very object oriented.  But it's not.  It's bad to the bone.
//

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
