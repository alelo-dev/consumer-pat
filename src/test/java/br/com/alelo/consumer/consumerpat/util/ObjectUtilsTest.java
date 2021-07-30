package br.com.alelo.consumer.consumerpat.util;

import br.com.alelo.consumer.consumerpat.exception.ValidationException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectUtilsTest {

    public static final String FIELD = "Field";

    @Test
    public void shouldNotFailWithNonNullValue() {
        assertDoesNotThrow(
                () -> ObjectUtils.shouldNotBeNull(1, FIELD),
                "Expected shouldNotBeNull() does not throw, but it did"
        );
    }

    @Test
    public void shouldThrowValidationExceptionWithNullValue() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> ObjectUtils.shouldNotBeNull(null, FIELD),
                "Expected shouldNotBeNull() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains(FIELD));
    }

    @Test
    public void shouldCopyOnlyNonNullProperties() {
        // All non null values, except field1
        MockClass source = source1();
        source.setField1(null);

        // All non null values
        MockClass target = source2();

        // Expected all field from source1
        MockClass expected = source1();
        // except field1
        expected.setField1(target.getField1());

        ObjectUtils.copyNonNullProperties(source, target);

        assertMockEquals(target, expected);
    }

    @Test
    public void shouldCopyToNonNullObject() {
        MockClass source = source1();
        MockClass expected = source1();

        MockClass target = ObjectUtils.copyTo(source, MockClass.class);

        assertMockEquals(target, expected);
    }

    @Test
    public void shouldCopyToNonNullObjectFromDifferentClass() {
        // All non null values
        NoEmptyConstructorMockClass source = source3();

        // All non null values
        MockClass target = source1();

        MockClass expected = source1();
        // Only field1 has the same type
        expected.setField1(source.getField1());

        ObjectUtils.copyNonNullProperties(source, target);

        assertMockEquals(target, expected);
    }

    @Test
    public void shouldCopyToNullObject() {
        assertNull(ObjectUtils.copyTo(null, MockClass.class),
                "Expected copyTo result to be null, but it didn't");
    }

    @Test
    public void shouldCopyListToNullObject() {
        final List<MockClass> result = ObjectUtils.copyListTo(null, MockClass.class);
        assertNotNull(result,
                "Expected copyListTo to be non null, but it didn't");
        assertTrue(result.isEmpty(),
                "Expected copyListTo to be empty, but it didn't");
    }

    @Test
    public void shouldCopyListToNonNullList() {
        List<MockClass> source = Arrays.asList(source1(), source2());
        List<MockClass> target = ObjectUtils.copyListTo(source, MockClass.class);

        assertEquals(source.size(), target.size(),
                "Expected list size to be equals, but it didn't");
        for (int index = 0; index < source.size(); index++) {
            assertMockEquals(target.get(index), source.get(index));
        }
    }

    @Test
    public void shouldThrowAtGetInstanceWithNoEmptyConstructor() {
        assertThrows(
                NoSuchMethodException.class,
                () -> ObjectUtils.getInstance(NoEmptyConstructorMockClass.class),
                "Expected getInstance() to throw, but it didn't"
        );
    }

    private void assertMockEquals(MockClass target, MockClass expected) {
        assertEquals(expected.getField1(), target.getField1(),
                "Expected field1 to be equals, but it didn't");

        assertEquals(expected.getField2(), target.getField2(),
                "Expected field2 to be equals, but it didn't");

        assertEquals(expected.getField3(), target.getField3(),
                "Expected field3 to be equals, but it didn't");

        assertEquals(expected.getField4().getField1(), target.getField4().getField1(),
                "Expected field4.field1 to be equals, but it didn't");

        assertEquals(expected.getField4().getField2(), target.getField4().getField2(),
                "Expected field4.field2 to be equals, but it didn't");

        assertEquals(expected.getField4().getField3(), target.getField4().getField3(),
                "Expected field4.field3 to be equals, but it didn't");
    }

    private MockClass source1() {
        return MockClass
                .builder()
                .field1("1")
                .field2(1L)
                .field3(1)
                .field4(MockClass.builder()
                        .field1("4")
                        .field2(4L)
                        .field3(4)
                        .build())
                .build();
    }

    private MockClass source2() {
        return MockClass
                .builder()
                .field1("2")
                .field2(2L)
                .field3(2)
                .field4(MockClass.builder()
                        .field1("5")
                        .field2(5L)
                        .field3(5)
                        .build())
                .build();
    }

    private NoEmptyConstructorMockClass source3() {
        return NoEmptyConstructorMockClass
                .builder()
                .field1("1")
                .field2(1)
                .field3("1")
                .field4(1L)
                .build();
    }

    @Getter
    @Setter
    @Builder
    static class NoEmptyConstructorMockClass {
        private String field1;
        private Integer field2;
        private String field3;
        private Long field4;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    static class MockClass {
        private String field1;
        private Long field2;
        private Integer field3;
        private MockClass field4;

        @Builder
        public MockClass(String field1, Long field2, Integer field3, MockClass field4) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
        }
    }
}