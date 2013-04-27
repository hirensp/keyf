package keyf.util;

import java.util.Collection;

/**
 * A utility for ensuring method and constructor non-null contracts are
 * satisfied.
 *
 * @author Justin
 */
public final class ParamUtil
{
    /**
     * Ensures that the {@code string} is not {@code null} and not {@link
     * String#isEmpty() blank}.
     *
     * @param string the string to check
     *
     * @return {@code string}
     *
     * @throws NullPointerException if {@code string} is {@code null}
     * @throws IllegalAccessException if {@code string} is {@link
     *     String#isEmpty() blank}
     */
    public static String requireNonBlank(String string)
    {
        requireNonNull(string);

        if (string.isEmpty())
        {
            throw new IllegalArgumentException("String cannot be blank (\"\")");
        }

        return string;
    }

    /**
     * Ensures that {@code parameter} is not {@code null}.
     *
     * @param parameter The parameter to check for {@code null}
     *
     * @param <T> The type of the parameter
     *
     * @return The {@code parameter}
     *
     * @throws NullPointerException if {@code parameter} is {@code null}.
     */
    public static <T> T requireNonNull(T parameter)
    {
        if (parameter == null)
        {
            throw new NullPointerException("Value must be non-null.");
        }

        return parameter;
    }

    /**
     * Ensures that {@code collection} is not and does not {@link
     * Collection#contains(Object)} a {@code null} value.
     *
     * @param collection The collection to check for {@code null} and contains
     *     {@code null}
     *
     * @param <T> The type of data the collection holds
     * @param <C> The type of the collection
     *
     * @return The {@code collection}
     *
     * @throws NullPointerException if {@code collection} is or contains {@code
     *     null}
     */
    public static <T, C extends Collection<T>>
            C requireNonNullAndContainsNonNull(C collection)
    {
        requireNonNull(collection);

        if (collection.contains(null))
        {
            throw new NullPointerException(
                    "Collection values must be non-null.");
        }

        return collection;
    }

    /**
     * Ensures that {@code collection} is not and does not {@link
     * Collection#contains(Object)} a {@code null} value and is not {@link
     * Collection#isEmpty() empty}.
     *
     * @param collection The collection to check for {@code null} and contains
     *     {@code null} and empty.
     *
     * @param <T> The type of data the collection holds
     * @param <C> The type of the collection
     *
     * @return The {@code collection}
     *
     * @throws NullPointerException if {@code collection} is or contains {@code
     *     null}
     * @throws IllegalArgumentException if {@code collection} is empty.
     */
    public static <T, C extends Collection<T>>
            C requireNonNullAndContainsNonNullAndNotEmpty(C collection)
    {
        requireNonNullAndContainsNonNull(collection);

        if (collection.isEmpty())
        {
            throw new IllegalArgumentException("Collection cannot be empty.");
        }

        return collection;
    }

    /**
     * This class should not be instantiated, ensure that it is not.
     */
    private ParamUtil() {}
}