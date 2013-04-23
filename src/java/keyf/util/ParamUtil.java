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
     * Ensures that {@code collection} is not and does not contain a {@code
     * null} value.
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
     * This class should not be instantiated, ensure that it is not.
     */
    private ParamUtil() {}
}