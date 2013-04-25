package keyf.clueless;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static keyf.util.ParamUtil.*;

/**
 * When the end of an iteration is reached, this "iterator" wraps back around to
 * the beginning. This is not a true "iterator" in that it does not extend
 * {@link Iterator}. This is because 1) there is no {@link Iterator#remove()}
 * method, and there is also no need for a {@link Iterator#hasNext()} method.
 * @author justin
 */
public class WrappingIterator<T>
{
    private final List<T> iterable;

    private Iterator<T> iterator;

    private T current;

    /**
     *
     * @param iterable The items to iterate over (note that a new Collection is
     *     made from this iterable, so modifications to iterable will not affect
     *     this instance)
     */
    public WrappingIterator(Iterable<T> iterable)
    {
        this.iterable = makeCollection(requireNonNull(iterable));
        this.iterator = iterable.iterator();
    }

    /**
     * Returns the next item of the iterable, wrapping if necessary.
     *
     * @return the next item
     */
    public T next()
    {
        if (!iterator.hasNext())
        {
            iterator = iterable.iterator();
        }

        current = iterator.next();

        return current;
    }

    public T current()
    {
        return current;
    }

    private List<T> makeCollection(Iterable<T> iter)
    {
        List<T> list = new ArrayList<T>();

        for (T item : iter)
        {
            list.add(item);
        }
        
        return list;
    }
}