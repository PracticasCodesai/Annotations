import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class UpdateNumberShould {

    @Test
    void when_Number_has_an_annotation_should_be_a_DecoratorNumber_instance() {
        Number number = new Number(5);
        UpdateNumber updateNumber = new UpdateNumber();
        Number newNumber = updateNumber.updateResults(number);

        assertThat(newNumber,
                hasAnnotation(number) ?
                        instanceOf(DecoratorNumber.class) :
                        not(instanceOf(DecoratorNumber.class)));
    }


    private boolean hasAnnotation(Number number) {
        Method[] methods = number.getClass().getMethods();
        for(Method method : methods){
            if(method.getAnnotation(AddToResult.class) != null){return true;}
        }
        return false;
    }

}


