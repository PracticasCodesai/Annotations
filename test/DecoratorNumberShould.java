import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class DecoratorNumberShould {

    @Test
    void add_value_of_annotation_to_getInt_return() {
        int annotation = 5;
        int mult = 2;
        Number number = new Number(5);
        Number decorator = new DecoratorStub(number,annotation);

        assertThat(decorator.multiplication(mult),
                is(number.multiplication(mult)+annotation));
        assertThat(decorator.getInt(),is(number.getInt()+annotation));
    }

}

class DecoratorStub extends DecoratorNumber{

    private int annotationIncrement;

    DecoratorStub(Number number, int increment) {
        super(number);
        this.annotationIncrement = increment;
    }

    @Override
    protected int getIncrement(AddToResult annotation) {
        return annotationIncrement;
    }
}