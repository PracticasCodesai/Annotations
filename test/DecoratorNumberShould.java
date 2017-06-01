import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class DecoratorNumberShould {

    @Test
    void add_value_of_annotation_to_all_methods_return() throws NoSuchMethodException {
        int mult = 2;
        Number number = new Number(5);
        Number decorator = new DecoratorNumber(number);

        AddToResult annotationGetInt =  number.getClass()
                .getDeclaredMethod("getInt").getAnnotation(AddToResult.class);
        AddToResult annotationMultiplication = number.getClass()
                .getDeclaredMethod("multiplication", int.class).getAnnotation(AddToResult.class);

        assertThat(decorator.multiplication(mult),
                is(number.multiplication(mult)+annotationMultiplication.increment()));
        assertThat(decorator.getInt(),is(number.getInt()+annotationGetInt.increment()));
    }

}
