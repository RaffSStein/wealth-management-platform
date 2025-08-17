package raff.stein.customer.service.update.visitor;

import lombok.AllArgsConstructor;

import java.util.Objects;

/**
 * Represents a key for a type, consisting of a raw type and a parameter type.
 * This is useful for generic type handling, especially in scenarios where
 * the raw type and its parameterized type need to be distinguished.
 */
@AllArgsConstructor
public class TypeKey {

    private final Class<?> rawType;
    private final Class<?> parameterType;

    public static TypeKey of(Class<?> rawType, Class<?> parameterType) {
        return new TypeKey(rawType, parameterType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeKey that)) return false;
        return Objects.equals(rawType, that.rawType) &&
                Objects.equals(parameterType, that.parameterType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawType, parameterType);
    }

    @Override
    public String toString() {
        return rawType.getSimpleName() + "<" + parameterType.getSimpleName() + ">";
    }
}
