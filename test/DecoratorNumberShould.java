import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class DecoratorNumberShould {

    @Test
    void add_value_of_annotation_to_getInt_return() {
        int annotation = 5;
        Number number = new Number(5);
        Number decorator = new DecoratorStub(number,annotation);

        assertThat(number.getInt()+annotation,is(decorator.getInt()));
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